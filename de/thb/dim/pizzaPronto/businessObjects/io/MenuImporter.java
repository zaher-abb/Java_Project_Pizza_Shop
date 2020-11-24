package de.thb.dim.pizzaPronto.businessObjects.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import de.thb.dim.pizzaPronto.valueObjects.DessertVO;
import de.thb.dim.pizzaPronto.valueObjects.DishVO;
import de.thb.dim.pizzaPronto.valueObjects.MenuVO;
import de.thb.dim.pizzaPronto.valueObjects.PastaVO;
import de.thb.dim.pizzaPronto.valueObjects.PizzaVO;

public class MenuImporter {
	

public  MenuImporter() {
		
	}

	public 	MenuVO readMenu(String fileName) {
		
		String line;
		BufferedReader textFile=null;
		LinkedList<DishVO>gerichte =new LinkedList<DishVO>();
		DishVO aktSpeise = null;
		try {
			textFile =new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String>zutaten=new ArrayList<String>();		

		try {
			while ((line = textFile.readLine()) != null){
				String[] words = line.split(":");

				if (words[0].equals("dish.type")){
					if (words[1].equals("Pizza")){
						aktSpeise = new PizzaVO();	
					}
				}
				if (words[0].equals("dish.name")){
					aktSpeise.setName(words[1]);

				}

				if (words[0].equals("dish.nr")){
					aktSpeise.setNumber(Integer.valueOf(words[1]));;	
				}
				if (words[0].equals("dish.size")){

					PizzaVO test = (PizzaVO)aktSpeise;
					test.setSize(Integer.valueOf(words[1]));
					aktSpeise = test;

				}if (words[0].equals("dish.price")){
					aktSpeise.setPrice(Float.valueOf(words[1]));
				}

				if (words[0].equals("dish.incredient")){
					zutaten.add(words[1]);

				}
				if(words[0].equals("\n")) {
					aktSpeise.setIngredients((String[])zutaten.toArray());
					gerichte.add(aktSpeise);
					zutaten=null;
				}
				if (words[0].equals("dish.type")){
					if (words[1].equals("Pasta")){
						aktSpeise = new PastaVO();	
					}
					if (words[0].equals("dish.name")){
						aktSpeise.setName(words[1]);
					}

					if (words[0].equals("dish.nr")){
						aktSpeise.setNumber(Integer.valueOf(words[1]));;	
					}
					if (words[0].equals("dish.typeOfPasta")){

						PastaVO test = (PastaVO)aktSpeise;
						test.setTypeOfPasta(Integer.valueOf(words[1]));
						aktSpeise = test;

					}if (words[0].equals("dish.price")){
						aktSpeise.setPrice(Float.valueOf(words[1]));
					}

					if (words[0].equals("dish.incredient")){
						zutaten.add(words[1]);

					}
					if(words[0].equals("\n")) {
						aktSpeise.setIngredients((String[])zutaten.toArray());
						gerichte.add(aktSpeise);
						zutaten=null;
					}
					if (words[0].equals("dish.type")){
						if (words[1].equals("Dessert")){
							aktSpeise = new DessertVO();	
						}
						if (words[0].equals("dish.name")){
							aktSpeise.setName(words[1]);
						}

						if (words[0].equals("dish.nr")){
							aktSpeise.setNumber(Integer.valueOf(words[1]));	
						}
					}if (words[0].equals("dish.price")){
						aktSpeise.setPrice(Float.valueOf(words[1]));
					}

					if (words[0].equals("dish.incredient")){
						zutaten.add(words[1]);

					}
					if(words[0].equals("\n")) {
						aktSpeise.setIngredients((String[])zutaten.toArray());
						gerichte.add(aktSpeise);
						zutaten=null;
					}					
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	MenuVO menu=new MenuVO(gerichte);
	
	return menu;
	}

}
