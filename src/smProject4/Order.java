package smProject4;

public class Order implements Customizable{
	private final int CAKE = 0;
	private final int YEAST = 1;
	private final int HOLE = 2;

	private Coffee[] coffees;
	private Donut[] donuts;

	public boolean add(Object obj) {
		return true;
	}

	public boolean remove(Object obj) {
		return false;
	}
}
