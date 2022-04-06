package smProject4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controls all the functions of the Coffee ordering window.
 * @author Aaron Browne, Harshkumar Patel
 */
public class OrderCoffeeController implements Initializable {
	@FXML private CheckBox cream;
	@FXML private CheckBox syrup;
	@FXML private CheckBox milk;
	@FXML private CheckBox caramel;
	@FXML private CheckBox whip;

	@FXML private ComboBox<String> size;
	@FXML private TextField currentPrice;
	@FXML private ComboBox<Integer> quantity;
	private int amount;
	
	private Coffee thisCoffee;
	private MainController main;

	private final int SHORT  = 1;
	private final int TALL   = 2;
	private final int GRANDE = 3;
	private final int VENTI  = 4;

	/**
	 * Initializes the values at sets up the program.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		size.getItems().addAll("Short", "Tall", "Grande", "Venti");
		size.setValue("Short");

		quantity.getItems().addAll(1, 2, 3, 4, 5);
		quantity.setValue(1);

		thisCoffee = new Coffee();

		amount = 1;
		updatePrice();
		checkCheckBoxes();
	}

	/**
	 * Changes the price and updates the coffee object when the checkboxes are checked or unchecked.
	 */
	private void checkCheckBoxes() {
		cream.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) thisCoffee.add(Topping.CREAM);
				else thisCoffee.remove(Topping.CREAM);
				updatePrice();
		    }
		});

		syrup.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) thisCoffee.add(Topping.SYRUP);
				else thisCoffee.remove(Topping.SYRUP);
				updatePrice();
		    }
		});

		milk.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) thisCoffee.add(Topping.MILK);
				else thisCoffee.remove(Topping.MILK);
				updatePrice();
		    }
		});

		caramel.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) thisCoffee.add(Topping.CARAMEL);
				else thisCoffee.remove(Topping.CARAMEL);
				updatePrice();
		    }
		});

		whip.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue) thisCoffee.add(Topping.WHIPPED_CREAM);
				else thisCoffee.remove(Topping.WHIPPED_CREAM);
				updatePrice();
		    }
		});
	}

	/**
	 * Updates the current coffee object when the size of the coffee is changed.
	 */
	public void sizeChange() {
		String newSize = size.getValue();
		int newSizeNumber = 0;
		switch(newSize) {
		case "Short":
			newSizeNumber = SHORT;
			break;
		case "Tall":
			newSizeNumber = TALL;
			break;
		case "Grande":
			newSizeNumber = GRANDE;
			break;
		case "Venti":
			newSizeNumber = VENTI;
		}

		thisCoffee.changeSize(newSizeNumber);
		updatePrice();
	}

	/**
	 * Updates the quantity variable when the user changes the quantity in the GUI.
	 */
	public void quantityChanged() {
		int newQuantity = quantity.getValue();
		amount = newQuantity;
		updatePrice();
	}

	/**
	 * Displays the current price on the GUI.
	 */
	private void updatePrice() {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		currentPrice.setText(df.format(thisCoffee.itemPrice() * amount));
	}

	/**
	 * Sets the main controller.
	 * @param mc
	 */
	public void setMain(MainController mc) {
		main = mc;
	}

	/**
	 * Adds the current coffee(s) to the order when the user presses the submit button.
	 */
	public void addToOrder() {
		ArrayList<Coffee> coffees = new ArrayList<Coffee>();
		for(int i = 0; i < amount; i++) coffees.add(thisCoffee);
		main.addCoffee(coffees);
		Stage stage = (Stage) cream.getScene().getWindow();
		stage.close();
	}

	/**
	 * Closes the window when the user presses the cancel button.
	 */
	public void cancel() {
		Stage stage = (Stage) cream.getScene().getWindow();
		stage.close();
	}
}
