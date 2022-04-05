package smProject4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Coffee extends MenuItem implements Customizable {
	private final int SHORT  = 1;
	private final int TALL   = 2;
	private final int GRANDE = 3;
	private final int VENTI  = 4;

	private final double DEFAULT_COST = 1.69;

	private Set<Topping> addons;
	private int size;

	public Coffee() {
		this.size = SHORT;
		this.price = DEFAULT_COST;
		this.addons = new HashSet<Topping>();
	}

	public boolean add(Object obj) {
		Topping t = (Topping) obj;
		addons.add(t);
		updatePrice();
		return true;
	}

	public boolean remove(Object obj) {
		Topping t = (Topping) obj;
		addons.remove(t);
		updatePrice();
		return true;
	}

	public void changeSize(int newSize) {
		if(newSize < SHORT || newSize > VENTI) return;
		size = newSize;
		updatePrice();
	}

	private void updatePrice() {
		double newPrice = 1.69;
		newPrice += (size - SHORT) * 0.4;
		newPrice += addons.size() * 0.3;
		price = newPrice;
	}

	@Override
	public String toString() {
		String result = "";
		switch(size) {
		case SHORT:
			result += "Short ";
			break;
		case TALL:
			result += "Tall ";
			break;
		case GRANDE:
			result += "Grande ";
			break;
		case VENTI:
			result += "Venti ";
			break;
		}

		result += "coffe with " + addons.size() + " toppings: ";
		Iterator<Topping> i = addons.iterator();
		while(i.hasNext()) {
			result += i + ", ";
		}
		return result;
	}
}
