/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 *
 */
public class UnicalcTester extends TestCase {

	// Instance Variables
	ArrayList<String> emp;
	Map<String,Quantity> env;
	Unicalc calc;
	Quantity ans;
	AST test;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception 
	{
		emp = new ArrayList<String>();
		env = QuantityDB.getDB();
		calc = new Unicalc();
	}

	/**
	 * Test method for {@link Unicalc#S()}.
	 */
	public void testS() 
	{
		calc.tokenize("def smoot 10 meter");
		AST s = calc.S();
		Quantity q1 = new Quantity(10,emp,emp);
		Quantity q2 = new Quantity(1,Arrays.asList("meter"),emp);
		AST expected = new Define("smoot", new Product(new Value(q1),new Value(q2)));
		
		assertEquals(expected, s);
	}

	/**
	 * Test method for {@link Unicalc#L()}.
	 */
	public void testL() 
	{
		calc.tokenize("# 17 kph");
		AST l = calc.L();
		Quantity q1 = new Quantity(17,emp,emp);
		Quantity q2 = new Quantity(1,Arrays.asList("kph"),emp);
		AST expected = new Normalize(new Product(new Value(q1),new Value(q2)));
		
		assertEquals(expected, l);
	}

	/**
	 * Test method for {@link Unicalc#E()}.
	 */
	public void testE() 
	{
		calc.tokenize("10 + 19");
		AST e = calc.E();
		Quantity q1 = new Quantity(10,emp,emp);
		Quantity q2 = new Quantity(19,emp,emp);
		AST expected = new Sum(new Value(q1), new Value(q2));
		
		assertEquals(expected, e);
	}

	/**
	 * Test method for {@link Unicalc#P()}.
	 */
	public void testP() 
	{
		calc.tokenize("105 / 5");
		AST p = calc.P();
		Quantity q1 = new Quantity(105,emp,emp);
		Quantity q2 = new Quantity(5,emp,emp);
		AST expected = new Quotient(new Value(q1), new Value(q2));
		
		assertEquals(expected, p);
	}

	/**
	 * Test method for {@link Unicalc#K()}.
	 */
	public void testK() 
	{
		calc.tokenize("-15");
		AST k = calc.K();
		Quantity fifthteen = new Quantity(15,emp,emp);
		AST expected = new Negation(new Value(fifthteen));
		
		assertEquals(expected, k);
	}

	/**
	 * Test method for {@link Unicalc#Q()}.
	 */
	public void testQ() 
	{
		Quantity two = new Quantity(2,emp,emp);
		Quantity four = new Quantity(4,emp,emp);
		calc.tokenize("(2^4) (4^2)");
		AST q = calc.Q();
		AST expected = new Product(new Power(new Value(two),4), new Power(new Value(four),2));
		
		assertEquals(expected, q);
	}

	/**
	 * Test method for {@link Unicalc#R()}.
	 */
	public void testR() 
	{
		Quantity two = new Quantity(2,emp,emp);
		calc.tokenize("2^3");
		AST r = calc.R();
		AST expected = new Power(new Value(two),3);
		
		assertEquals(expected,r);
	}





}
