package smProject4;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class BasketController implements Initializable {
	@FXML private ListView<String> itemList;
	@FXML private TextField subtotal;
	@FXML private TextField tax;
	@FXML private TextField total;

	private ArrayList<MenuItem> items;

	private double price;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		itemList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		price = 0;
	}

	public void populateList(ArrayList<MenuItem> stuff) {
		items = stuff;
		update();
	}

	public void remove() {
	}

	public void submit() {
	}

	public void update() {
		for(MenuItem item : items) {
			itemList.getItems().add(item.toString());
			price += item.itemPrice();
		}

		DecimalFormat df = new DecimalFormat("###,##0.00");
		subtotal.setText(df.format(price));
	}
}
