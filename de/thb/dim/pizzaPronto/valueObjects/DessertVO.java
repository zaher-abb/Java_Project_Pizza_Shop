package de.thb.dim.pizzaPronto.valueObjects;

public class DessertVO extends DishVO implements Cloneable{
	
	
	public DessertVO(int number, String name,float price){
		super(number,name,price);
	}
	
	public DessertVO(){
		this(0, "DummyDessert",0.00f);
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
	
//	public DessertVO clone() {
//	
//		DessertVO dessert;
//		dessert = new DessertVO(this.number,this.name,this.price);
//		return dessert;
//	}
		
	
	// abstract Methods
	
	@Override
	public String getNameOfDish() {
		return this.name;
	}
	
	@Override
	public int getNumberOfDish() {	
		return this.number;
	}
	
}
