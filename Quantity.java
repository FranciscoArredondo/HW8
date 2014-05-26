/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set; // remove later
import java.util.TreeSet;
import java.text.DecimalFormat;



/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 *
 */
public class Quantity 
{
	// instance variables //TODO: Change back to private
	public double value;
	public Map<String,Integer> units;
	
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
	 * This constructor takes in three parameters a double (the numeric value), 
	 * a List<String> of the units in the numerator (i.e., with positive 
	 * exponents), and a List<String>of the units in the denominator 
	 * (i.e., the units with negative exponents).
	 * If either of the list arguments is null, this method should throw an 
	 * IllegalArgumentException.
	 * @param value		the numeric value
	 * @param numer		the units in the numerator
	 * @param denom		the units in the denominator
	 */
	public Quantity(double value, List<String> numer, List<String> denom ) 
			throws IllegalArgumentException
	{
		if (numer == null || denom == null)
			throw new IllegalArgumentException();
		
		this.value = value;
		this.units = new HashMap<String, Integer>();
		unitsListToMap(numer, denom);
		
	}
	
	/**
	 * This method takes a single Quantity argument, multiplies this by the
	 * argument, and returns the result.
	 * This method should throw an IllegalArgumentException if its argument 
	 * is null.
	 * @param quantity		the quantity to multiply this with
	 * @return				the result 
	 */
	public Quantity mul(Quantity quantity) throws IllegalArgumentException
	{
		if (quantity == null)
			throw new IllegalArgumentException();
		
		double product = this.value * quantity.value;
		Map<String,Integer> newUnits = addMaps(this.units, quantity.units);
		List<String> numer = getNumeratorUnits(newUnits);
		List<String> denom = getDenominatorUnits(newUnits);
		
		return new Quantity(product, numer, denom);
	}
	
	/**
	 * This method takes a single Quantity argument, multiplies this by the
	 * argument, and returns the result.
	 * This method should throw an IllegalArgumentException if its argument is 
	 * null or if the value in the Quantity argument is zero.
	 * @param quantity		the quantity to multiply this with
	 * @return				the result 
	 */
	public Quantity div(Quantity divisor)
	{
		if (divisor == null || divisor.value == 0)
			throw new IllegalArgumentException();
		
		double quotient = this.value/divisor.value;
		Map<String,Integer> newUnits = subMaps(this.units, divisor.units);
		List<String> numer = getNumeratorUnits(newUnits);
		List<String> denom = getDenominatorUnits(newUnits);
		
		return new Quantity(quotient, numer, denom);
	}
	
	/**
	 * A method pow that takes a single int argument (positive, negative, or 
	 * zero!), raises this to the given power, and returns the result.
	 * @param quantity		the quantity to multiply this with
	 * @return				the result 
	 */
	public Quantity pow(int power)
	{
		double newValue = this.value*this.value;
		
		return null;
	}
	
	/**
	 * A method add that takes a single Quantity argument, adds this to it, and
	 * returns the result. 
	 * This method should throw an IllegalArgumentException if its argument is 
	 * null or if the two Quantity objects do not have the same units.
	 * @param quantity		the quantity to add to the calling quantity
	 * @return				the result in a Quantity form
	 */
	public Quantity add(Quantity quantity)
	{
		// check if the units do not match throw an IllegalArgumentException
		return null;
	}
	
	
	/**
	 * A method sub that takes a single Quantity argument, subtracts it from 
	 * this, and returns the result.
	 * This method should throw an IllegalArgumentException if its argument is 
	 * null or if the two Quantity objects do not have the same units.
	 * 
	 * @param quantity
	 * @return
	 */
	public Quantity sub(Quantity quantity)
	{
		// check if the units do not match throw an IllegalArgumentException
		return null;
	}
	
