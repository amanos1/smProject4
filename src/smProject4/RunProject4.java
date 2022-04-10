package smProject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Driver class that sets up and loads the program.
 * @author Aaron Browne
 */
public class RunProject4 extends Application {

	/**
	 * Main function that just calls the javafx launch function.
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts the program and launches the window.
	 * @param stage The stage that will be displayed.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Cafe");
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		Parent main = mainLoader.load();
		stage.setScene(new Scene(main, 600, 400));
		stage.show();
		MainController mainController = mainLoader.getController();
		mainController.closeListener(stage);
	}
}
