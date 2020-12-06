/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set; // remove later
import java.util.TreeSet;
import java.text.DecimalFormat;

/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 */

public class Quantity 
{
	// instance variables
	private double value;
	private Map<String,Integer> units;
	private Set<String> keys;
	
	/**
	 * zero-argument constructor that creates a default quantity of value 1 and
	 * no units.
	 */
	public Quantity()
	{
		value = 1;
		units = new HashMap<String, Integer>();
		keys = units.keySet();
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
		Map<String,Integer> newUnits = new HashMap<String,Integer>();
		newUnits.putAll(toCopy.units);
		
		this.value = toCopy.value;
		this.units = newUnits;
		//this.keys = this.units.keySet();
	}
	
	/**
	 * <p>This constructor takes in three parameters a double (the numeric value), 
	 * a List<String> of the units in the numerator (i.e., with positive 
	 * exponents), and a List<String>of the units in the denominator 
	 * (i.e., the units with negative exponents).</p>
	 * <p>If either of the list arguments is null, this method should throw an 
	 * IllegalArgumentException.</p>
	 * @param value		the numeric value
	 * @param numer		the units in the numerator
	 * @param denom		the units in the denominator
	 * @throws IllegalArgumentException
	 */
	public Quantity(double value, List<String> numer, List<String> denom ) 
			throws IllegalArgumentException
	{
		if (numer == null || denom == null)
			throw new IllegalArgumentException();
		
		this.value = value;
		this.units = new HashMap<String,Integer>();
		unitsListToMap(numer, denom);
		this.keys = this.units.keySet();
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
		//add the exponents of the units and store them into a new hashmap
		Map<String,Integer> newUnits = addMaps(this.units, quantity.units);
		//generate list of the units in the numerator and denominator
		List<String> numer = getNumeratorUnits(newUnits);
		List<String> denom = getDenominatorUnits(newUnits);
		//return the product as a quantity object
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
		//subtract the exponents of the units
		Map<String,Integer> newUnits = subMaps(this.units, divisor.units);
		//generate list of the units in the numerator and denominator
		List<String> numer = getNumeratorUnits(newUnits);
		List<String> denom = getDenominatorUnits(newUnits);
		//return the product as a quantity object
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
		double newValue = Math.pow(value, power);
		//multiply the exponents of the units by an integer and store into a new map.
		Map<String,Integer> newUnits = expMap(power,this.units);
		//generate list of the units in the numerator and denominator
		List<String> numer = getNumeratorUnits(newUnits);
		List<String> denom = getDenominatorUnits(newUnits);
		//return the result in the form of a quantity object
		return new Quantity(newValue, numer, denom);
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
		//check that the units of the operands are equal
		if(quantity == null || !quantity.units.equals(this.units))
			throw new IllegalArgumentException();
		double sum = this.value + quantity.value;
		//generate list of the units in the numerator and denominator
		List<String> numer = getNumeratorUnits(this.units);
		List<String> denom = getDenominatorUnits(this.units);
		//return the sum as a new quantity object
		return new Quantity(sum, numer, denom);
	}
	
	/**
	 * A method sub that takes a single Quantity argument, subtracts it from 
	 * this, and returns the result.
	 * This method should throw an IllegalArgumentException if its argument is 
	 * null or if the two Quantity objects do not have the same units.
	 * 
	 * @param quantity		the quantity to subtract from the calling quantity
	 * @return				the result in a Quantity form
	 */
	public Quantity sub(Quantity quantity)
	{
		//check that the units of the operands are equal
		if(quantity == null || !quantity.units.equals(this.units))
			throw new IllegalArgumentException();
		double diff = this.value - quantity.value;
		//generate list of the units in the numerator and denominator
		List<String> numer = getNumeratorUnits(this.units);
		List<String> denom = getDenominatorUnits(this.units);
		//return the difference as a new quantity object
		return new Quantity(diff, numer, denom);
	}
	
