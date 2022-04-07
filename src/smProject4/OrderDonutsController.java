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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderDonutsController implements Initializable {
	
	@FXML private ComboBox<String> type;
	@FXML private ComboBox<String> flavor;
	@FXML private ComboBox<Integer> quantity;
	@FXML private ListView<String> orderList;
	@FXML private Label currentPrice;
	@FXML private Label total;
	
	private double totalPrice;
	DecimalFormat df = new DecimalFormat("#.##");
	private ArrayList<Donut> donutLst = new ArrayList<Donut>();
	private MainController main;
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) 
	{
		type.getItems().addAll("Yeast Donut", "Cake Donut", "Donut Hole");
		type.setValue("Yeast Donut");
		flavor.getItems().addAll("Glazed","Chocolate","Strawberry","Jelly");
		flavor.setValue("Glazed");
		quantity.getItems().addAll(1,2,3,4,5);
		quantity.setValue(1);
		totalPrice = 0;
		currentPrice.setText("0.00");
		total.setText("0.00");
	}
	
	public void addDonut() 
	{
		Donut d = new Donut(type.getValue(),flavor.getValue(),quantity.getValue());
		donutLst.add(d);
		totalPrice += d.getPrice();
		orderList.getItems().add(d.toString());
		currentPrice.setText(""+d.getPrice());
		total.setText(df.format(totalPrice));
		//items.appendText("\n"+d);
	}
	
	public void remove() 
	{
		final int MAX_REMOVE = 0;
		int r = 0;
		String selection = orderList.getSelectionModel().getSelectedItem();
		orderList.getItems().clear();
		for(int i = 0; i<donutLst.size(); i++) 
		{
			if(donutLst.get(i).toString().equals(selection) && r == MAX_REMOVE) 
			{
				totalPrice -= donutLst.get(i).getPrice();
				currentPrice.setText("-"+donutLst.get(i).getPrice());
				total.setText(df.format(totalPrice));
				donutLst.remove(i);
				r = 1;
			}else 
			{
				orderList.getItems().add(donutLst.get(i).toString());
			}
		}
		
	}
	
	public void setMain(MainController mc) {
		main = mc;
	}
	
	public void addToOrder() 
	{
		//ArrayList<Coffee> coffees = new ArrayList<Coffee>();
		//for(int i = 0; i < donutLst.size(); i++) coffees.add(thisCoffee);
		main.addDonuts(donutLst);
		Stage stage = (Stage) type.getScene().getWindow();
		stage.close();
	}
}