package de.thb.dim.pizzaPronto.valueObjects;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.io.Serializable;
import java.text.DecimalFormat;

public abstract class DishVO implements Comparable<DishVO>,Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int number;
	protected String name;
	protected String[] ingredients;
	protected float price;
	
	public DishVO(int number, String name, String[] ingredients, float price) {
		
		setNumber(number);
		setName(name);
		setIngredients(ingredients);
		setPrice(price);
	}
	
	public DishVO(int number, String name, float price) {
		
		this(number,name,null,price);	
	}
	
	public DishVO() {
		
		this(0,null,null,0.0f);	
//		ArrayList<String> ls;
//		ls = this.ingredients;
//		ls.add("ingredient1");
//		ls.add("ingredient2");
//		ls.add("ingredient3");
	}
	
	// Methods
	
	public String ingredientsToString() {
		
		StringBuffer text = new StringBuffer();
		
		if(ingredients != null)
		{
			for(String ingredient: getIngredients()) {
				text.append(ingredient+", ");
			}
			text= new StringBuffer(text.substring(0, text.length()-2));
		}
		return text.toString();
	}
	
	public abstract String getNameOfDish();
		
	public abstract int getNumberOfDish();	
		
	// Standards Methods
	
	@Override
	public int compareTo(DishVO d) {
		
		return this.getNameOfDish().compareTo(d.getNameOfDish());			
	}
	
	/*
	@Override
	public int compareTo(Object o) {
		if(o instanceof Student) {
			Student other = (Student) o;
			
			if(this.age < other.age) {
				return -1;
			}
			if(this.age > other.age) {
				return 1;
			}
			return 0;
		}
		return 0;
	}
	*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + Float.floatToIntBits(price);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DishVO other = (DishVO) obj;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
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
	
	@Override
	public String toString() {
		
		DecimalFormat dformat = new DecimalFormat(".00");
		
		StringBuilder text = new StringBuilder(" ");
		
		text.append(getNumberOfDish()+" - ");
		text.append(getNameOfDish()+"\t");
		
		text.append(ingredientsToString()+"");
		
		text.append("\n\t\tPrice: "+dformat.format(getPrice())+"Euro \n");
		
		return text.toString();
	}
	
	// Setter and Getter 
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getIngredients() {
		return ingredients;
	}
	public void setIngredients(String[] ingredients) {
		this.ingredients = ingredients;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
			
}
