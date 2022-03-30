package smProject4;

public class StoreOrders implements Customizable {
	private int max = 4;
	private int numOrds;
	private Order[] orders;

	public StoreOrders() {
		orders = new Order[max];
		numOrds = 0;
	}

	private void grow() {
	}

	public boolean add(Object order) {
		return true;
	}

	public boolean remove(Object order) {
		return false;
	}
}
