package smProject4;

import java.util.ArrayList;

public class Order implements Customizable{
	private ArrayList<MenuItem> items;

	private double price;

	public Order() {
		price = 0;
		items = new ArrayList<MenuItem>();
	}

	public boolean add(Object obj) {
		MenuItem mi = (MenuItem) obj;
		items.add(mi);
		price += mi.itemPrice();
		return true;
	}

	public boolean remove(Object obj) {
		MenuItem mi = (MenuItem) obj;
		items.remove(mi);
		price -= mi.itemPrice();
		return false;
	}

	public double orderPrice() {
		return price;
	}
}
