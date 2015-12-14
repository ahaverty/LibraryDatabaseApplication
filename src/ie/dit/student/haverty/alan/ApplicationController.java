package ie.dit.student.haverty.alan;

public class ApplicationController {
	
	private ApplicationView view;
	private ApplicationModel model;
	
	ApplicationController(ApplicationView view, ApplicationModel model) {
		this.model = model;
		this.view = view;
		
		//TODO add listeners
	}
	

}
