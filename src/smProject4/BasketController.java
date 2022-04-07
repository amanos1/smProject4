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

public class BasketController implements Initializable {
	@FXML private ListView<String> itemList;
	@FXML private TextField subtotal;
	@FXML private TextField tax;
	@FXML private TextField total;

	Order order;
	MainController main;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		itemList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void populateList(Order stuff, MainController mc) {
		order = stuff;
		main = mc;
		update();
	}

	public void remove() {
		ObservableList<String> selected;
		selected = itemList.getSelectionModel().getSelectedItems();
		for(String trash : selected) {
			order.remove(trash);
		}
		update();
	}

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