	/**
	 * A method negate that takes no arguments and returns a new Quantity which 
	 * is the negation of the calling Quantity object.
	 * @return		the negation of the calling quantity
	 */
	public Quantity negate()
	{
		double neg = -(this.value);
		//generate list of the units in the numerator and denominator
		List<String> numer = getNumeratorUnits(this.units);
		List<String> denom = getDenominatorUnits(this.units);
		//return the negation as a new quantity object
		return new Quantity(neg, numer, denom);
	}
	
	
	/**
	 * A method toString()that returns the quantity as a String.
	 * @return		A string representation of a Quantity object
	 */
	@Override
	public String toString()
	{
		double valueOfTheQuantity = this.value; 
		Map<String,Integer> mapOfTheQuantity = this.units;
		// Ensure we get the units in order 
		TreeSet<String> orderedUnits = new TreeSet<String>(mapOfTheQuantity.keySet());
		StringBuffer unitsString = new StringBuffer();
		for (String key : orderedUnits) 
		{
			int expt = mapOfTheQuantity.get(key); 
			unitsString.append(" " + key);
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
		//check that quantity is of of type Quantity and that the units are equal
		if (!(quantity instanceof Quantity)|| 
			!( (Quantity) quantity).units.equals(this.units))
			return false;
		//create string representations of the two quantity objects
		String string1 = this.toString();
		String string2 = quantity.toString();
		//compare their string representations
		return string1.equals(string2);
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
		//if database contains unit key then the units can still be normalized
		if (unitDataBase.containsKey(unitName))
		{
			Quantity toReturn = unitDataBase.get(unitName);
			return toReturn.normalize(unitDataBase);
		}
		else
			//units were not found in the database
			return new Quantity (1, Arrays.asList(unitName), Collections.<String>emptyList());
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
		Quantity quant = new Quantity(this.value,Collections.<String>emptyList(), Collections.<String>emptyList());
		for (String str: this.keys)
			quant = quant.mul(Quantity.normalizedUnit(str, unitDataBase).pow(this.units.get(str)));
		return quant;
	}
	
	private void adjustExponentBy(String unitName,int delta) //TODO: Remove before submitting
	{
		if (!this.units.containsKey(unitName))
			this.units.put(unitName, delta);
		else
			this.units.put(unitName, this.units.get(unitName)+delta);
		
		if (this.units.get(unitName) == 0)
			this.units.remove(unitName);
	}
	
	
	private Quantity normRec(Map<String,Quantity> dataBase, Iterator <Map.Entry<String, Quantity>> iter) //TODO: Remove before submitting
	{
		if (!iter.hasNext())
			return null;
		Map.Entry<String, Quantity> tmpEntry = iter.next();
		
		if (dataBase.containsKey(tmpEntry.getKey()))
		{
			return this.mul(Quantity.normalizedUnit(tmpEntry.getKey(), dataBase));
		}
		return null;
	}
	
	
	//////////////////////// Private Helper Methods //////////////////////////
	
	/**
	 * This method is designed to read in the units from a List<String> and 
	 * store its non-zero exponents to the units map.
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
	 * This method is designed to take in two maps and copy all their keys into
	 * a new map, any values associated with keys contained in both maps will
	 * be added together. This method is used to aid in the multiplication of
	 * quantities with units.
	 * @param map1			the hashmap of units for the first quantity
	 * @param map2			the hashmap of units for the second quantity
	 * @return				the sum of the two specified maps
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
		// generate key sets for the first map to iterate over
		copy1KeySet = copy1.keySet();
		// iterate over the first map
		Iterator<String> iter1 = copy1KeySet.iterator();
		// copy <key,values> that are contained in both maps
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
				copy2.remove(tmpKey); // remove <tmpKey,value> from the second map
			}
			else
			{
				// <key,value> was only contained in first map copy to final map
				map3.put(tmpKey, tmpValue);
			}
			// remove the current key from first map
			iter1.remove();
		}
		
		// copy anything remaining in map2
		copy2KeySet = copy2.keySet();
		for (String key: copy2KeySet)
		{
			int tmpValue = copy2.get(key);
			map3.put(key, tmpValue);
		}
		return map3;
	}
	
	/**
	 * This method is designed to retrieve the units of the numerator from a 
	 * specified map, and return them as an ArrayList.
	 * @param map		the specified map to retrieve the units with positive exponents
	 * @return			the units in the form of an arrayList
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
	 * @param map		the specified map to retrieve the units with negative exponents
	 * @return			the units in the form of an arrayList
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
	 * This method is designed to take in two maps and copy all their keys 
	 * into a new map, any values associated with keys contained in both maps 
	 * will be subtracted from one another.
	 * @param map1		the hashmap of units for the first quantity
	 * @param map2		the hashmap of units for the second quantity
	 * @return			the difference of the two specified maps
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
		// generate key sets for the first map to iterate over
		copy1KeySet = copy1.keySet();
		// iterate over the first of the two sets
		Iterator<String> iter1 = copy1KeySet.iterator();
		
		// copy <key,values> that are contained in both maps
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
				copy2.remove(tmpKey); // remove <tmpKey,value> from the second map
			}
			else
			{
				map3.put(tmpKey, tmpValue);
			}
			// remove the key from the first map
			iter1.remove();
		}
		
		// copy anything remaining in map2
		copy2KeySet = copy2.keySet();
		for (String key: copy2KeySet)
		{
			int tmpValue = copy2.get(key);
			map3.put(key, -tmpValue);
		}
		return map3;
	}
	
	/**
	 * This method is designed to help exponentiation of quantities with units.
	 * It will take in the specified exponent to raise each unit by.
	 * @param pow		multiply key values by this amount	
	 * @param map1		the specified map
	 * @return			the result of exponentiation of the specified units map 
	 */
	private Map<String,Integer> expMap(int pow, Map<String,Integer> map1)
	{
		Map<String,Integer> copy1 = new HashMap<String,Integer>();
		Map<String,Integer> toReturn  = new HashMap<String,Integer>();
		Set<String> copy1KeySet;
		
		// create a copy of map1
		copy1.putAll(map1);
		// generate key sets for each map to iterate over
		copy1KeySet = copy1.keySet();
		// iterate over the set of keys
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
	
	
	private void getInverse(Quantity quantity) //TODO:Remove this method if not used
	{
		//quantity.units.equals(this.units);
	}
	
	/**
	 * mini tests
	 * @param args
	 */
	public static void main (String args[])//TODO:Remove this method if not used
	{
		List<String> emp = new ArrayList<String>();
	}
	
	
	
	
	
	

}
