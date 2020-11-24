package de.thb.dim.pizzaPronto.businessObjects;
import de.thb.dim.pizzaPronto.valueObjects.ChefVO;
import de.thb.dim.pizzaPronto.valueObjects.EmployeeVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;

public class Kitchen implements IService {
	
	EmployeeVO[] employees;
	
	public Kitchen() {
		
		employees = new EmployeeVO[1];
		
		employees[0]= new ChefVO("Koch5Sterne", "Bocuse", "Bruno");		
	}
	
	public String startService(OrderVO order) throws NullPointerException, IllegalStateException {
		
		StringBuilder text;
		
		if(order == null) {
			throw new NullPointerException("No order available.");
//			text = new StringBuilder(String.format(" Service of ChefVO %s: No order available. ", employees[0].getPersonnelNo()));
		}		
		else if (order.getState() == StateOfOrderVO.CONFIRMED) {
			order.setState(StateOfOrderVO.READY);
			text = new StringBuilder(String.format(" Service of ChefVO %s: Order is ready. ", employees[0].getPersonnelNo()));	
		}
		else 
		{
			throw new IllegalStateException("No order for processing available.");
//			text = new StringBuilder(String.format(" Service of ChefVO %s: No order for processing available. ", employees[0].getPersonnelNo()));		
		}
		return text.toString();
	}
	
	// Setter and Getter 
	
	public EmployeeVO[] getEmployees() {
		return employees;
	}
	
	public void setEmployees(EmployeeVO[] employees) {
		this.employees = employees;
	}
}
