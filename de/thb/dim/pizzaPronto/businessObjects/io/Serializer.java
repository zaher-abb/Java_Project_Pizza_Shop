package de.thb.dim.pizzaPronto.businessObjects.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import de.thb.dim.pizzaPronto.valueObjects.OrderVO;

public class Serializer extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectOutputStream os;
	private ObjectInputStream is;
	
	public Serializer(String datei) throws FileNotFoundException, NullPointerException {
		try {
			os=new ObjectOutputStream (new FileOutputStream(datei));
		}

		catch ( IOException e) {	
			System.err.println("io error :"+e.getMessage());
			e.printStackTrace();
		}
		try {
			is=new ObjectInputStream (new FileInputStream(datei));
		}

	 	catch(IOException ex) {
			System.err.println("io error :"+ex.getMessage()) ;
		}
	}
	
	public void serializeOrder(OrderVO order) {
		
		try {
			os.writeObject(order);
			this.closeOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void closeOutput() {
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public OrderVO deserializeOrder() {
			
		try {
			return (OrderVO)is.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.closeInput();
		}
		return null;
	} 
	
	public void closeInput() {
		try {
			is.close();
		} catch (IOException e) {
//			System.err.println("io error :"+e.getMessage());
			e.printStackTrace();
		}
		
	}
}
