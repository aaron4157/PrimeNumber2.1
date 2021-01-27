package idv.aaron4157.maximals;

import java.util.ArrayList;
import java.util.List;
/**
 * This class needs DI from Optimus Prime
 * functions: instantiate NumberEntity;
 * tells whether it's prime number, it's perfect number, and lists all factors for a number entity
 */
public class Rattrap {
	private OptimusPrime commander;
	private List<Integer> pFactors=null;
	private List<Integer> allFactors=null;
	
	public Rattrap(OptimusPrime OptimalOptimus) {
		// dependency injection as initialized
		this.commander = OptimalOptimus;
	}
	/**
	 * Generate number entity
	 * */
	public NumberEntity analyzeNumber(int a) {
		NumberEntity numbean = new NumberEntity();
		numbean.setValue(a);
		pFactors = commander.factorize(a);
		numbean.setComposition(pFactors);
		int fSum = commander.perfectness(pFactors);
		numbean.setFactorSum(fSum);		
		return numbean;
		
	}
	/**
	 * Test if the number entity represents a prime number
	 * */
	public boolean inferPrime(NumberEntity testNumber) {
		return testNumber.getComposition().isEmpty();		
	}
	/**
	 *  Test if the number entity represents a Mersennes prime number = 2^n -1
	 * */
	public static boolean inferMersennesPrime(NumberEntity testNumber) {
		int test = 0;
		int n = 0;
		
		while(testNumber.getComposition().isEmpty() && test <= testNumber.getValue()) {
			test = (int) (Math.pow(2, n) -1);
			if(test == testNumber.getValue()) return true;
			else n++;
		}
		return false;
	}
	/**
	 * judge whether a number entity represents a perfect number
	 * */
 	public static String perfectness(NumberEntity testNumber) {
		if(testNumber.getFactorSum() == 2*testNumber.getValue()) 
			return "perfect number";
		else if(testNumber.getFactorSum() > 2*testNumber.getValue())
			return "abundant number";
		else return "deficient number";
	}
	
	/**
	 * produces a list of all factors for a given number
	 * */
	public List<Integer> listFactors(NumberEntity testNumber) {
		pFactors = testNumber.getComposition();
		allFactors = new ArrayList<>();		

		if(pFactors.isEmpty()) {
			allFactors.add(1); //happens when x is prime
			return allFactors;
		}
		
		f(1,0);
		allFactors.remove(allFactors.indexOf( testNumber.getValue() ));
		return allFactors;
	}
	private void f(int m, int i) {
		//generative algorithm based on prime factorization
		if(i >= pFactors.size()) {
			//prevent repeated factors...
			if(allFactors.indexOf(m)<0)	
				allFactors.add(m);				
			return;
		}
		int n = pFactors.get(i);
		f((m*n), i+1);
		f(m, i+1);
		return;			
	}
	

	
}
