package smProject4;

public class Order implements Customizable{
	private final int CAKE = 0;
	private final int YEAST = 1;
	private final int HOLE = 2;

	private Coffee[] coffees;

	//since two types of donuts are the same thing, each index will contain the amount of each type of donut	
	//for example, donuts[0] is the amount of cake donuts, donuts[1] is the amount of yeast donuts
	private int[] donuts;

	public boolean add(Object obj) {
		return true;
	}

	public boolean remove(Object obj) {
		return false;
	}
}
