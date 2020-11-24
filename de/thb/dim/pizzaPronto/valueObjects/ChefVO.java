package de.thb.dim.pizzaPronto.valueObjects;
import java.awt.Color;
public class ChefVO extends EmployeeVO{

	private Color colorApron;
	
	// Constructor 
	
	public ChefVO(String personnelNo,String lastName, String firstName) 
	{	
		super(personnelNo, lastName, firstName);
		colorApron = Color.WHITE;
		vacationDays = 25;
		salary = 2100;
	}
	
	public ChefVO() 
	{	
		this(null,null,null);		
	}
		
	// Standards Method 
	
	@Override
	public String toString() {
		
		return "\nChef:\n" +super.toString()+"\nApron " + colorApron.toString();
	}
	
	// Setter and Getter 
	
	public void setColorApron(Color colorApron) 
	{
		this.colorApron = colorApron;
	}
	public Color getColorApron() 
	{
		return colorApron;
	}
	
}
