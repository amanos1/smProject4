package smProject4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

/**
 * Controls all the functions for the current order window.
 * @author Aaron Browne, Harshkumar Patel
 */
public class BasketController implements Initializable {
	@FXML private ListView<String> itemList;
	@FXML private TextField subtotal;
	@FXML private TextField tax;
	@FXML private TextField total;

	Order order;
	MainController main;

	/**
	 * Initializes the values at sets up the program.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		itemList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	/**
	 * Retrieves information from the main window.
	 * @param stuff	The current order.
	 * @param mc The main controller object.
	 */
	public void populateList(Order stuff, MainController mc) {
		order = stuff;
		main = mc;
		update();
	}

	/**
	 * Removes an item/items from the list when the button is pressed.
	 */
	public void remove() {
		ObservableList<String> selected;
		selected = itemList.getSelectionModel().getSelectedItems();
		for(String trash : selected) {
			order.remove(trash);
		}
		update();
	}

	/**
	 * Adds the current order to the list of orders and closes the window.
	 */
	public void submit() {
		main.addOrder();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Order Removed");
		alert.setHeaderText("Order Added Successfully!");
		alert.setContentText("Order Added Successfully!");
		alert.showAndWait();
		Stage stage = (Stage) tax.getScene().getWindow();
		stage.close();
	}

	/**
	 * Displays all the items currently in the order and the price.
	 */
	public void update() {
		itemList.getItems().clear();
		for(MenuItem item : order.getList()) {
			itemList.getItems().add(item.toString());
		}

		DecimalFormat df = new DecimalFormat("###,##0.00");
		subtotal.setText(df.format(order.orderPrice()));
		tax.setText(df.format(order.getTax()));
		total.setText(df.format(order.getTotal()));
	}
}