	/**
	 * A method negate that takes no arguments and returns a new Quantity which 
	 * is the negation of this Quantity.
	 * @return		the negation of the calling quantity
	 */
	public Quantity negate()
	{
		return null;
	}
	
	
	/**
	 * A method toString()that returns the quantity as a String.
	 * @return		A string representation of a Quantity object
	 */
	@Override
	public String toString()
	{
		// XXX You will need to fix these lines to match your fields! 
		double valueOfTheQuantity = this.value; 
		Map<String,Integer> mapOfTheQuantity = this.units;
		// Ensure we get the units in order 
		TreeSet<String> orderedUnits = new TreeSet<String>(mapOfTheQuantity.keySet());
		StringBuffer unitsString = new StringBuffer();
		for (String key : orderedUnits) 
		{
			int expt = mapOfTheQuantity.get(key); unitsString.append(" " + key);
			if (expt != 1)
				unitsString.append("^" + expt);
		}
		// Used to convert doubles to a string with a 
		// fixed maximum number of decimal places.
		DecimalFormat df = new DecimalFormat("0.0####");
		// Put it all together and return. 
		return df.format(valueOfTheQuantity) + unitsString.toString();
	}
	
	
	/**
	 * A boolean method equals that takes any single Object, and returns true 
	 * if and only if that object is a Quantity whose units are exactly the same
	 * as the calling object and whose value is the same when rounded to five 
	 * places after the decimal point. E.g. the values 0.111112 and 0.111113 
	 * are considered equals while 0.111112 and 0.111118 are not.
	 * @param quantity		the specified object to be compared with
	 * @return				true if the quantities are equal, else return false
	 */
	@Override
	public boolean equals(Object quantity)
	{
		if (!(quantity instanceof Quantity))
			return false;
		// compare the calling quantity to the specified quantity using their string representations
		
		return false;
	}
	
	/**
	 * A method hashCode() that returns an integer, such that equal Quantities 
	 * always return the same integer.
	 */
	public int hashCode()
	{
		return this.toString().hashCode();
	}
	
	
	/**
	 * <p>A static method normalizedUnit that takes a String 
	 * (the name of a unit) and a Map<String,Quantity>(a units database). It 
	 * should create a brand-new normalized Quantity equivalent to 1 of the 
	 * given unit.</p>
	 * 
	 * <p>For example, if db is the database defined above, then 
	 * Quantity.normalizedUnit("km",db)should return the quantity 1000 meter; 
	 * Quantity.normalizedUnit("hour",db)should return the quantity 3600second; 
	 * and Quantity.normalizedUnit("kph",db)would return the quantity 0.27777...
	 * meters per second.</p>
	 * @param unitName			name of unit to be normalized
	 * @param unitsDataBase		the database to units to be searched
	 * @return					the normalized quantity
	 */
	public static Quantity normalizedUnit(String unitName, Map<String,Quantity> unitDataBase)
	{
		return null;
		
	}
	
	/**
	 * <p>A (non-static) method normalize that takes in a database in the same 
	 * format as above and returns a copy of this but in normalized form 
	 * (with all defined units expanded out into primitive units).</p>
	 * <p>For example, normalizing the quantity 60 kph, given the above 
	 * database, should produce the quantity 16.6666... meters per second.</p>
	 * @param unitDataBase		the specified database of units
	 * @return					the calling quantity as a new normalized quantity
	 */
	public Quantity normalize(Map<String,Quantity>unitDataBase)
	{
		return null;
	}
	
	
	//////////////////////// Private Helper Methods //////////////////////////
	
	/**
	 * This method is designed to read in the units from a List<String> and 
	 * store its non-zero exponent to the units map.
	 * @param numerator			the units in the numerator
	 * @param denominator		the units in the denominator
	 */
	private void unitsListToMap(List<String> numerator, List<String> denominator)
	{
		// adding numerator units
		for (int i = 0, count = 0; i < numerator.size(); ++i)
		{
			if (units.containsKey(numerator.get(i)))
				units.put(numerator.get(i), ++count);
			else
			{
				count = 0;
				units.put(numerator.get(i), ++count);
			}
				
		}
		
		// adding denominator units
				for (int i = 0, count = 0; i < denominator.size(); ++i)
				{
					if (units.containsKey(denominator.get(i)))
					{
						// if units perfectly cancel then remove them from the map
						if (units.get(denominator.get(i))-1 == 0)
							units.remove(denominator.get(i));
						else // continue to decrement the exponent
							units.put(denominator.get(i), units.get(denominator.get(i))-1);
					}
					else
					{
						count = 0;
						units.put(denominator.get(i), --count);
					}
					
						
				}
	}
	
