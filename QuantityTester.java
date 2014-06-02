/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 */
public class QuantityTester extends TestCase {

	//Instance Variables
	Quantity gravity;
	Quantity velocity;
	Quantity velocity2;
	Quantity sol;
	Quantity solCopy;
	Quantity plank;
	Quantity plank2;
	Quantity avogado;
	Map<String,Quantity> db;
	List<String> emp;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception 
	{
		gravity = new Quantity(9.8, Arrays.asList("m"), Arrays.asList("s","s"));
		velocity = new Quantity(10, Arrays.asList("m"), Arrays.asList("s"));
		velocity2 = new Quantity(100, Arrays.asList("m"), Arrays.asList("s"));
		sol = new Quantity(2.99, Arrays.asList("m"), Arrays.asList("s"));
		solCopy = new Quantity(2.99, Arrays.asList("m"), Arrays.asList("s"));
		plank = new Quantity(626, Arrays.asList("J", "s"), Collections.<String>emptyList());
		plank2 = new Quantity(26, Arrays.asList("J", "s"), Collections.<String>emptyList());
		avogado = new Quantity(602, Collections.<String>emptyList(), Arrays.asList("mol"));
		
		db = new HashMap<String,Quantity>();
		emp = new ArrayList<String>();   

		db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
		db.put("day", new Quantity(24, Arrays.asList("hour"), emp));
		db.put("hour", new Quantity(60, Arrays.asList("minute"), emp));
		db.put("minute", new Quantity(60, Arrays.asList("second"), emp));
		db.put("hertz", new Quantity(1, emp, Arrays.asList("second")));
		db.put("kph", new Quantity(1, Arrays.asList("km"), Arrays.asList("hour")));
	}

	/**
	 * Test method for {@link Quantity#hashCode()}.
	 */
	public void testHashCode() 
	{
		int hashCode1 = sol.hashCode();
		int hashCode2 = sol.hashCode();
		int hashCode3 = solCopy.hashCode();
		int hashCode4 = gravity.hashCode();
		
		assertEquals(hashCode1, hashCode2);
		assertEquals(hashCode2, hashCode3);
		assertTrue(hashCode1 != hashCode4);
	}

	/**
	 * Test method for {@link Quantity#Quantity()}.
	 */
	public void testQuantity()
	{
		Quantity test = new Quantity();
		assertEquals("1.0", test.toString());
	}

	/**
	 * Test method for {@link Quantity#Quantity(Quantity)}.
	 */
	public void testQuantityCopy() 
	{
		Quantity test = new Quantity(gravity);
		assertEquals("9.8 m s^-2", test.toString());
	}

	/**
	 * Test method for {@link Quantity#Quantity(double, java.util.List, java.util.List)}.
	 */
	public void testQuantityThreeArgs() 
	{
		Quantity test = new Quantity (15, Arrays.asList("min", "min", "s"), Arrays.asList("m", "J"));
		assertEquals("15.0 J^-1 m^-1 min^2 s", test.toString());
	}

	/**
	 * Test method for {@link Quantity#mul(Quantity)}.
	 */
	public void testMul() 
	{
		Quantity product = velocity.mul(gravity);
		Quantity expected = new Quantity (98, Arrays.asList("m","m"), Arrays.asList("s","s","s"));
		assertEquals(expected,product);
	}

	/**
	 * Test method for {@link Quantity#div(Quantity)}.
	 */
	public void testDiv() 
	{
		Quantity quotient = velocity.div(plank);
		Quantity expected = new Quantity(0.01597, Arrays.asList("m"), Arrays.asList("J","s","s"));
		assertEquals(expected,quotient);
	}

	/**
	 * Test method for {@link Quantity#pow(int)}.
	 */
	public void testPow() 
	{
		Quantity pow = gravity.pow(3);
		Quantity expected = new Quantity(941.192, Arrays.asList("m","m","m"), Arrays.asList("s","s","s","s","s","s"));
		assertEquals(expected,pow);
	}

	/**
	 * Test method for {@link Quantity#add(Quantity)}.
	 */
	public void testAdd() 
	{
		Quantity sum = velocity.add(velocity2);
		Quantity expected = new Quantity(110, Arrays.asList("m"), Arrays.asList("s"));
		assertEquals(expected,sum);
	}
	
	/**
	 * Test method for {@link Quantity#add(Quantity)}.
	 * Testing for exception thrown.
	 */
	public void testAddEception()
	{
		try
		{
			@SuppressWarnings("unused")
			Quantity sum = velocity.add(gravity);
			fail("Should have thrown an IllegalArgumentException.");
		}
		catch (IllegalArgumentException e)
		{
			//normal
		}
	}

	/**
	 * Test method for {@link Quantity#sub(Quantity)}.
	 */
	public void testSub() 
	{
		Quantity diff = plank.sub(plank2);
		Quantity expected = new Quantity(600, Arrays.asList("J","s"), emp);
		assertEquals(expected, diff);
	}
	
	/**
	 * Test method for {@link Quantity#sub(Quantity)}.
	 * Testing for exception thrown.
	 */
	public void testSubEception()
	{
		try
		{
			@SuppressWarnings("unused")
			Quantity diff = velocity.sub(gravity);
			fail("Should have thrown an IllegalArgumentException.");
		}
		catch (IllegalArgumentException e)
		{
			//normal
		}
	}

	/**
	 * Test method for {@link Quantity#negate()}.
	 */
	public void testNegate() 
	{
		Quantity neg = gravity.negate();
		Quantity expected = new Quantity(-9.8, Arrays.asList("m"), Arrays.asList("s","s"));
		assertEquals(expected,neg);
	}

	/**
	 * Test method for {@link Quantity#equals(java.lang.Object)}.
	 */
	public void testEqualsObject() 
	{
		boolean fail = plank.equals(gravity);
		boolean pass = sol.equals(solCopy);
		assertEquals(false, fail);
		assertEquals(true, pass);
	}

	/**
	 * Test method for {@link Quantity#normalizedUnit(java.lang.String, java.util.Map)}.
	 */
	public void testNormalizedUnit() 
	{
		Quantity test = Quantity.normalizedUnit("hour", db);
		Quantity expected = new Quantity(3600, Arrays.asList("second"), emp);

		Quantity test2 = Quantity.normalizedUnit("km", db);
		Quantity expected2 = new Quantity(1000, Arrays.asList("meter"), emp);

		assertEquals(expected.toString(), test.toString());
		assertEquals(expected2.toString(), test2.toString());
	}

	/**
	 * Test method for {@link Quantity#normalize(java.util.Map)}.
	 */
	public void testNormalize() 
	{
		Map<String,Quantity> db = new HashMap<String,Quantity>();
		List<String> emp = new ArrayList<String>();

		db.put("hour", new Quantity(60, Arrays.asList("minute"), emp));
		db.put("minute", new Quantity(60, Arrays.asList("second"), emp));


		Quantity quantity = new Quantity(20, Arrays.asList("hour"), emp);
		Quantity test = quantity.normalize(db);
		Quantity expected = new Quantity(72000, Arrays.asList("second"), emp);

		assertEquals(expected, test);
	}

}
