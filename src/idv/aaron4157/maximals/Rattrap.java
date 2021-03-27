package idv.aaron4157.maximals;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import idv.aaron4157.maximals.Rhinox.PowerMod;
/**
 * This class needs DI from Optimus Prime
 * functions: instantiate NumberEntity;
 * tells whether it's prime number, it's perfect number, and lists all factors for a number entity
 */
public class Rattrap {
	private OptimusPrime commander;
	private Random generator = new SecureRandom(); //RECOMMANDED random generator
	private Rhinox rhinox = new Rhinox(commander);
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
		int fSum = commander.factorSum(pFactors);
		numbean.setFactorSum(fSum);		
		return numbean;
		
	}
	/**
	 * tells whether a number entity represents a prime number
	 * */
	public boolean inferPrime(NumberEntity testNumber) {
		return testNumber.getComposition().isEmpty();		
	}
	/**
	 *  Test if the number represents a Mersennes prime number = 2^n -1
	 * */
	/*
	 * public static boolean inferMersennesPrime(NumberEntity testNumber) { int test
	 * = 0; int n = 0;
	 * 
	 * while(testNumber.getComposition().isEmpty() && test <= testNumber.getValue())
	 * { test = (int) (Math.pow(2, n) -1); if(test == testNumber.getValue()) return
	 * true; else n++; } return false; }
	 */
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
	/**
	 * tells whether a number is prime, based on Fermat prime test
	 * */
	public Map<Integer, Boolean> prpTest(int n) {
		Map<Integer, Boolean> output = new HashMap<>();		
		int a = 0;
		int result;
		while(a<=1) {
			a = generator.nextInt(n-1);
		}
		
		PowerMod dinobot = rhinox.new PowerMod(n, n-1);
		result = dinobot.calculate(a);
		System.out.println(a +"-PRP test: "+ result);
		output.put(a, result == 1);
		return output; 		
	}
	/**
	 * list all Mersennes primes under 100,000,000,000,
	 * which contains 8 elements!
	 * */
	public void listMersennesPrime() {
		//1.choose q from prime list
		//2.pick a ≡ 1 mod 2q from prime list to temp list
		//3.pick a ≡ ±1 mod 8 from prime list to temp list
		//4.try factorizing (2^q -1) with the temp list
		//5.persistence
		//6.if there is file, read it
	}

	
}
