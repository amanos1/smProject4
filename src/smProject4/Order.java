package smProject4;

import java.util.ArrayList;

public class Order implements Customizable{
	private double TAX_RATE = 0.06625;
	private ArrayList<MenuItem> items;

	private double price;
	private double tax;
	private double total;

	public Order() {
		price = 0;
		items = new ArrayList<MenuItem>();
	}

	public boolean add(Object obj) {
		MenuItem mi = (MenuItem) obj;
		items.add(mi);
		changePrice(mi.itemPrice());
		return true;
	}

	public boolean remove(Object obj) {
		String toBeRemoved = (String) obj;
		for(MenuItem item : items) {
			if(item.toString().equals(toBeRemoved)) {
				changePrice(item.itemPrice() * -1);
				items.remove(item);
				break;
			}
		}
		return false;
	}

	public void changePrice(double amount) {
		price += amount;
		tax = price * TAX_RATE;
		total = price + tax;
	}

	public ArrayList<MenuItem> getList() {
		return items;
	}

	public double orderPrice() {
		return price;
	}

	public double getTax() {
		return tax;
	}

	public double getTotal() {
		return total;
	}
}