	/**
	 * This method is designed to take in two maps and add all their keys into
	 * a new map, any values associated with keys contained in both maps will
	 * be added together.
	 * @param map1
	 * @param map2
	 */
	private Map<String,Integer> addMaps(Map<String,Integer> map1, Map<String,Integer> map2)
	{
		Map<String,Integer> copy1 = new HashMap<String,Integer>();
		Map<String,Integer> copy2 = new HashMap<String,Integer>();
		Map<String,Integer> map3  = new HashMap<String,Integer>();
		Set<String> copy1KeySet;
		Set<String> copy2KeySet;
		int newValue;
		// create a copy of map1 and map2
		copy1.putAll(map1);
		copy2.putAll(map2);
		// generate key sets for each map to iterate over
		copy1KeySet = copy1.keySet();
		copy2KeySet = copy2.keySet();
		// iterate over the first of the two sets
		Iterator<String> iter1 = copy1KeySet.iterator();
		
		
		// copy everything contained in both lists
		while(iter1.hasNext())
		{
			String tmpKey = iter1.next();
			int tmpValue = copy1.get(tmpKey);
			// check if map2 contains tmpKey
			if(copy2.containsKey(tmpKey))
			{
				newValue = tmpValue + copy2.get(tmpKey);
				if (!(newValue == 0)) // don't add any units that cancel out
					map3.put(tmpKey, newValue);
			}
			else
			{
				map3.put(tmpKey, tmpValue);
			}
			// remove the key from both list
			iter1.remove();
			copy2KeySet.remove(tmpKey);	
		}
		
		// copy anything remaining in map2
		Iterator<String> iter2 = copy2KeySet.iterator();
		while(iter2.hasNext())
		{
			String tmpKey = iter2.next();
			int tmpValue = copy2.get(tmpKey);
			map3.put(tmpKey, tmpValue);
			copy2KeySet.remove(tmpKey);	
		}
		return map3;
	}
	
	/**
	 * This method is designed to retrieve the units of the numerator from a 
	 * specified map, and return them as a list.
	 * @param map
	 * @return
	 */
	private List<String> getNumeratorUnits(Map<String,Integer> map)
	{
		Set<String> mapKeySet = map.keySet();
		Iterator<String> iter = mapKeySet.iterator();
		List<String> toReturn = new ArrayList<String>();
		
		while(iter.hasNext())
		{
			String tmpKey = iter.next();
			int count = map.get(tmpKey);
			while(count > 0)
			{
				toReturn.add(tmpKey);
				--count;
			}
		}
		return toReturn;
	}
	
	/**
	 * This method is designed to retrieve the units of the denominator from a 
	 * specified map, and return them as a list.
	 * @param map
	 * @return
	 */
	private List<String> getDenominatorUnits(Map<String,Integer> map)
	{
		Set<String> mapKeySet = map.keySet();
		Iterator<String> iter = mapKeySet.iterator();
		List<String> toReturn = new ArrayList<String>();
		
		while(iter.hasNext())
		{
			String tmpKey = iter.next();
			int count = map.get(tmpKey);
			while(count < 0)
			{
				toReturn.add(tmpKey);
				++count;
			}
		}
		return toReturn;
	}
	
	/**
	 * This method is designed to take in two maps and subtract all their keys 
	 * into a new map, any values associated with keys contained in both maps 
	 * will be subtracted from one another.
	 * @param map1
	 * @param map2
	 * @return
	 */
	private Map<String,Integer> subMaps(Map<String,Integer> map1, Map<String,Integer> map2)
	{
		Map<String,Integer> copy1 = new HashMap<String,Integer>();
		Map<String,Integer> copy2 = new HashMap<String,Integer>();
		Map<String,Integer> map3  = new HashMap<String,Integer>();
		Set<String> copy1KeySet;
		Set<String> copy2KeySet;
		int newValue;
		// create a copy of map1 and map2
		copy1.putAll(map1);
		copy2.putAll(map2);
		// generate key sets for each map to iterate over
		copy1KeySet = copy1.keySet();
		copy2KeySet = copy2.keySet();
		// iterate over the first of the two sets
		Iterator<String> iter1 = copy1KeySet.iterator();
		
		
		// copy everything contained in both lists
		while(iter1.hasNext())
		{
			String tmpKey = iter1.next();
			int tmpValue = copy1.get(tmpKey);
			// check if map2 contains tmpKey
			if(copy2.containsKey(tmpKey))
			{
				newValue = tmpValue - copy2.get(tmpKey);
				if (!(newValue == 0)) // don't add any units that cancel out
					map3.put(tmpKey, newValue);
			}
			else
			{
				map3.put(tmpKey, tmpValue);
			}
			// remove the key from both list
			iter1.remove();
			copy2KeySet.remove(tmpKey);	
		}
		
		// copy anything remaining in map2
		Iterator<String> iter2 = copy2KeySet.iterator();
		while(iter2.hasNext())
		{
			String tmpKey = iter2.next();
			int tmpValue = copy2.get(tmpKey);
			map3.put(tmpKey, tmpValue);
			copy2KeySet.remove(tmpKey);	
		}
		return map3;
	}
	
