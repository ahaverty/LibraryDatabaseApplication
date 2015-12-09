package ie.dit.student.haverty.alan;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Application {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		frame.getContentPane().add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 0));
		
		JPanel page_title = new JPanel();
		header.add(page_title);
		
		JLabel lblPageTitle = new JLabel("Page Title");
		lblPageTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		page_title.add(lblPageTitle);
		
		JPanel rootCard = new JPanel();
		frame.getContentPane().add(rootCard, BorderLayout.CENTER);
		rootCard.setLayout(new CardLayout(0, 0));
		
		JPanel branchSelect = new JPanel();
		rootCard.add(branchSelect, "name_209982383061979");
		branchSelect.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBranchSelectHelpText = new JLabel("Select a library branch:");
		branchSelect.add(lblBranchSelectHelpText);
		
		JComboBox comboBranchNames = new JComboBox();
		comboBranchNames.setModel(new DefaultComboBoxModel(new String[] {"Bolton Street", "Kevin Street"}));
		branchSelect.add(comboBranchNames);
		
		JButton btnSelectBranch = new JButton("Select Branch");
		btnSelectBranch.setAlignmentX(Component.CENTER_ALIGNMENT);
		branchSelect.add(btnSelectBranch);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		rootCard.add(tabbedPane, "name_210836601530489");
		
		JPanel patronPanel = new JPanel();
		tabbedPane.addTab("Patrons", null, patronPanel, null);
		patronPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panelSelectPatron = new JPanel();
		patronPanel.add(panelSelectPatron, "name_211393038128806");
		
		JComboBox comboBoxPatrons = new JComboBox();
		panelSelectPatron.add(comboBoxPatrons);
		
		JButton btnNewButton = new JButton("Select Patron");
		panelSelectPatron.add(btnNewButton);
		
		JPanel panelPatronFunctions = new JPanel();
		patronPanel.add(panelPatronFunctions, "name_213285884191200");
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
	}

}
