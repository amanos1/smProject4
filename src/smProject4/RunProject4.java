package smProject4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunProject4 extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starts the program and launches the window.
	 * @param stage The stage that will be displayed.
	 */
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setTitle("Cafe");
		Parent main = FXMLLoader.load(getClass().getResource("MainView.fxml"));
		stage.setScene(new Scene(main, 800, 400));
		stage.show();
	}
}
