package smProject4;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements Initializable {
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

		/*Stage stage = (Stage) button.getScene().getWindow();
		stage.setOnCloseRequest(e -> {
			donutsStage.close();
			coffeeStage.close();
			basketStage.close();
			ordersStage.close();
		});*/
	}

	public void orderDonuts() throws IOException {
		Parent donutRoot = FXMLLoader.load(getClass().getResource("OrderDonutsView.fxml"));
		donutsStage.setScene(new Scene(donutRoot, 600, 400));
		donutsStage.show();
	}

	public void orderCoffee() throws IOException{
		Parent coffeeRoot = FXMLLoader.load(getClass().getResource("OrderCoffeeView.fxml"));
		coffeeStage.setScene(new Scene(coffeeRoot, 600, 400));
		coffeeStage.show();
	}

	public void currentOrder() throws IOException{
		Parent basketRoot = FXMLLoader.load(getClass().getResource("BasketView.fxml"));
		basketStage.setScene(new Scene(basketRoot, 600, 400));
		basketStage.show();
	}

	public void storeOrders() throws IOException {
		Parent ordersRoot = FXMLLoader.load(getClass().getResource("OrdersView.fxml"));
		ordersStage.setScene(new Scene(ordersRoot, 600, 400));
		ordersStage.show();
	}
}