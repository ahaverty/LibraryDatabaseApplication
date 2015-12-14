package ie.dit.student.haverty.alan;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JTabbedPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ApplicationView{

	JFrame frame;
	JTextField textFieldFineAmount;
	JTextField textFieldBookName;
	JTextField textFieldAuthorName;
	JTextField textFieldPublisherName;
	
	JLabel lblPageTitle;
	JButton btnMainMenu;
	
	JPanel rootCard;
	JPanel patronPanel;
	
	private String branchSelectCard = "branchSelectCard";
	private String tabbedCard = "tabbedCard";
	private String patronSelect = "patronSelectCard";
	private String patronFunctions = "patronFunctionsCard";
	
	JButton btnSelectBranch = new JButton("Select Branch");
	JComboBox<Branch> comboBranchNames;
	
	JButton btnSelectPatron;
	JComboBox<Patron> comboBoxPatrons;
	JComboBox<Book> comboBoxBooks;
	
	JButton btnSubmitNewBook;
	JButton btnAddACopy;
	
	JTextArea textAreaBranchInformation;
	
	JButton btnCheckoutBook;
	JComboBox<BookCopy> comboBoxCheckoutList;
	JComboBox<BookCopy> comboBoxReturnList;
	JButton btnReturnBook;
	JButton btnConfirmPayment;
	JTextArea textArea;
	
	private ApplicationModel model;

	/**
	 * Create the application.
	 */
	public ApplicationView(ApplicationModel model) {
		this.model = model;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/*
		 * Set the look and feel of the swing application
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 615, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel pageTitle = new JPanel();
		header.add(pageTitle);
		
		lblPageTitle = new JLabel(model.getPageTitle());
		lblPageTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		pageTitle.add(lblPageTitle);
		
		btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setVisible(false);
		header.add(btnMainMenu, BorderLayout.WEST);
		
		rootCard = new JPanel();
		frame.getContentPane().add(rootCard, BorderLayout.CENTER);
		rootCard.setLayout(new CardLayout(0, 0));
		
		JPanel branchSelect = new JPanel();
		rootCard.add(branchSelect, branchSelectCard);
		branchSelect.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBranchSelectHelpText = new JLabel("Select a library branch:");
		branchSelect.add(lblBranchSelectHelpText);
		
		comboBranchNames = new JComboBox<Branch>();
		comboBranchNames.setRenderer(new BranchRenderer());
		setBranchNames();
		branchSelect.add(comboBranchNames);
		
		btnSelectBranch.setAlignmentX(Component.CENTER_ALIGNMENT);
		branchSelect.add(btnSelectBranch);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		rootCard.add(tabbedPane, tabbedCard);
		
		patronPanel = new JPanel();
		tabbedPane.addTab("Patrons", null, patronPanel, null);
		patronPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelSelectPatron = new JPanel();
		patronPanel.add(panelSelectPatron, patronSelect);
		
		comboBoxPatrons = new JComboBox<Patron>();
		comboBoxPatrons.setRenderer(new PatronRenderer());
		panelSelectPatron.add(comboBoxPatrons);
		
		btnSelectPatron = new JButton("Select Patron");
		panelSelectPatron.add(btnSelectPatron);
		
		JPanel panelPatronFunctions = new JPanel();
		patronPanel.add(panelPatronFunctions, patronFunctions);
		panelPatronFunctions.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:pref"),
				ColumnSpec.decode("min:grow"),
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblCheckoutBook = new JLabel("Checkout Book");
		panelPatronFunctions.add(lblCheckoutBook, "1, 1, right, default");
		
		btnCheckoutBook = new JButton("Submit");
		btnCheckoutBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		comboBoxCheckoutList = new JComboBox<BookCopy>();
		comboBoxCheckoutList.setRenderer(new BookRenderer());
		panelPatronFunctions.add(comboBoxCheckoutList, "2, 1, fill, default");
		panelPatronFunctions.add(btnCheckoutBook, "3, 1");
		
		JLabel lblReturnBook = new JLabel("Return Book");
		panelPatronFunctions.add(lblReturnBook, "1, 2, right, default");
		
		comboBoxReturnList = new JComboBox<BookCopy>();
		comboBoxReturnList.setRenderer(new BookRenderer());
		panelPatronFunctions.add(comboBoxReturnList, "2, 2, fill, default");
		
		btnReturnBook = new JButton("Submit");
		panelPatronFunctions.add(btnReturnBook, "3, 2");
		
		JLabel lblPayFine = new JLabel("Pay Fine");
		panelPatronFunctions.add(lblPayFine, "1, 3, right, default");
		
		textFieldFineAmount = new JTextField();
		textFieldFineAmount.setEditable(false);
		panelPatronFunctions.add(textFieldFineAmount, "2, 3, fill, default");
		textFieldFineAmount.setColumns(10);
		
		btnConfirmPayment = new JButton("Confirm Payment");
		panelPatronFunctions.add(btnConfirmPayment, "3, 3");
		
		JLabel lblLoanHistory = new JLabel("Loan history");
		lblLoanHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelPatronFunctions.add(lblLoanHistory, "1, 4, 3, 1, center, default");
		
		textArea = new JTextArea();
		panelPatronFunctions.add(textArea, "1, 6, 3, 1, fill, fill");
		
		JPanel administrationPanel = new JPanel();
		tabbedPane.addTab("Administration", null, administrationPanel, null);
		administrationPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel administrationPanelFunctions = new JPanel();
		administrationPanel.add(administrationPanelFunctions);
		administrationPanelFunctions.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),}));
		
		JLabel lblAddABook = new JLabel("Add a Book");
		lblAddABook.setHorizontalAlignment(SwingConstants.CENTER);
		administrationPanelFunctions.add(lblAddABook, "1, 1, 2, 1");
		
		JLabel lblBookName = new JLabel("Book name");
		administrationPanelFunctions.add(lblBookName, "1, 3, right, default");
		
		textFieldBookName = new JTextField();
		administrationPanelFunctions.add(textFieldBookName, "2, 3, fill, default");
		textFieldBookName.setColumns(10);
		
		JLabel lblAuthorName = new JLabel("Author name");
		administrationPanelFunctions.add(lblAuthorName, "1, 5, right, default");
		
		textFieldAuthorName = new JTextField();
		administrationPanelFunctions.add(textFieldAuthorName, "2, 5, fill, default");
		textFieldAuthorName.setColumns(10);
		
		JLabel lblPublisherName = new JLabel("Publisher name");
		administrationPanelFunctions.add(lblPublisherName, "1, 7, right, default");
		
		textFieldPublisherName = new JTextField();
		administrationPanelFunctions.add(textFieldPublisherName, "2, 7, fill, default");
		textFieldPublisherName.setColumns(10);
		
		btnSubmitNewBook = new JButton("Submit book to the database");
		btnSubmitNewBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		administrationPanelFunctions.add(btnSubmitNewBook, "1, 9, 2, 1");
		
		JLabel lblNewLabel = new JLabel("Add a book copy to this branch");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setEnabled(true);
		administrationPanelFunctions.add(lblNewLabel, "1, 11, 2, 1");
		
		JLabel lblSelectABook = new JLabel("Select a book");
		lblSelectABook.setHorizontalAlignment(SwingConstants.TRAILING);
		administrationPanelFunctions.add(lblSelectABook, "1, 13, right, default");
		
		comboBoxBooks = new JComboBox<Book>();
		comboBoxBooks.setRenderer(new BookRenderer());
		administrationPanelFunctions.add(comboBoxBooks, "2, 13, fill, default");
		
		btnAddACopy = new JButton("Add a copy to this branch");
		administrationPanelFunctions.add(btnAddACopy, "1, 15, 2, 1");
		
		JLabel lblBranchInformation = new JLabel("Branch Information");
		lblBranchInformation.setHorizontalAlignment(SwingConstants.CENTER);
		administrationPanelFunctions.add(lblBranchInformation, "1, 17, 2, 1");
		
		textAreaBranchInformation = new JTextArea();
		textAreaBranchInformation.setEditable(false);
		administrationPanelFunctions.add(textAreaBranchInformation, "1, 19, 2, 1, fill, bottom");
	}
	
	/**
	 * Customer renderers to define how items are displayed in combolists
	 * @author Alan
	 *
	 */
	public static class BookRenderer extends DefaultListCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent( JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
	        Object item = value;
	        if( item instanceof Book || item instanceof BookCopy  || item instanceof BookLoan) {
	            item = ( ( Book ) item ).getTitle() + " by " + ( ( Book ) item ).getAuthorName();
	        }
	        return super.getListCellRendererComponent( list, item, index, isSelected, cellHasFocus);
	    }
	}
	
	public static class BranchRenderer extends DefaultListCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent( JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
	        Object item = value;
	        if( item instanceof Branch ) {
	            item = ( ( Branch ) item ).getId() + " - " + ( ( Branch ) item ).getName();
	        }
	        return super.getListCellRendererComponent( list, item, index, isSelected, cellHasFocus);
	    }
	}
	
	public static class PatronRenderer extends DefaultListCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent( JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
	        Object item = value;
	        if( item instanceof Patron ) {
	            item = "#" + ( ( Patron ) item ).getId() + " - " + ( ( Patron ) item ).getName();
	        }
	        return super.getListCellRendererComponent( list, item, index, isSelected, cellHasFocus);
	    }
	}
	
	void addBranchSelectListener(ActionListener listener) {
		btnSelectBranch.addActionListener(listener);
    }
	
	void addMainMenuListener(ActionListener listener) {
		btnMainMenu.addActionListener(listener);
	}
	
	void addSubmitNewBookListener(ActionListener listener) {
		btnSubmitNewBook.addActionListener(listener);
	}
	
	void addBookCopyListener(ActionListener listener) {
		btnAddACopy.addActionListener(listener);
	}
	
	void addPatronSelectListener(ActionListener listener) {
		btnSelectPatron.addActionListener(listener);
	}
	
	void addCheckoutBookListener(ActionListener listener) {
		btnCheckoutBook.addActionListener(listener);
	}
	
	void addReturnBookListener(ActionListener listener) {
		btnReturnBook.addActionListener(listener);
	}
	
	void addPayFineListener(ActionListener listener) {
		btnConfirmPayment.addActionListener(listener);
	}
	
	/**
	 * Create a popup alert with the provided message and an 'ok' button
	 * @param s
	 */
	public void alert(String s){
	   JOptionPane.showMessageDialog(null, s);
	}
	
	void resetPageTitle() {
		lblPageTitle.setText(model.getPageTitle());
	}
	
	/**
	 * Set the list of branch names
	 */
	void setBranchNames() {
		comboBranchNames.removeAllItems();
		
		for(Branch branch : model.getBranches()) {
			comboBranchNames.addItem(branch);
		}
	}
	
	
	
	Branch getSelectedBranch() {
		return (Branch) comboBranchNames.getSelectedItem();
	}
	
	void openBranchSelectCard() {
		enableHomeButton(false);
		CardLayout rootCardLayout = (CardLayout) rootCard.getLayout();
		rootCardLayout.show(rootCard, branchSelectCard);
	}
	
	void openTabbedCard() {
		enableHomeButton(true);
		CardLayout rootCardLayout = (CardLayout) rootCard.getLayout();
		rootCardLayout.show(rootCard, tabbedCard);
	}
	
	void openPatronSelectCard() {
		enableHomeButton(true);
		CardLayout patronCardLayout = (CardLayout) patronPanel.getLayout();
		patronCardLayout.show(patronPanel, patronSelect);
	}
	
	void openPatronFunctionsCard() {
		enableHomeButton(true);
		CardLayout patronCardLayout = (CardLayout) patronPanel.getLayout();
		patronCardLayout.show(patronPanel, patronFunctions);
	}
	
	void resetTabbedPages() {
		resetPatronList();
		resetAdministrationFunctions();
		resetPageTitle();
	}
	
	void resetPatronList() {
		comboBoxPatrons.removeAllItems();
		for(Patron patron : model.getPatrons()){
			comboBoxPatrons.addItem(patron);
		}
	}
	
	void resetAdministrationFunctions() {
		resetAdministrationFields();
		resetBookList();
		resetBranchInformation();
	}
	
	/**
	 * Reset the admin panel text fields to empty
	 */
	void resetAdministrationFields() {
		textFieldAuthorName.setText("");
		textFieldBookName.setText("");
		textFieldPublisherName.setText("");
	}
	
	void resetBookList(){
		comboBoxBooks.removeAllItems();
		for(Book book : model.getAllBooks()) {
			comboBoxBooks.addItem(book);
		}
	}
	
	void resetBranchInformation() {
		Branch selectedBranch = model.getSelectedBranch();
		StringBuilder sb = new StringBuilder();

		sb.append("Branch ID: ");
		sb.append(selectedBranch.getId());
		sb.append("\n");
		sb.append(selectedBranch.getName());
		sb.append("\n");
		sb.append(selectedBranch.getAddress());
		textAreaBranchInformation.setText(sb.toString());
	}
	
	void enableHomeButton(boolean enable) {
		btnMainMenu.setVisible(enable);
	}
	
	void resetPatronFunctions() {
		resetAvailableBooks();
		resetReturnableBooks();
		resetFine();
		resetLoanHistory();
	}
	
	void resetAvailableBooks() {
		comboBoxCheckoutList.removeAllItems();
		for (BookCopy bookCopy : model.getPatronsAvailableBookCopies()){
			comboBoxCheckoutList.addItem(bookCopy);
		}
	}
	
	void resetReturnableBooks() {
		comboBoxReturnList.removeAllItems();
		for (BookLoan bookLoan : model.getPatronsReturnableBookCopies()) {
			comboBoxReturnList.addItem(bookLoan);
		}
	}
	
	void resetFine() {
		textFieldFineAmount.setText(model.getPatronsDues());
	}
	
	void resetLoanHistory() {
		textArea.setText(model.getPatronsLoanHistory());
	}

}
