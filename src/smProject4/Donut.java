package smProject4;

import java.text.DecimalFormat;

public class Donut extends MenuItem {
	private final double YEAST_DONUT_PRICE = 1.59;
	private final double CAKE_DONUT_PRICE = 1.79;
	private final double DONUT_HOLE_PRICE = 0.39;
	private int quantity;
	private String type;
	DecimalFormat df = new DecimalFormat("#.##");
	
	public Donut() 
	{
		this.type = "Yeast Donut";
		super.price = YEAST_DONUT_PRICE;
		this.quantity = 1;
	}
	
	public Donut(String t, int q) 
	{
		this.type = t;
		this.quantity = q;
		if(type.equals("Yeast Donut")) 
		{
			super.price = (YEAST_DONUT_PRICE*quantity);
		}else if(type.equals("Cake Donut")) 
		{
			super.price = (CAKE_DONUT_PRICE*quantity);
		}else 
		{
			super.price = (DONUT_HOLE_PRICE*quantity);
		}
	}
	
	public double getPrice() 
	{
		super.price = Double.parseDouble(df.format(super.price)); 
		return super.price;
	}
	
	@Override
	public String toString() 
	{
		String output = this.type+" ("+this.quantity+")";
		return output;
	}
}
