package smProject4;

import java.util.Set;

public class Coffee extends MenuItem implements Customizable {
	private final int SHORT  = 1;
	private final int TALL   = 2;
	private final int GRANDE = 3;
	private final int VENTI  = 4;

	private Set<Toppings> addons;
	private int size;
	private double price;

	public Coffee(int size) {
		this.size = size;
	}

	public boolean add(Object obj) {
		return true;
	}

	public boolean remove(Object obj) {
		return false;
	}
}
