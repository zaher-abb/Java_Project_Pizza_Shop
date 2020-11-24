package de.thb.dim.pizzaPronto.businessObjects;

import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;

public interface IService {

	String startService(OrderVO order) throws NoCustomerException, NullPointerException, IllegalStateException;
	
}
