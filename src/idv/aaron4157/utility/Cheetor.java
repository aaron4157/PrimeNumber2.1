package idv.aaron4157.utility;

import java.util.ArrayList;
import java.util.List;
/**
 *這個類別根據Number物件資訊進行推斷 
 */
public class Cheetor {
	private List<Integer> pFactors=null;
	private List<Integer> allFactors=null;
	
	public Cheetor() {
		// TODO Auto-generated constructor stub
	}
	public String inferPrime(NumberEntity testNumber) {
		pFactors=testNumber.getComposition();
		if(pFactors.contains(testNumber.getValue()))
			return "prime";
		else return "composite";
	}

	public static boolean inferMasonPrime(NumberEntity testNumber) {
		int test = 0;
		int n = 0;
		
		while(test <= testNumber.getValue()) {
			test = (int) (Math.pow(2, n) -1);
			if(test == testNumber.getValue()) return true;
			else n++;
		}
		return false;
	}
	
 	public static String perfectness(NumberEntity testNumber) {
		if(testNumber.getFactorSum()==2*testNumber.getValue()) 
			return "perfect number";
		else if(testNumber.getFactorSum() > 2*testNumber.getValue())
			return "abundant number";
		else return "deficient number";
	}
	
	
	public List<Integer> listFactors(NumberEntity testNumber) {
		pFactors = testNumber.getComposition();
		allFactors = new ArrayList<>();		
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