	/**
	 * This method is designed to help exponentiate the values in a specified
	 * map.
	 * @param pow		multiply key values by this amount	
	 * @param map1		the specified map
	 * @return
	 */
	private Map<String,Integer> expMap(int pow, Map<String,Integer> map1)
	{
		Map<String,Integer> copy1 = new HashMap<String,Integer>();
		Map<String,Integer> toReturn  = new HashMap<String,Integer>();
		Set<String> copy1KeySet;
		int newValue;
		// create a copy of map1
		copy1.putAll(map1);
		// generate key sets for each map to iterate over
		copy1KeySet = copy1.keySet();
		// iterate over the first set
		Iterator<String> iter1 = copy1KeySet.iterator();
		
		// copy everything 
		while(iter1.hasNext())
		{
			String tmpKey = iter1.next();
			int tmpValue = copy1.get(tmpKey)*pow;
			toReturn.put(tmpKey, tmpValue);
			// remove the key from both list
			iter1.remove();
		}
		
		return toReturn;
	}
	
	
	/**
	 * mini tests
	 * @param args
	 */
	public static void main (String args[])
	{
		Quantity test = new Quantity(9.8, Arrays.asList("m", "s", "s"), Arrays.asList("m","s", "s"));
		Quantity test2 = new Quantity(2, Arrays.asList("m"), Arrays.asList("s","s"));
		
		//Quantity ans = test.mul(test2);
		//Quantity ans = test.div(test2);
		Map<String, Integer> ans = test.expMap(3, test2.units);
		
		System.out.println(ans.toString());
		//Quantity map1 = new Quantity(9.8, Arrays.asList("a", "b", "C","h", "m", "s"), Arrays.asList("p", "q", "a"));
		//Quantity map2 = new Quantity(9.8, Arrays.asList("a", "x", "z", "m", "d"), Arrays.asList("m", "m", "s"));
		
		/*System.out.println(map1.units.entrySet().toString());
		System.out.println(map2.units.entrySet().toString());
		System.out.println("\nKey Sets: ");
		System.out.println("map1: " + map1.units.keySet().toString());
		System.out.println("map2: "+ map2.units.keySet().toString());
		
		Set<String> map1KeySet = map1.units.keySet();
		map1KeySet.clear();
		
		System.out.println(map1.units.entrySet().toString());
		System.out.println(map2.units.entrySet().toString());
		System.out.println("\nKey Sets: ");
		System.out.println("map1: " + map1.units.keySet().toString());
		System.out.println("map2: "+ map2.units.keySet().toString());*/
		
		
		/*Map<String, Integer> map1 = new HashMap<String, Integer>();
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map1.put("m",1);
		map1.put("s",2);
		map2.put("s",2);
		map2.put("m",1);
		
		
		System.out.println("map1: " + map1.entrySet().toString());
		System.out.println("map2: " + map2.entrySet().toString());
		Map<String,Integer> map3 = test.subMaps(map1,map2);
		System.out.println("map3: " + map3.entrySet().toString());
		
		System.out.println(test.getDenominatorUnits(map3).toString());*/

		
		
		/*System.out.println(test.units.entrySet().toString());
		System.out.println(test2.units.entrySet().toString());
		System.out.println("\nKey Sets: ");
		System.out.println("test: " + test.units.keySet().toString());
		System.out.println("test2: "+ test2.units.keySet().toString());
		
		Map<String, Integer> copy = new HashMap<String, Integer>();
		copy.putAll(test.units);
		System.out.println("copy: " + copy.entrySet().toString());
		System.out.println("test: " + test.units.entrySet().toString());
		
		copy.clear();
		System.out.println("copy: " + copy.entrySet().toString());
		System.out.println("test: " + test.units.entrySet().toString());*/
	}
	
	
	
	
	
	

}
