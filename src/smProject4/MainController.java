package smProject4;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controls all the functions of the main window and holds the database of all orders.
 * @author Aaron Browne, Harshkumar Patel
 *
 */
public class MainController implements Initializable {
	private StoreOrders database;
	private Order currentOrder;

	private Stage donutsStage;
	private Stage coffeeStage;
	private Stage basketStage;
	private Stage ordersStage;

	private OrdersController ordersController;
	private BasketController basketController;

	@FXML private Pane pane;

	/**
	 * Initializes the values at sets up the program.
	 */
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
	}

	/**
	 * Launches the donut ordering screen when the button is pressed.
	 * @throws IOException
	 */
	public void orderDonuts() throws IOException {
		FXMLLoader donutLoader = new FXMLLoader(getClass().getResource("OrderDonutsView.fxml"));
		Parent donutRoot = donutLoader.load();
		OrderDonutsController odc = donutLoader.getController();
		donutsStage.setScene(new Scene(donutRoot, 600, 450));
		donutsStage.show();
		odc.setMain(this);
	}

	/**
	 * Launches the coffee ordering window when the button is pressed.
	 * @throws IOException
	 */
	public void orderCoffee() throws IOException {
		FXMLLoader coffeeLoader = new FXMLLoader(getClass().getResource("OrderCoffeeView.fxml"));
		Parent coffeeRoot = coffeeLoader.load();
		OrderCoffeeController occ = coffeeLoader.getController();
		coffeeStage.setScene(new Scene(coffeeRoot, 600, 400));
		coffeeStage.show();
		occ.setMain(this);
	}

	/**
	 * Launches the current order window when the button is pressed.
	 * @throws IOException
	 */
	public void currentOrder() throws IOException {
		FXMLLoader basketLoader = new FXMLLoader(getClass().getResource("BasketView.fxml"));
		Parent basketRoot = basketLoader.load();
		basketController = basketLoader.getController();
		basketStage.setScene(new Scene(basketRoot, 600, 400));
		basketStage.show();
		basketController.populateList(currentOrder, this);
	}

	/**
	 * Launches the orders window when the button is pressed.
	 * @throws IOException
	 */
	public void storeOrders() throws IOException {
		FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("OrdersView.fxml"));
		Parent ordersRoot = storeLoader.load();
		ordersController = storeLoader.getController();
		ordersStage.setScene(new Scene(ordersRoot, 600, 450));
		ordersStage.show();
		ordersController.setup(database);
	}

	/**
	 * Adds a list of coffees to the order.
	 * @param coffees The coffees to add.
	 */
	public void addCoffee(ArrayList<Coffee> coffees) {
		for(Coffee coffee : coffees) {
			currentOrder.add(coffee);
		}
		if(basketStage.isShowing())
			basketController.update();
	}

	/**
	 * Adds a list of donuts to the order.
	 * @param donuts The list of donuts to add.
	 */
	public void addDonuts(ArrayList<Donut> donuts) {
		boolean added;
		for(Donut donut : donuts) {
			added = false;
			for(MenuItem i : currentOrder.getList()) {
				try {
					Donut d = (Donut) i;
					if(donut.equals(d)) {
						d.addDonuts(donut);
						added = true;
					}
				} catch(Exception e) {}
			}
			if(!added) currentOrder.add(donut);
		}
		if(basketStage.isShowing())
			basketController.update();
	}

	/**
	 * Adds the current order to the database.
	 */
	public void addOrder() {
		database.add(currentOrder);
		currentOrder = new Order();
		basketController.populateList(currentOrder, this);
		if(ordersStage.isShowing())
			ordersController.update();
	}

	/**
	 * Closes all the other windows when the current window is closed.
	 * @param stage The main orders stage.
	 */
	public void closeListener(Stage stage) {
		stage.setOnCloseRequest(e -> {
			donutsStage.close();
			coffeeStage.close();
			basketStage.close();
			ordersStage.close();
		});
	}
}
