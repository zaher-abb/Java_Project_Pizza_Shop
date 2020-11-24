package de.thb.dim.pizzaPronto.valueObjects;

import java.io.Serializable;

public abstract class PersonVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String lastName;
	protected String firstName;
	protected String street;
	protected int houseNumber;
	
	public PersonVO(String lastName, String firstName, String street, int houseNumber) {
		setFirstName(firstName);
		setLastName(lastName);
		setStreet(street);
		setHouseNumber(houseNumber);
	}
	
	public PersonVO(String lastName, String firstName) {
		setFirstName(firstName);
		setLastName(lastName);
	}	

	public PersonVO() {
		this(null, null);
	}
	
	// Standard Methods
	
	@Override
	public String toString() {
		return "Name: " + lastName +" "+firstName+ "\nstreet:" + street +" "+houseNumber ;
	}
	
	// Setter and Getter 
	
		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public int getHouseNumber() {
			return houseNumber;
		}

		public void setHouseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
		}
	
}
