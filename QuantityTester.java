/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import junit.framework.TestCase;
import java.util.Arrays;

/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 *
 */
public class QuantityTester extends TestCase {

	//Instance Variables
	Quantity test; 
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception 
	{
		test = new Quantity(9.8, Arrays.asList("m", "s", "s"), Arrays.asList("m", "s","s", "s"));
	}

	/**
	 * Test method for {@link Quantity#hashCode()}.
	 */
	public void testHashCode() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#Quantity()}.
	 */
	public void testQuantity() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#Quantity(Quantity)}.
	 */
	public void testQuantityQuantity() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#Quantity(double, java.util.List, java.util.List)}.
	 */
	public void testQuantityDoubleListOfStringListOfString() 
	{
		assertEquals(9.8, test.value);
		assertEquals(null, test.units.get("m"));
		assertEquals(new Integer(-1), test.units.get("s"));
		System.out.println(test.units.entrySet().toArray()[0]);
		
		//fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#mul(Quantity)}.
	 */
	public void testMul() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#div(Quantity)}.
	 */
	public void testDiv() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#pow(int)}.
	 */
	public void testPow() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#add(Quantity)}.
	 */
	public void testAdd() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#sub(Quantity)}.
	 */
	public void testSub() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#negate()}.
	 */
	public void testNegate() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#equals(java.lang.Object)}.
	 */
	public void testEqualsObject() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#normalizedUnit(java.lang.String, java.util.Map)}.
	 */
	public void testNormalizedUnit() 
	{
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link Quantity#normalize(java.util.Map)}.
	 */
	public void testNormalize() 
	{
		fail("Not yet implemented"); // TODO
	}

}
