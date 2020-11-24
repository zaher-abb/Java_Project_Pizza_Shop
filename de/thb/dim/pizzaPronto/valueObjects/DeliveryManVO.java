package de.thb.dim.pizzaPronto.valueObjects;

public class DeliveryManVO extends EmployeeVO {
	
	String driverLicence;
	
	public DeliveryManVO(String personnelNo,String lastName, String firstName) {
		super(personnelNo, lastName, firstName);
		vacationDays = 25;
		salary = 2100;
		driverLicence = "XYZ123";
	}
	
	public DeliveryManVO() {
		this(null,null,null);
	}
	
	// Standards Method
	
	@Override
	public String toString() {
		
		return "\nDeliveryMan:\n"+super.toString();
	}
	
	// Setter and Getter 
	
	public String getDriverLicence() {
		return driverLicence;
	}

	public void setDriverLicence(String driverLicence) {
		this.driverLicence = driverLicence;
	}
}
