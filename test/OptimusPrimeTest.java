
import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;

import idv.aaron4157.maximals.OptimusPrime;


/**
 * This test class examines the class OptimusPrime
 * The DOC is PrimeList.txt, which is not isolated for most of the times
 * @author aaron
 */
public class OptimusPrimeTest {
	
	static OptimusPrime optimus;
	TestHelper testHelper;
            
    public OptimusPrimeTest() {
	//OptimusPrime initialization; default upper limit is 104,729 	
    	super();
		
		try {
			optimus = new OptimusPrime();
			testHelper = new TestHelper();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("INIT FAIL: "+ e.getMessage());
		}
	}
    
    @AfterClass
    public static void testConstructorError() {
    	if(OptimusPrimeTest.optimus != null) {    		
    		System.out.println("Test: constructed!");
    	} else {
    		System.out.println("Test: not constructed!");
    	}
    }
    
    @Test
    public void testPrimeList() {
    	
    	//arrange
		String primes = "PrimeList.txt";
		long capacity;
		
    	try {
    		
    		//act
			BufferedReader br = new BufferedReader( new FileReader(primes));
			
			capacity = br.lines().count();
			//index<9999! DONOT convert to Array
			//long maxPrime = br.lines().mapToLong(a -> Long.parseLong(a)).max().getAsLong();
			
			//assert
			//use fact: 104729 is the 10,000 th prime number
			assertEquals(capacity, 10000);
			//assertEquals(maxPrime, 104729);
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    @Test
    public void testFactorize() {
    	//arrange
    	int expected[] = new int[] {2,2,2,2,31};
    	//act
    	List<Integer> result = optimus.factorize(496);
		//assert
    	assertTrue( testHelper.listVerifyEqual(result, expected) );
    }
    
    @Test
    public void testFactorSum() {
    	List<Integer> expected = new ArrayList<>(); 
    		//a prime number p has empty factor list, thus σ(p) = 1 
    	assertEquals(optimus.factorSum(expected),1);
    		//a perfect number x has σ(x) = x
    	expected = optimus.factorize(8128);
    	assertEquals(optimus.factorSum(expected), 8128);
    	
    	System.out.println("factors of 8128 is: "+ expected);
    }
    
    @Test
    public void testListGoldBach() {
    	//arrange
    	HashMap<String,int[]> expected = new HashMap<>();
    	expected.put("1", new int[] {3,23});
    	expected.put("3", new int[] {7,19});
    	expected.put("5", new int[] {13,13});
    	
    	//act
    	HashMap<String, int[]> result = optimus.listGoldBach(26);

    	//assert
    	assertTrue(testHelper.mapVerifyEqual(result, expected));

    }
    
    @Test
    public void testLottery() {
    	int[] result1;
    	int[] result2;
    	//act
    	result1 = optimus.lottery();
    	System.out.println(result1[0]+","+result1[1]);
    	assertEquals(result1.length, 2);
    	//act
    	result2 = optimus.lottery();
    	System.out.println(result2[0]+","+result2[1]);
    	assertFalse(testHelper.arrayVerifyEqual(result2, result1));
    	
    }
    
}
