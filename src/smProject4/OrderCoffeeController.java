package smProject4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class OrderCoffeeController implements Initializable {
	@FXML private CheckBox cream;
	@FXML private CheckBox syrup;
	@FXML private CheckBox milk;
	@FXML private CheckBox caramel;
	@FXML private CheckBox whip;

	@FXML private ComboBox<String> size;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		size.getItems().addAll("Short", "Tall", "Grande", "Venti");
	}
}