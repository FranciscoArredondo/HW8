/*
 * CSE 12 Homework 8
 * Francisco Arredondo, Michelle Arteaga
 * A07614106, A11468765
 * A00, A00
 * May 23, 2014
 */

import junit.framework.TestCase;

import java.util.*;

/**
 * @author Francisco Arredondo
 * @author Michelle Arteaga
 *
 */
public class ASTTester extends TestCase {

 Map<String,Quantity> env;
 ArrayList<String> emp;
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
  * Test method for {@link Product#Product(AST, AST)}.
  */
 public void testProduct() 
 {
  calc.tokenize("3 * 5");
  test = calc.parse();
  ans = test.eval(env);
  
  assertEquals(new Quantity(15, emp, emp).toString(), ans.toString());
 }

 /**
  * Test method for {@link Quotient#Quotient(AST, AST)}.
  */
 public void testQuotient() 
 {
  calc.tokenize("150 / 50");
  test = calc.parse();
  ans = test.eval(env);

  assertEquals(new Quantity(3, emp, emp).toString(), ans.toString());
 }

 /**
  * Test method for {@link Sum#Sum(AST, AST)}.
  */
 public void testSum() 
 {
  calc.tokenize("60 + 15 + 50");
  test = calc.parse();
  ans = test.eval(env);
  System.out.println("ans string form: "+ans.toString());

  assertEquals(new Quantity(125, emp, emp).toString(), ans.toString());
 }
 
 /**
  * Test method for {@link Difference#Difference(AST, AST)}
  */
 public void testDifference()
 {
  calc.tokenize("200 - 50 - 15");
  test = calc.parse();
  ans = test.eval(env);

  assertEquals(new Quantity(165, emp, emp).toString(), ans.toString());
 }
 
 /**
  * Test method for {@link Power#Power(AST, int)}
  */
 public void testPower()
 {
  calc.tokenize("10^4");
  test = calc.parse();
  ans = test.eval(env);

  assertEquals(new Quantity(10000, emp, emp).toString(), ans.toString());
 }
 
 /**
  * Test method for {@link Negation#Negation(AST)}
  */
 public void testNegation()
 {
  calc.tokenize("-5");
  test = calc.parse();
  ans = test.eval(env);
  
  assertEquals(new Quantity(-5, emp, emp).toString(), ans.toString());
 }
 
 /**
  * Test method for {@link Normalize#Normalize(AST)}
  */
 public void testNormalize()
 {
	 calc.tokenize("# 10 hr");
	 test = calc.parse();
	 ans = test.eval(env);
	 Quantity expected = new Quantity(36000, Arrays.asList("second"), emp);     
   
	 assertEquals(expected.toString(), ans.toString());
 }
 
 /**
  * Test method for {@link Define#Define(String, AST)}
  */
 public void testDefine()
 { 
	 assertFalse(env.containsKey("smoot"));
	 
	 calc.tokenize("def smoot 100 meters");
	 test = calc.parse();
	 ans = test.eval(env);
   
	 assertTrue(env.containsKey("smoot"));
	 assertEquals(new Quantity(100, Arrays.asList("meters"), emp).toString(), ans.toString());   
 }
 
 /**
  * Test method for {@link Value#Value(Quantity)}
  */
 public void testValue()
 {
	 calc.tokenize("5");
	 test = calc.parse();
	 ans = test.eval(env); 
   
   assertEquals(new Quantity(5,emp,emp).toString(), ans.toString());
 }

}