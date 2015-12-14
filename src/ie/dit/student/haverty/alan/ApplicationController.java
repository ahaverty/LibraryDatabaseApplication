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
		
	}
	
	class BranchSelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			/*
			 * Set the models chosen branch by comparing the selected branch name
			 */
			Branch selectedBranch = view.getSelectedBranch();
			model.selectBranch(selectedBranch.getId());
			
			/*
			 * Open the tabbed view
			 */
			view.openTabbedCard();
			view.resetTabbedPages();
		}
	}
	

}
