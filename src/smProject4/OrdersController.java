package smProject4;

import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controls all the functions for the previous orders window.
 * @author Aaron Browne, Harshkumar Patel
 */
public class OrdersController implements Initializable {
	@FXML private ComboBox<Integer> orderNumber;
	@FXML private TextField orderPrice;
	@FXML private ListView<String> orderItems;

	private int currentOrderNumber;
	private StoreOrders orders;

	/**
	 * Initializes the values at sets up the program.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		currentOrderNumber = 0;
	}

	/**
	 * Retrieves order information from the main window.
	 * @param mainStore The database of orders from the main window.
	 */
	public void setup(StoreOrders mainStore) {
		orders = mainStore;
		showOrder();
		update();
	}

	/**
	 * Shows the order currently selected or nothing if there are no orders to display.
	 */
	private void showOrder() {
		if(orders.getList().isEmpty()) {
			orderItems.getItems().clear();
			orderPrice.setText("");
		}
		else {
			Order currentOrder = orders.getList().get(currentOrderNumber);
			orderItems.getItems().clear();
			for(MenuItem item : currentOrder.getList()) {
				orderItems.getItems().add(item.toString());
			}
	
			DecimalFormat df = new DecimalFormat("###,##0.00");
			orderPrice.setText(df.format(currentOrder.getTotal()));
		}
	}

	/**
	 * Removes an order from the database when the button is pressed.
	 */
	public void removeOrder() {
		orders.remove(orders.getList().get(currentOrderNumber));
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Order Removed");
		alert.setHeaderText("Order Removed Successfully!");
		alert.setContentText("Order Removed Successfully!");
		alert.showAndWait();
		currentOrderNumber = 0;
		update();
		showOrder();
	}

	/**
	 * Updates the list of orders on the drop down menu.
	 */
	public void update() {
		orderNumber.getItems().clear();
		if(orders.getList().size() == 0) return;
		for(int i = 0; i < orders.getList().size(); i++) {
			orderNumber.getItems().add(i + 1);
		}
		orderNumber.setValue(1);
	}

	/**
	 * Changes the order being displayed when the order number is picked in the drop-down menu.
	 */
	public void changeOrder() {
		if(orderNumber.getValue() == null) return;
		currentOrderNumber = orderNumber.getValue() - 1;
		showOrder();
	}

	/**
	 * Exports information form each of the orders to a text file.
	 */
	public void export() {
		PrintWriter out;
		DecimalFormat df = new DecimalFormat("###,##0.00");
		try {
			out = new PrintWriter("orders.txt");
			for(int i = 0; i < orders.getList().size(); i++) {
				Order o = orders.getList().get(i);
				out.println("\n\nOrder #" + (i  + 1) + ":");
				for(MenuItem item : o.getList()) {
					out.println("\t" + item + "......." + df.format(item.itemPrice()));
				}
				out.println("\nSubtotal:");
				out.println("\t" + df.format(o.orderPrice()));
				out.println("\nTax:");
				out.println("\t" + df.format(o.getTax()));
				out.println("\nTotal:");
				out.println("\t" + df.format(o.getTotal()));
			}
			out.close();
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * Closes the window when the quit button is pressed.
	 */
	public void quit() {
		Stage stage = (Stage) orderPrice.getScene().getWindow();
		stage.close();
	}
}
