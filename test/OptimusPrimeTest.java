
import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.AfterClass;
import org.junit.Test;

import idv.aaron4157.maximals.OptimusPrime;


/**
 * This test class examines the class OptimusPrime
 * @author aaron
 */
public class OptimusPrimeTest {
	
	static OptimusPrime optimus;
            
    public OptimusPrimeTest() {
	//OptimusPrime initialization; default upper limit is 104,729 	
    	super();
		
		try {
			optimus = new OptimusPrime();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("INIT FAIL: "+ e.getMessage());
		}
	}
    
    @AfterClass
    public static void testConstructorError() {
    	if(OptimusPrimeTest.optimus != null) {    		
    		System.out.println("constructed!");
    	} else {
    		System.out.println("not constructed!");
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
			
			//capacity = br.lines().count();
			//index<9999! DONOT convert to Array
			long maxPrime = br.lines().mapToLong(a -> Long.parseLong(a)).max().getAsLong();
			
			//assert
			//104729 is the 10,000 th prime number
			//assertEquals(capacity, 10000);
			assertEquals(maxPrime, 104729);
			
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
    	int factorsStub[] = new int[] {2,2,2,2,31};
    	List<Integer> result = optimus.factorize(496);
    	IntStream.of(factorsStub).forEach(e -> 
    			result.remove(result.indexOf(e))   		
    	);
    	assertTrue(result.isEmpty());
    }
    
    @Test
    public void testPerfectness() {
    	List<Integer> factorsStub = new ArrayList<>(); 
    		//a prime number has empty factor list
    	assertEquals(optimus.perfectness(factorsStub),1);
    	
    	factorsStub = optimus.factorize(8128);
    	assertEquals(optimus.perfectness(factorsStub), 8128 * 2);
    	
    	System.out.println("factors of 8128 is: "+ factorsStub);
    }
    
    @Test
    public void testListGoldBach() {
    	//arrange
    	HashMap<String,int[]> stub = new HashMap<>();
    	stub.put("1", new int[] {3,23});
    	stub.put("3", new int[] {7,19});
    	stub.put("5", new int[] {13,13});
    	
    	//act
    	HashMap<String, int[]> result = optimus.listGoldBach(26);
    	result.keySet().forEach(key -> {
    		if(stub.containsKey(key)) {
    			int[] value = stub.remove(key);
    			System.out.println(value[0] +","+ value[1]);
    		}
    	});
    	
    	//assert
    	assertTrue(stub.isEmpty());

    }
    
    
    
}
