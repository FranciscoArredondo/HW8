/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import java.util.HashMap;
import java.util.Map;


/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 *
 */
public class Quantity 
{
	// instance variables
	private double value;
	private Map<String,Integer> units;
	
	/**
	 * zero-argument constructor that creates a default quantity of value 1 and
	 * no units.
	 */
	public Quantity()
	{
		value = 1;
		units = new HashMap<String, Integer>();
	}
	
	/**
	 * A constructor that takes a single Quantity argument, and creates a deep
	 * copy. By deep copy here we mean that the new Quantity will be a different
	 * object with its own instance variables, and that it will contain a copy
	 * of the Map object referenced by the argument Quantity.
	 * @param toCopy	the Quantity object to be copied.
	 */
	public Quantity(Quantity toCopy)
	{
		//TODO: May need to check this for correctness with a tutor.
		value = toCopy.value;
		units = toCopy.units; // Unsure if this is how the map object should be 
							  // copied. 
	}
	
	/**
	 * 
	 * @param value
	 * @param numer
	 * @param denom
	 */
	public Quantity(double value, List<String> numer, List<String> denom )
	{
		
	}
	
	
	
	

}
