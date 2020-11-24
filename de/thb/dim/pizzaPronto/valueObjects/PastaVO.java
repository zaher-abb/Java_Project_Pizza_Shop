package de.thb.dim.pizzaPronto.valueObjects;

//import java.util.ArrayList;

public class PastaVO extends DishVO implements Cloneable{

	private int typeOfPasta;
	
	public PastaVO(int number, String name, String[] ingredients, float price,int pastaType){
		super(number,name,ingredients,price);
		setTypeOfPasta(pastaType);
	}
	
//	public PastaVO(int number, String name, String[] ingredients, float price, int pastaType) {
//		this(0,null,new ArrayList<String>(),0.0f,0);
//		for(int i = 0; i < ingredients.length; i++)
//		{
//			this.ingredients.add(ingredients[i]);
//		}
//	}
	
	public PastaVO(){
		this(0,null,null,0.0f,0);
	}
	
	// Standards Methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + typeOfPasta;
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
		PastaVO other = (PastaVO) obj;
		if (typeOfPasta != other.typeOfPasta)
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
	
	// abstract Methods
	
	public String getNameOfDish() {
		
		StringBuffer sb = new StringBuffer();
		sb.append("Pasta ");
		
		switch(typeOfPasta) {
			case 4 : 
				sb.append(getName() + " - Spaghetti");
				break;
			case 5 : 
				sb.append(getName() + " - Tortellini");
				break;
			case 6 : 
				sb.append(getName() + " - Gnocchi");
				break;
			default : 
				sb.append(getName());
				break;
		}
		
		return sb.toString();
		
	}
		
	public int getNumberOfDish() {		
		return this.typeOfPasta*100+this.number;
	}
	
	// Setter and Getter 
	
	public int getTypeOfPasta() {
		return typeOfPasta;
	}

	public void setTypeOfPasta(int typeOfPasta) {
		this.typeOfPasta = typeOfPasta;
	}
	
}
