package smProject4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements Initializable {
	StoreOrders database;
	Order currentOrder;

	Stage donutsStage;
	Stage coffeeStage;
	Stage basketStage;
	Stage ordersStage;

	@FXML private Button button;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		donutsStage = new Stage();
		donutsStage.setTitle("Order Donuts");

		coffeeStage = new Stage();
		coffeeStage.setTitle("Order Coffee");

		basketStage = new Stage();
		basketStage.setTitle("Current Order");

		ordersStage = new Stage();
		ordersStage.setTitle("View all Store Orders");

		currentOrder = new Order();
		database = new StoreOrders();

		/*Stage stage = (Stage) button.getScene().getWindow();
		stage.setOnCloseRequest(e -> {
			donutsStage.close();
			coffeeStage.close();
			basketStage.close();
			ordersStage.close();
		});*/
	}

	public void orderDonuts() throws IOException {
		FXMLLoader dddd = new FXMLLoader(getClass().getResource("OrderDonutsView.fxml"));
		Parent donutRoot = dddd.load();
		donutsStage.setScene(new Scene(donutRoot, 600, 400));
		donutsStage.show();
	}

	public void orderCoffee() throws IOException {
		FXMLLoader cccc = new FXMLLoader(getClass().getResource("OrderCoffeeView.fxml"));
		Parent coffeeRoot = cccc.load();
		OrderCoffeeController occ = cccc.getController();
		occ.setMain(this);
		coffeeStage.setScene(new Scene(coffeeRoot, 600, 400));
		coffeeStage.show();
	}

	public void currentOrder() throws IOException {
		Parent basketRoot = FXMLLoader.load(getClass().getResource("BasketView.fxml"));
		basketStage.setScene(new Scene(basketRoot, 600, 400));
		basketStage.show();
	}

	public void storeOrders() throws IOException {
		Parent ordersRoot = FXMLLoader.load(getClass().getResource("OrdersView.fxml"));
		ordersStage.setScene(new Scene(ordersRoot, 600, 400));
		ordersStage.show();
	}

	public void addCoffee(Coffee coffee) {
		currentOrder.add(coffee);
	}
}