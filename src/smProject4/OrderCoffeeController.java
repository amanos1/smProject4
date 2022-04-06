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

public class OrderCoffeeController implements Initializable {
	@FXML private CheckBox cream;
	@FXML private CheckBox syrup;
	@FXML private CheckBox milk;
	@FXML private CheckBox caramel;
	@FXML private CheckBox whip;

	@FXML private ComboBox<String> size;
	@FXML private TextField currentPrice;
	@FXML private ComboBox<String> quantity;
	private int amount;
	
	private Coffee thisCoffee;
	private MainController main;

	private final int SHORT  = 1;
	private final int TALL   = 2;
	private final int GRANDE = 3;
	private final int VENTI  = 4;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		size.getItems().addAll("Short", "Tall", "Grande", "Venti");
		size.setValue("Short");

		thisCoffee = new Coffee();

		updatePrice();
		amount = 1;

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

	private void updatePrice() {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		currentPrice.setText(df.format(thisCoffee.itemPrice()));
	}

	public void setMain(MainController mc) {
		main = mc;
	}

	public void addToOrder() {
		ArrayList<Coffee> coffees = new ArrayList<Coffee>();
		for(int i = 0; i < amount; i++) coffees.add(thisCoffee);
		main.addCoffee(coffees);
		Stage stage = (Stage) cream.getScene().getWindow();
		stage.close();
	}

	public void cancel() {
		Stage stage = (Stage) cream.getScene().getWindow();
		stage.close();
	}
}
