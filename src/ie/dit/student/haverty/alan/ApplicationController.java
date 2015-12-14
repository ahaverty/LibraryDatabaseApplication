package ie.dit.student.haverty.alan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationController {
	
	private ApplicationView view;
	private ApplicationModel model;
	
	ApplicationController(ApplicationView view, ApplicationModel model) {
		this.model = model;
		this.view = view;
		
		view.addBranchSelectListener(new BranchSelectListener());
		view.addMainMenuListener(new MainMenuButtonListener());
		view.addSubmitNewBookListener(new InsertBookButtonListener());
	}
	
	class MainMenuButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			model.reset();
			view.resetPageTitle();
			view.openBranchSelectCard();
			
		}
	}
	
	class BranchSelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			/*
			 * Set the page title 
			 */
			model.setPageTitle("Branch Functions");
			
			/*
			 * Set the models chosen branch for the admin functions and patron
			 */
			Branch selectedBranch = view.getSelectedBranch();
			model.selectBranch(selectedBranch);
			
			/*
			 * Open the tabbed view
			 */

			view.resetTabbedPages();
			view.openTabbedCard();
		}
	}
	
	class InsertBookButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			boolean success = model.insertBook(view.textFieldBookName.getText(), view.textFieldAuthorName.getText(), view.textFieldPublisherName.getText());
			if (success) {
				view.alert("Successfully inserted the new book!");
			} else {
				view.alert("Error inserting book, please try again...");
			}
			
			view.resetAdministrationFunctions();
			
		}
	}
	
	
	

}
