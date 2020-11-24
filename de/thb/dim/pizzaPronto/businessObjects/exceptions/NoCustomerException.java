package de.thb.dim.pizzaPronto.businessObjects.exceptions;

public class NoCustomerException extends Exception {

	private static final long serialVersionUID = 4379684360813511592l;
	
	public NoCustomerException() {
		
	}
	
	public NoCustomerException(String message) {
		super(message);
	}
	
}
