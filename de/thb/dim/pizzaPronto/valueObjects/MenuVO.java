package de.thb.dim.pizzaPronto.valueObjects;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MenuVO {
	
	private List<DishVO> dishes;
	
	public MenuVO() {	
		initMenu();
	}
	
	public MenuVO(ArrayList<DishVO> dishes) {
		this.dishes = dishes;
	}
	
	public MenuVO(LinkedList<DishVO> gerichte) {
		this(new ArrayList<DishVO>());
	}

	private void initMenu(){
		
		this.dishes = new ArrayList<DishVO>();
		
		dishes.add(new PizzaVO(30, "Popeye", new String[] {"Schinken","Spinat", "Champignon", "Ei" }, 7.00f, 1));
		dishes.add(new PizzaVO(30, "Popeye", new String[] { "Schinken","Spinat", "Champignon", "Ei" }, 8.90f, 2));
		dishes.add(new PizzaVO(31, "Hawaii", new String[] { "Schinken","Ananas", "Curry" }, 5.80f, 1));
		dishes.add(new PizzaVO(31, "Hawaii", new String[] { "Schinken","Ananas", "Curry" }, 7.40f, 1));
		dishes.add(new PizzaVO(32, "Prima", new String[] { "Schinken","Salami", "Zwiebeln" }, 7.40f, 1));
		dishes.add(new PizzaVO(32, "Prima", new String[] { "Schinken","Salami", "Zwiebeln" }, 8.90f, 1));
		
		// hier folgen die weiteren Gerichte
		
		dishes.add(new PastaVO(11, "Napoli", new String[] {"Tomatensauce"}, 5.60f, 4));
		dishes.add(new PastaVO(11, "Napoli", new String[] {"Tomatensauce"}, 5.60f, 5));
		dishes.add(new PastaVO(11, "Napoli", new String[] {"Tomatensauce"}, 5.60f, 6));
		dishes.add(new PastaVO(12, "Bolognese",new String[] { "Hackfleischsauce" }, 6.40f, 4));
		dishes.add(new PastaVO(12, "Bolognese",new String[] { "Hackfleischsauce" }, 6.40f, 5));
		dishes.add(new PastaVO(12, "Bolognese",new String[] { "Hackfleischsauce" }, 6.40f, 6));
		dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 4));
		dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 5));
		dishes.add(new PastaVO(13, "alla Panna", new String[] { "Schinken","Sahne" }, 6.40f, 6));
		dishes.add(new DessertVO(21, "Hausgemachter Obstsalat", 2.30f));
		dishes.add(new DessertVO(22, "Hausgemachte Pannacotta", 2.60f));
		dishes.add(new DessertVO(23, "Hausgemachtes Tiramisu", 2.80f));
		
	}
	
	public String toString() {
		
		//int i=0;
		StringBuilder sb = new StringBuilder();
		DecimalFormat dFormat = new DecimalFormat(".00"); // Format the price
		
		sb.append("MENU PIZZA PRONTO\n\n");
		
		// Pizzas
		
		sb.append("Prima special pizzas: \n1 normal1 (Diameter approx. 26 cm) and \n2 grande (Diameter approx. 32 cm)\n");
	
		for(DishVO dish: dishes) {
			if(dish instanceof PizzaVO) 

				sb.append(dish.getNumber() + "\t");
				sb.append(dish.getName() + "\t");
				sb.append(dish.ingredientsToString());
				sb.append("\n\tPreis:\t\t\t"
						+ dFormat.format(dish.getPrice()) + " Euro");
			}
		
		// Pasta
		
		sb.append("Prima special pastas:\n\t4 Spaghetti\n\t5 Tortellini\n\t6 Gnocchi\n");
		
		for(DishVO dish: dishes) {
			if(dish instanceof PastaVO)
				sb.append(" " + dish.getNumber() + "\t");
			sb.append(dish.getName() + "\t");

			sb.append(dish.ingredientsToString());

			sb.append("\n\tPreis:\t\t\t"
					+ dFormat.format(dish.getPrice()) + " Euro");
			
			sb.append("\n");
		}
		
		// desserts
				sb.append("\nPrima desserts:\n");
		
		for(DishVO dish: dishes) {
			if(dish instanceof DessertVO)
				sb.append(dish.getNumber() + "\t");
				sb.append(dish.getName() + "\t");

				sb.append(dish.ingredientsToString());

				sb.append("\n\tPreis:\t\t\t"
					+ dFormat.format(dish.getPrice()) + " Euro");
				sb.append("\n");
		}
		
		return sb.toString();
		
	/*	
		do{
			if(dishes.get(i)!=null){
				sb.append(dishes[i].getNumber()+"\t");
				sb.append(dishes[i].getName()+"\t");
				sb.append(dishes[i].ingredientsToString());	
				sb.append("\n\tPrice:\t\t\t"+dFormat.format(dishes[i].getPrice())+" Euro");
				if(dishes[i].getNumber() == dishes[i+1].getNumber()){
					sb.append("\n\tprice:\t\t\t"+dFormat.format(dishes[i+1].getPrice())+" Euro");
					sb.append("\n");
					i+=2;
				} else
					i++;		
			}
		}while (i<dishes.length && dishes[i] instanceof PizzaVO);
		
		// Pasta
		
		sb.append("Prima special pastas:\n\t4 Spaghetti\n\t5 Tortellini\n\t6 Gnocchi\n");
		
		do{
			
			if(dishes[i]!=null) {
				sb.append(dishes[i].getNumber()+"\t");
				sb.append(dishes[i].getName()+"\t");
				sb.append(dishes[i].ingredientsToString());
				sb.append("\n\tPrice:\t\t\t"+dFormat.format(dishes[i].getPrice())+" Euro\n");
				if(dishes[i].getNumber() == dishes[i+1].getNumber() && dishes[i].getNumber() == dishes[i+2].getNumber())
					i+=3;
			}
		}while (i<dishes.length && dishes[i] instanceof PastaVO);
		
		// desserts
		sb.append("\nPrima desserts:\n");
		
		do{
			if(dishes[i]!=null) {				
				sb.append(dishes[i].getNumber()+"\t");
				sb.append(dishes[i].getName()+"\t");
				sb.append(dishes[i].ingredientsToString());
				sb.append("\n\tPrice:\t\t\t"+dFormat.format(dishes[i].getPrice())+" Euro\n");	
			}
			i++;
		}while (i<dishes.length && dishes[i] instanceof DessertVO);
	*/	
		
	}
	
	// Setter and Getter 
	
	public DishVO getDish(int index) {
		
		 if(dishes.size() != 0 && index <= dishes.size())
			 return dishes.get(index);
		 else
			 return null;
	}
	
	public int getNumberOfDishes() {
		
		return  dishes.size();
	}
	
	
}
