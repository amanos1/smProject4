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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderDonutsController implements Initializable {
	
	@FXML private ComboBox<String> type;
	@FXML private Label currentPrice;
	@FXML private TextArea items;
	@FXML private ComboBox<Integer> quantity;
	
	private ArrayList<Donut> donutLst = new ArrayList<Donut>();
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{
		type.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
		type.setValue("Yeast Donut");
		quantity.getItems().addAll(1,2,3,4,5);
		quantity.setValue(1);
	}
	
	public void addToOrder() 
	{
		Donut d = new Donut(type.getValue(),quantity.getValue());
		donutLst.add(d);
		currentPrice.setText(""+d.getPrice());
		items.appendText("\n"+d);
	}
}