package de.thb.dim.pizzaPronto.valueObjects;

public enum Gender {

	M(1),F(2),I(3),U(4);
	
	private final int number;
		
	private Gender(int number) {
		
		this.number=number;
	}
		
	// Methods
	
	public int toNumber() {
	
		return number;
	}
	
	public String toString() {
		switch(number){
			case 1: return "male"; 
			case 2: return "female";
			case 3: return "intersex";
		}
		return "unknown";	

	}
		
}
	
	
	
	

