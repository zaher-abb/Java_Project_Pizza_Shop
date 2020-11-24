package de.thb.dim.pizzaPronto.valueObjects;

public abstract class EmployeeVO extends PersonVO {

	protected String personnelNo;
	protected float salary;
	protected int vacationDays;
	
	// Constructor 
	
	public EmployeeVO(String personnelNo,String lastName, String firstName) {
		super(lastName,firstName);
		setPersonnelNo(personnelNo);
	}
	
	public EmployeeVO() {
		this(null,null,null);
	}

	// Standards Methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personnelNo == null) ? 0 : personnelNo.hashCode());
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
		EmployeeVO other = (EmployeeVO) obj;
		if (personnelNo == null) {
			if (other.personnelNo != null)
				return false;
		} else if (!personnelNo.equals(other.personnelNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return personnelNo+" "+super.toString()+ "\nsalary: " + salary + "\nNumber of vacation days: " + vacationDays;
	}
	
	// Setter and Getter

	public String getPersonnelNo() {
		return personnelNo;
	}
	
	public void setPersonnelNo(String personnelNo) {
		this.personnelNo = personnelNo;
	}
		
}
