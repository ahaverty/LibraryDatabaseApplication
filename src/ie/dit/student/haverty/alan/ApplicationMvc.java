package ie.dit.student.haverty.alan;

public class ApplicationMvc {

	public static void main(String[] args) {
		ApplicationModel model = new ApplicationModel();
        ApplicationView view = new ApplicationView(model);
        ApplicationController controller = new ApplicationController(model, view);

        view.setVisible(true);
	}

}
