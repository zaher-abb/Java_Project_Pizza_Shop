package de.thb.dim.pizzaPronto.valueObjects;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerNoDateOfBirthException;
import de.thb.dim.pizzaPronto.valueObjects.exceptions.CustomerTooYoungException;
public class CustomerVO extends PersonVO {
	
	private static int nextId =0;
	private int id;
	private Gender gender;
	private LocalDate dateOfBirth;
	private OrderVO order;
	
	// Constructor
	
	public CustomerVO(String lastName, String firstName,String street, int houseNubmer,Gender gender, LocalDate dateOfBirth) throws NullPointerException, CustomerTooYoungException, CustomerNoDateOfBirthException 
	{
		super(lastName,firstName,street,houseNubmer);
		id=nextId;
		nextId++;
		setGender(gender);
		setDateOfBirth(dateOfBirth);
		setOrder(order);
	}
		
	public CustomerVO(String lastName, String firstName, LocalDate dateOfBirth) throws NullPointerException, CustomerTooYoungException, CustomerNoDateOfBirthException
	{
		this(lastName,firstName,null,0,null,dateOfBirth);
	}
	
	// Methods
	
	public short calculateAge() throws CustomerNoDateOfBirthException{
		
		short age =-1;
		Period period;
		LocalDate today= LocalDate.now();
		
		if(this.dateOfBirth == null) {
			throw new CustomerNoDateOfBirthException("Internal error: No date of birth.");
			
		}	
			period = Period.between(dateOfBirth,today);		
			age = (short) period.getYears();
				
		return age ;
	}
	
	public boolean hasOrder() {
		if(order!=null)
			return true;
		else 
			return false;	
	}
		
	// Standard Methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		CustomerVO other = (CustomerVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() throws NullPointerException {	
		
		if(toString()==null)
			throw new NullPointerException("toString() must not be null");
		
		try {
			return "Customer: "+"\nid: "+id+"\n"+super.toString()+"\ngender: "+gender
					+ "\ndate of birth: " + this.getDateOfBirth()+"\nAge: "+calculateAge()+"has a current order: "+hasOrder();
		} catch (CustomerNoDateOfBirthException e) {
			System.err.println(e.getMessage());
			return "Date Of Birthday missed";
		}		
	}
		
	private String dobToString() throws CustomerNoDateOfBirthException
	{	
		if(dateOfBirth == null) {
			throw new CustomerNoDateOfBirthException("Internal error: No date of birth.");
		}

		return  dateOfBirth.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
	}
	
	
	// Setter and Getter
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public Gender getGender()
	{
		return gender;
	}
	
	/**
	* @param dob
	* @throws CustomerTooYoungException, customer must be older than 17
	* @throws NullPointerException, dob must not be null
	 * @throws CustomerNoDateOfBirthException 
	*/
	
	public void setDateOfBirth(LocalDate dateOfBirth) throws NullPointerException, CustomerTooYoungException, CustomerNoDateOfBirthException
	{
		this.dateOfBirth = dateOfBirth;
		
		if(dateOfBirth == null)
			throw new NullPointerException("dob must not be null");
		
		if(calculateAge() < 18)	{
			this.dateOfBirth = null;
			throw new CustomerTooYoungException("Customer is not an adult.");
		}
		
	}
	
	public LocalDate getDateOfBirth()
	{
		return dateOfBirth;
	}
	
	public int getId()
	{
		return id;
	}
	
	public static int getNextId()
	{
		return nextId;
	}
	
	public OrderVO getOrder() {
		return order;
	}

	public void setOrder(OrderVO order) {
		this.order = order;
	}
	
}