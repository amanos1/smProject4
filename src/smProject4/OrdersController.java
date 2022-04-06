package smProject4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrdersController implements Initializable {
	@FXML private ComboBox<Integer> orderNumber;
	@FXML private TextField orderPrice;
	@FXML private ListView<String> orderItems;

	private int currentOrderNumber;
	private StoreOrders orders;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		currentOrderNumber = 0;
	}

	public void setup(StoreOrders mainStore) {
		orders = mainStore;
		showOrder();
		update();
	}

	private void showOrder() {
		Order currentOrder = orders.getList().get(currentOrderNumber);
		orderItems.getItems().clear();
		for(MenuItem item : currentOrder.getList()) {
			orderItems.getItems().add(item.toString());
		}

		DecimalFormat df = new DecimalFormat("###,##0.00");
		orderPrice.setText(df.format(currentOrder.getTotal()));
	}

	public void removeOrder() {
		orders.remove(orders.getList().get(currentOrderNumber));
		if(currentOrderNumber == orders.getList().size()) currentOrderNumber--;
		update();
		showOrder();
	}

	private void update() {
		orderNumber.getItems().clear();
		if(orders.getList().size() == 0) return;
		for(int i = 0; i < orders.getList().size(); i++) {
			orderNumber.getItems().add(i + 1);
		}
		orderNumber.setValue(1);
	}

	public void changeOrder() {
		if(orderNumber.getValue() == null) return;
		currentOrderNumber = orderNumber.getValue() - 1;
		showOrder();
	}

	public void quit() {
		Stage stage = (Stage) orderPrice.getScene().getWindow();
		stage.close();
	}
}
