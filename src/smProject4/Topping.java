package smProject4;

public enum Topping {
	CREAM("Cream"), SYRUP("Syrup"), MILK("Milk"), CARAMEL("Caramel"), WHIPPED_CREAM("Whipped Cream");

	String string;
	private Topping(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}
