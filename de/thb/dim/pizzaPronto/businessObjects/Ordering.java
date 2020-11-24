package de.thb.dim.pizzaPronto.businessObjects;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoCustomerException;
import de.thb.dim.pizzaPronto.businessObjects.exceptions.NoOrderException;
import de.thb.dim.pizzaPronto.controller.IOrdering;
import de.thb.dim.pizzaPronto.valueObjects.CustomerVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.OrderVO;
import de.thb.dim.pizzaPronto.valueObjects.StateOfOrderVO;
import java.util.Comparator;
import java.time.LocalDate;
public class Ordering implements IOrdering{

	private static MenuVO menu;	
	private static int nextId = 0;
	
	CustomerVO currentCustomer;
	OrderVO currentOrder;
	IService kitchen;
	IService delivery;
	
	public Ordering() {
		
		if(menu == null)
			menu = new MenuVO();
		
		currentOrder = null;
		currentCustomer = null;
		
		kitchen = new Kitchen();
		delivery = new Delivery();
	}
	
	// Methods
	
	public OrderVO startNewOrder(CustomerVO customer) throws NullPointerException{
		if (menu == null)
			menu = new MenuVO();
	
		if (customer == null) {
			throw new NullPointerException("Customer must not be null");
		}else {
			if (nextId == 0 || nextId == 0 || nextId / 100000 < LocalDate.now().getYear()) {
				nextId = (LocalDate.now().getYear() * 100000);
			} else 
				nextId++;
			
			currentOrder = new OrderVO(nextId, StateOfOrderVO.STARTED, LocalDateTime.now(), customer);
			currentCustomer = customer;
			currentCustomer.setOrder(currentOrder);
		}

		return currentOrder;
	}
	
	public void addDish(DishVO dish) throws NoOrderException,IllegalStateException{
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		else if(currentOrder != null && currentOrder.getState()== StateOfOrderVO.STARTED) {
			currentOrder.addDish(dish);
		}
		 
		else if(currentOrder != null && currentOrder.getState()!= StateOfOrderVO.STARTED)
			throw new IllegalStateException("Your order is complete, you can not add any dishes.");
	}
	
	// delete dish
	
	public void deleteDish(DishVO dish)  throws NoOrderException, IllegalStateException{
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		else if(currentOrder != null && currentOrder.getState() == StateOfOrderVO.STARTED) {
			currentOrder.deleteDish(dish);
		}
		
		else if(currentOrder != null && currentOrder.getState() != StateOfOrderVO.STARTED)
			throw new IllegalStateException("Your order is complete, you can not add any dishes.");
	}
	
	// calculate the total price
	
	public float calculateTotalPrice() throws NoOrderException{
		
		float x=0.0f;
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		else if(currentOrder != null && (currentOrder.getState() == StateOfOrderVO.STARTED || currentOrder.getState()== StateOfOrderVO.CONFIRMED)) {
			x= currentOrder.calculatePriceDishes();
		}
		
		return x;
	}
	
	// to confirm the order
	
	public void confirmOrder() throws NoOrderException, IllegalStateException, NoCustomerException {
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		else if (currentOrder != null && currentOrder.getState() == StateOfOrderVO.STARTED) {
			try {
			currentOrder.setState(StateOfOrderVO.CONFIRMED);
			startService();
			}catch(Exception e) {
				System.out.println("Internal error by processing an order: "+e);
			}
		}
		else 
			throw new IllegalStateException("Your order can not be confirmed.");
			
	}
	
	// Start service
	
	public void startService() throws NoOrderException, IllegalStateException, NoCustomerException 
	{	
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		if(currentOrder != null && currentOrder.getState()==StateOfOrderVO.STARTED)
			throw new IllegalStateException("The order can not be processed.");
		
		if(currentOrder != null && currentOrder.getState()==StateOfOrderVO.CONFIRMED) {
			String text = kitchen.startService(currentOrder);
			System.out.println(text+"\n");
		}
			
		if(currentOrder != null && currentOrder.getState()==StateOfOrderVO.READY) {
			String text = delivery.startService(currentOrder);
			System.out.println(text+"\n");
		}
		
		if(currentOrder != null && currentOrder.getState()==StateOfOrderVO.DELIVERED) {
			currentOrder.setTimestampStartedOrder(LocalDateTime.now());
			currentOrder.setState(StateOfOrderVO.FINISHED);
			System.out.println(" Order completed: "+currentOrder.toString());
			currentCustomer.setOrder(null);
		}	
	}
	
	public List<DishVO> sortShoppingBasket() throws NoOrderException{
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		List<DishVO> sortByName = currentOrder.getShoppingBasket();
			
		Collections.sort(sortByName);
		
		return sortByName;

	}
	
	public List<DishVO> sortShoppingBasketByNumber() throws NoOrderException{
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		List<DishVO> sortByNumber = currentOrder.getShoppingBasket();
		
		Collections.sort(sortByNumber, new Comparator<DishVO>() {

			@Override
			public int compare(DishVO o1, DishVO o2) {
				return Integer.compare(o1.getNumberOfDish(), o2.getNumberOfDish());
			}
			
		});
		
		return sortByNumber;	
	}
	
	public List<DishVO> sortShoppingBasketByPrice() throws NoOrderException{
		
		if(currentOrder == null) {
			throw new NoOrderException("There is no order.");
		}
		
		List<DishVO> sortByPrice = currentOrder.getShoppingBasket();
		
		sortByPrice.sort((DishVO x,DishVO y)-> Float.compare( x.getPrice() ,  y.getPrice()));
		
		return sortByPrice;	
	}
	
	/*
	@Override 
	public int compare(Object o1, Object o2) {
		
		if(dish instanceof DishVO) {
			DishVO other= (DishVO) dish;
			
		if(this.number < other.number) {
			return -1;
		}
		if(this.number > other.number) {
			return 1;
		}
		return 0;
		}
		return 0;				
	
	}
	*/
	
	// Setter and Getter 
	
	public CustomerVO getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(CustomerVO currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public OrderVO getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(OrderVO currentOrder) {
		this.currentOrder = currentOrder;
	}

	public IService getKitchen() {
		return kitchen;
	}

	public void setKitchen(IService kitchen) {
		this.kitchen = kitchen;
	}

	public IService getDelivery() {
		return delivery;
	}

	public void setDelivery(IService delivery) {
		this.delivery = delivery;
	}

	public static MenuVO getMenu() {
		return menu;
	}

	public static int getNextId() {
		return nextId;
	}
	
}
