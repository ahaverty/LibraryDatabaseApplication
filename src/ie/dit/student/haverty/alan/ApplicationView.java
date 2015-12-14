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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	JPanel rootCard;
	JPanel patronPanel;
	
	private String branchSelectCard = "branchSelectCard";
	private String tabbedCard = "tabbedCard";
	
	JButton btnSelectBranch = new JButton("Select Branch");
	private JComboBox<Branch> comboBranchNames;
	private JComboBox<String> comboBoxPatrons;
	JComboBox<Book> comboBoxBooks;
	
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
		frame.setBounds(100, 100, 461, 627);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel page_title = new JPanel();
		header.add(page_title);
		
		JLabel lblPageTitle = new JLabel(model.getPageTitle());
		lblPageTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		page_title.add(lblPageTitle);
		
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
		patronPanel.add(panelSelectPatron, "patronSelect");
		
		comboBoxPatrons = new JComboBox<String>();
		panelSelectPatron.add(comboBoxPatrons);
		
		JButton btnNewButton = new JButton("Select Patron");
		panelSelectPatron.add(btnNewButton);
		
		JPanel panelPatronFunctions = new JPanel();
		patronPanel.add(panelPatronFunctions, "patronFunctions");
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
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JComboBox comboBox = new JComboBox();
		panelPatronFunctions.add(comboBox, "2, 1, fill, default");
		panelPatronFunctions.add(btnNewButton_1, "3, 1");
		
		JLabel lblReturnBook = new JLabel("Return Book");
		panelPatronFunctions.add(lblReturnBook, "1, 2, right, default");
		
		JComboBox comboBox_1 = new JComboBox();
		panelPatronFunctions.add(comboBox_1, "2, 2, fill, default");
		
		JButton btnSubmit = new JButton("Submit");
		panelPatronFunctions.add(btnSubmit, "3, 2");
		
		JLabel lblPayFine = new JLabel("Pay Fine");
		panelPatronFunctions.add(lblPayFine, "1, 3, right, default");
		
		textField = new JTextField();
		textField.setEditable(false);
		panelPatronFunctions.add(textField, "2, 3, fill, default");
		textField.setColumns(10);
		
		JButton btnConfirmPayment = new JButton("Confirm Payment");
		panelPatronFunctions.add(btnConfirmPayment, "3, 3");
		
		JLabel lblLoanHistory = new JLabel("Loan history");
		lblLoanHistory.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelPatronFunctions.add(lblLoanHistory, "1, 4, 3, 1, center, default");
		
		JTextArea textArea = new JTextArea();
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblAddABook = new JLabel("Add a Book");
		lblAddABook.setHorizontalAlignment(SwingConstants.CENTER);
		administrationPanelFunctions.add(lblAddABook, "1, 1, 2, 1");
		
		JLabel lblBookName = new JLabel("Book name");
		administrationPanelFunctions.add(lblBookName, "1, 3, right, default");
		
		textField_1 = new JTextField();
		administrationPanelFunctions.add(textField_1, "2, 3, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblAuthorName = new JLabel("Author name");
		administrationPanelFunctions.add(lblAuthorName, "1, 5, right, default");
		
		textField_2 = new JTextField();
		administrationPanelFunctions.add(textField_2, "2, 5, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblPublisherName = new JLabel("Publisher name");
		administrationPanelFunctions.add(lblPublisherName, "1, 7, right, default");
		
		textField_3 = new JTextField();
		administrationPanelFunctions.add(textField_3, "2, 7, fill, default");
		textField_3.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Submit book to the database");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		administrationPanelFunctions.add(btnNewButton_2, "1, 9, 2, 1");
		
		JLabel lblSearchForA = new JLabel("Search for a book");
		lblSearchForA.setHorizontalAlignment(SwingConstants.CENTER);
		administrationPanelFunctions.add(lblSearchForA, "1, 11, 2, 1");
		
		JLabel lblBookTitle = new JLabel("Book title");
		administrationPanelFunctions.add(lblBookTitle, "1, 13, right, default");
		
		textField_4 = new JTextField();
		administrationPanelFunctions.add(textField_4, "2, 13, fill, default");
		textField_4.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Search");
		administrationPanelFunctions.add(btnNewButton_3, "1, 15, 2, 1");
		
		JLabel lblNewLabel = new JLabel("Add a book copy to this branch");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setEnabled(true);
		administrationPanelFunctions.add(lblNewLabel, "1, 17, 2, 1");
		
		JLabel lblSelectABook = new JLabel("Select a book");
		lblSelectABook.setHorizontalAlignment(SwingConstants.TRAILING);
		administrationPanelFunctions.add(lblSelectABook, "1, 19, right, default");
		
		comboBoxBooks = new JComboBox();
		comboBoxBooks.setRenderer(new BookRenderer());
		administrationPanelFunctions.add(comboBoxBooks, "2, 19, fill, default");
		
		JButton btnAddACopy = new JButton("Add a copy to this branch");
		administrationPanelFunctions.add(btnAddACopy, "1, 21, 2, 1");
		
		JLabel lblBranchInformation = new JLabel("Branch Information");
		lblBranchInformation.setHorizontalAlignment(SwingConstants.CENTER);
		administrationPanelFunctions.add(lblBranchInformation, "1, 23, 2, 1");
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		administrationPanelFunctions.add(textArea_1, "1, 25, 2, 1, fill, fill");
	}
	
	/**
	 * Customer renderer to show title and author in combolist for books
	 * @author Alan
	 *
	 */
	public static class BookRenderer extends DefaultListCellRenderer {
	    public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
	        Object item = value;
	        if( item instanceof Book ) {
	            item = ( ( Book ) item ).getTitle() + " by " + ( ( Book ) item ).getAuthorName();
	        }
	        return super.getListCellRendererComponent( list, item, index, isSelected, cellHasFocus);
	    }
	}
	
	public static class BranchRenderer extends DefaultListCellRenderer {
	    public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
	        Object item = value;
	        if( item instanceof Branch ) {
	            item = ( ( Branch ) item ).getId() + " - " + ( ( Branch ) item ).getName();
	        }
	        return super.getListCellRendererComponent( list, item, index, isSelected, cellHasFocus);
	    }
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
	
	void addBranchSelectListener(ActionListener listener) {
		btnSelectBranch.addActionListener(listener);
    }
	
	Branch getSelectedBranch() {
		return (Branch) comboBranchNames.getSelectedItem();
	}
	
	void openBranchSelectCard() {
		CardLayout rootCardLayout = (CardLayout) rootCard.getLayout();
		rootCardLayout.show(rootCard, branchSelectCard);
	}
	
	void openTabbedCard() {
		CardLayout rootCardLayout = (CardLayout) rootCard.getLayout();
		rootCardLayout.show(rootCard, tabbedCard);
	}
	
	void resetTabbedPages() {
		resetPatronList();
		resetAdministrationFunctions();
	}
	
	void resetPatronList() {
		for(Patron patron : model.getPatrons()){
			comboBoxPatrons.addItem(patron.getName());
		}
	}
	
	void resetAdministrationFunctions() {
		resetBookList();
	}
	
	void resetBookList(){
		for(Book book : model.getAllBooks()) {
			comboBoxBooks.addItem(book);
		}
	}

}
