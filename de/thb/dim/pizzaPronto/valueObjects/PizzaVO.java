package de.thb.dim.pizzaPronto.valueObjects;

//import java.util.ArrayList;

public class PizzaVO extends DishVO implements Cloneable {
	
	private int size;
	
	// Constructor 
	
	public PizzaVO(int number, String name, String[] ingredients,float price, int size)
	{	
		super(number,name,ingredients,price);
		setSize(size);
	}
	
//	public PizzaVO(int number, String name, String[] ingredients, float price, int size) {
//		this(0,null,new ArrayList<String>(),0.0f,0);
//		for(int i = 0; i < ingredients.length; i++)
//		{
//			this.ingredients.add(ingredients[i]);
//		}
//	}
	
	public PizzaVO(int nubmer, String name, String[] ingredients,float price) 
	{
		this(0,null,null,0.0f,0);	
	}
	
	public PizzaVO() 
	{
		this(0,null, null, 0.0f);
//		ArrayList<String> ls;
//		ls = this.ingredients;
//		ls.add("ingredient1");
//		ls.add("ingredient2");
//		ls.add("ingredient3");
	}
	
	// Standard Methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PizzaVO other = (PizzaVO) obj;
		if (size != other.size)
			return false;
		return true;
	}
		
	// Methods
	
	@Override
	public String getNameOfDish() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("Pizza ");
		
		if(size == 1)sb.append(getName()+" - Normal");
		else sb.append(getName()+" - Grande");
		
		return sb.toString();
	}
	
	@Override
	public int getNumberOfDish() {	
		return number*10+size;
	}
	
	public Object clone() throws CloneNotSupportedException {
		
		Object other = null;
		
		try {
			other = super.clone();
		} catch(CloneNotSupportedException e){
			throw new InternalError();
		}
		return other;
	}

	// Setter and Getter
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
