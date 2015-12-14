package ie.dit.student.haverty.alan;

public class Application {

	public static void main(String[] args) {
		ApplicationModel model = new ApplicationModel();
        ApplicationView view = new ApplicationView(model);
        new ApplicationController(view, model);

        view.frame.setVisible(true);
	}
}