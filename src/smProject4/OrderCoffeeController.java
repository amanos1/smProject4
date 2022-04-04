package smProject4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OrderCoffeeController implements Initializable {
	@FXML private CheckBox cream;
	@FXML private CheckBox syrup;
	@FXML private CheckBox milk;
	@FXML private CheckBox caramel;
	@FXML private CheckBox whip;

	@FXML private ComboBox<String> size;
	@FXML private TextField currentPrice;
	
	private Coffee thisCoffee;
	private final int SHORT  = 1;
	private final int TALL   = 2;
	private final int GRANDE = 3;
	private final int VENTI  = 4;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		size.getItems().addAll("Short", "Tall", "Grande", "Venti");
		size.setPromptText("Size");

		thisCoffee = new Coffee(SHORT);

		cream.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) thisCoffee.add(Topping.CREAM);
				else thisCoffee.remove(Topping.CREAM);
				updatePrice();
		    }
		});
	}

	private void updatePrice() {
		currentPrice.setText(null);
	}
}