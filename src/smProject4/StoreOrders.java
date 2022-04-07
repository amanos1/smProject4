package smProject4;

import java.util.ArrayList;

public class StoreOrders implements Customizable {
	private ArrayList<Order> orders;

	public StoreOrders() {
		orders = new ArrayList<Order>();
	}

	public boolean add(Object order) {
		Order ord = (Order) order;
		orders.add(ord);
		return true;
	}

	public boolean remove(Object order) {
		Order ord = (Order) order;
		orders.remove(ord);
		return false;
	}

	public ArrayList<Order> getList() {
		return orders;
	}
}
