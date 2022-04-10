package smProject4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controls all the functions for the current order window.
 * @author Harshkumar Patel
 */
public class OrderDonutsController implements Initializable {
	
	@FXML private ComboBox<String> type;
	@FXML private ComboBox<String> flavor;
	@FXML private ComboBox<Integer> quantity;
	@FXML private ListView<String> orderList;
	@FXML private Label currentPrice;
	@FXML private Label total;
	private double totalPrice;

	DecimalFormat df = new DecimalFormat("0.00");
	private ArrayList<Donut> donutLst = new ArrayList<Donut>();
	private MainController main;

	/**
	 * Initializes the values at sets up the program.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		type.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
		type.setValue("Yeast Donut");
		flavor.getItems().addAll("Glazed","Chocolate","Strawberry","Jelly");
		flavor.setValue("Glazed");
		quantity.getItems().addAll(1,2,3,4,5);
		quantity.setValue(1);
		currentPrice.setText("0.00");
		totalPrice = 0;
	}

	/**
	 * Adds donut(s) to the queue when the button is pressed.
	 */
	public void addDonut() {
		Donut d = new Donut(type.getValue(), flavor.getValue(), quantity.getValue());
		totalPrice += d.price*quantity.getValue();
		total.setText(df.format(totalPrice));
		boolean added = false;
		for(Donut donut : donutLst) {
			if(donut.equals(d)) {
				donut.addDonuts(d);
				added = true;
			}
		}
		if(!added) donutLst.add(d);
		currentPrice.setText(""+d.price);
		update();
	}

	/**
	 * Removes donut(s) from the queue when button is pressed.
	 */
	public void remove() {
		String selection = orderList.getSelectionModel().getSelectedItem();

		for(Donut d : donutLst) {
			if(d.toString().equals(selection)) {
				currentPrice.setText("-"+df.format(d.price));
				donutLst.remove(d);
				totalPrice -= d.price;
				total.setText(df.format(totalPrice));
				break;
			}
		}

		update();
	}

	/**
	 * Updates the list and the price in the GUI.
	 */
	private void update() {
		orderList.getItems().clear();

		for(Donut d : donutLst) {
			orderList.getItems().add(d.toString());
		}

	}

	/**
	 * Retrieves information from the main window.
	 * @param mc The main window object.
	 */
	public void setMain(MainController mc) {
		main = mc;
	}

	/**
	 * Adds the items in the queue to the order.
	 */
	public void addToOrder() {
		main.addDonuts(donutLst);
		Stage stage = (Stage) type.getScene().getWindow();
		stage.close();
	}
}