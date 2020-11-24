package de.thb.dim.pizzaPronto.businessObjects;
import java.util.Random;
import java.time.LocalDateTime;

import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DeliveryManVO;
import de.thb.dim.pizzaPronto.valueObjects.EmployeeVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;


public class Delivery implements IService{

	private EmployeeVO[] employees;
	
	public Delivery() {
		
		employees = new EmployeeVO[2];
		
		employees[0]= new DeliveryManVO("Lieferant01", "Rasender", "Rudi");
		employees[1]= new DeliveryManVO("Lieferant02", "Lacy", "Lutz");		
	}
	
	// Methods
	
	public String startService(OrderVO order) throws NoCustomerException, NullPointerException, IllegalStateException {
		
		StringBuilder text = new StringBuilder();
		EmployeeVO employee = selectEmployee();
		CustomerVO customer;
		
		if(order == null) {
			throw new NullPointerException("No order available.");
//			text.append(String.format(" Service of DeliveryManVO %s: No order available. ", employee.getPersonnelNo()));
		} 
		
		else {
			customer= order.getCustomer();
		
			if(customer == null) {
				throw new NoCustomerException("No customer available.");
//				text.append(String.format(" Service of DeliveryManVO %s: No customer available. ", employee.getPersonnelNo()));
			} 
			else {
		
				if(order.getState() == StateOfOrderVO.READY) {
					
					order.setState(StateOfOrderVO.DELIVERED);
					text.append(String.format(" Drive to customer %s %s, in %s %s\n",
							customer.getLastName(), customer.getFirstName(), customer.getStreet(), customer.getHouseNumber()));
					
					text.append(String.format("\nService of DeliveryManVO %s", employee.getPersonnelNo()));
					text.append(String.format("Order is delivered on %1$tm/%1$td/%1$tY at %1$tH:%1$tM o'clock",
							 LocalDateTime.now()));
			}
				else {
					throw new IllegalStateException("No order is ready for processing.");
//					text.append(String.format(" Service of DeliveryManVO %s: No order is ready for processing.", employee.getPersonnelNo()));            		
				}
			}
		}
		return text.toString();
	}
	
	private EmployeeVO selectEmployee() {
		Random zufall= new Random();
			
		return employees[zufall.nextInt(employees.length-1)];
	}
	
	// Setter and Getter
	
	public EmployeeVO[] getEmployees() {
		return employees;
	}

	public void setEmployees(EmployeeVO[] employees) {
		this.employees = employees;
	}
	
}
