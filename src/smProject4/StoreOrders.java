package smProject4;

public class StoreOrders implements Customizable {
	//An instance of this class keeps a list of orders placed by the user. This class must implement the Customizable interface above to provide the behavior of adding and removing orders.
	public boolean add(Object obj) {
		return true;
	}

	public boolean remove(Object obj) {
		return false;
	}
}
