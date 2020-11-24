package de.thb.dim.pizzaPronto.valueObjects;
import java.io.Serializable;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
//import java.text.DecimalFormat;
//import java.util.Arrays;
public class OrderVO implements Serializable{
	
	//private static final int MAX_DISHES= 10;
	//private int index;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderNo;
	private StateOfOrderVO state;
	
	private LocalDateTime timestampStartedOrder;
	private LocalDateTime timestampDeliveredOrder;
	private LinkedList<DishVO> shoppingBasket;
	private CustomerVO customer;
	
		
	public OrderVO(int orderNo, StateOfOrderVO state,LocalDateTime order, CustomerVO customer) {
	
		this.orderNo = orderNo;
		setTimestampStartedOrder(order);
		setCustomer(customer);
		this.setState(state);
		shoppingBasket = new LinkedList<DishVO>();			
	}
	
	// Methods
	
	public void addDish(DishVO dish) {
			
		shoppingBasket.add(dish);	
	}
	
	// Delete Dish
	
	public void deleteDish(DishVO dish) {
		
		if(shoppingBasket.contains(dish)) {
			shoppingBasket.remove(dish);
		}
		// Frage, ob es richtig oder falsh
//		else if (shoppingBasket.size() == 0)
//			shoppingBasket.add(null);
	}
	
	// Calculate the Price of Dishes
	
	public float calculatePriceDishes() {
		
		float total=0.0f;
		
		{
		for(int i=0;i<shoppingBasket.size();i++) {	
			
			//if(shoppingBasket[i]!=null)
				total+=shoppingBasket.get(i).getPrice();			
		}	
		}	
		return total;
	}
	
	// Get the dish
	
	public DishVO getDish(int index) {
		if(index>=0 && index<= shoppingBasket.size() && shoppingBasket.get(index)!=null)
			return shoppingBasket.get(index);
		else 
			return null;
	}
	
	// Get the number of dishes
	
	public int getNumberOfDishes() {		
		return shoppingBasket.size();
	}
	
	// Standard Methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + orderNo;
		result = prime * result + ((shoppingBasket == null) ? 0 : shoppingBasket.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((timestampDeliveredOrder == null) ? 0 : timestampDeliveredOrder.hashCode());
		result = prime * result + ((timestampStartedOrder == null) ? 0 : timestampStartedOrder.hashCode());
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
		OrderVO other = (OrderVO) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderNo != other.orderNo)
			return false;
		if (shoppingBasket == null) {
			if (other.shoppingBasket != null)
				return false;
		} else if (!shoppingBasket.equals(other.shoppingBasket))
			return false;
		if (state != other.state)
			return false;
		if (timestampDeliveredOrder == null) {
			if (other.timestampDeliveredOrder != null)
				return false;
		} else if (!timestampDeliveredOrder.equals(other.timestampDeliveredOrder))
			return false;
		if (timestampStartedOrder == null) {
			if (other.timestampStartedOrder != null)
				return false;
		} else if (!timestampStartedOrder.equals(other.timestampStartedOrder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		//DecimalFormat dFormat = new DecimalFormat(".00");
		
		StringBuilder text = new StringBuilder(String.format("OrderVO "+ getOrderNo() + " from %1$tm/%1$td/%1$tY %1$tH:%1$tM with delivery at  %2$tm/%2$td/%2$tY %2$tH:%2$tM\n", 
				timestampStartedOrder, timestampDeliveredOrder));
		
		text.append(" Of customer "+customer.getLastName()+" "+customer.getFirstName()+" ID of customer: "+customer.getId()+"\n");
		
		for(int i=0;i<shoppingBasket.size();i++) {
			
			if(shoppingBasket.get(i) != null)
				text.append(shoppingBasket.get(i).toString()+"");
				text.append("\n");
		}		
		
		//text.append("\n\n The total Price:\t\t\t"+dFormat.format(calculatePriceDishes())+" Euro");
		
		return text.toString();
	}
	
	// Setter and Getter

	public int getOrderNo() {
		return orderNo;
	}

	public LocalDateTime getTimestampStartedOrder() {
		return timestampStartedOrder;
	}

	public void setTimestampStartedOrder(LocalDateTime timestampStartedOrder) {
		this.timestampStartedOrder = timestampStartedOrder;
	}

	public LocalDateTime getTimestampDeliveredOrder() {
		return timestampDeliveredOrder;
	}

	public void setTimestampDeliveredOrder(LocalDateTime timestampDeliveredOrder) {
		this.timestampDeliveredOrder = timestampDeliveredOrder;
	}
	
	public LinkedList<DishVO> getShoppingBasket() {
		return shoppingBasket;
	}

	public void setShoppingBasket(LinkedList<DishVO> shoppingBasket) {
		this.shoppingBasket = shoppingBasket;
	}

	public CustomerVO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVO customer) {
		if(customer!=null)
		this.customer = customer;
	}
	
	public StateOfOrderVO getState() {
		return state;
	}

	public void setState(StateOfOrderVO state) {
		this.state = state;
	}
	
}
