package idv.aaron4157.maximals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * In ver.2.1, this this class focuses on data managements.
 * the data columns are:
 * integer value, its prime compositions, its factors, its factor sums
 * additional function:
 * Goldbach pairs(given value), random prime pairs
 * @author aaron
 */
public class OptimusPrime {
	private String filePath = "PrimeList.txt";
	private Path ppath = Paths.get(filePath);
	private BufferedReader pReader;
	private int[] pCache;	

	public OptimusPrime() throws IOException {
		if(!ppath.toFile().exists()) {
			mkFile(104730);	
		}
		pCache = loadBuffer(1024);
		System.out.println("Initialized.");
	}
	/**
	 * CORE: Generate a prime list ~ given upper bound 
	 * */
    void mkFile(int upper) throws IOException{

    	List<Integer> primeList = new ArrayList<>();
        //start integer scan, deposit the primes
        int i = 2;
        Boolean isComposite = false;
        
        do{
            isComposite = false;
            for(int j : primeList){
                if(i%j == 0){
                    isComposite = true;
                    break;
                }
                if(j > Math.sqrt(i)) break;
            }
            if(!isComposite) primeList.add(i);
            i++;
        }while(i< upper);
        
        //Persistence: object (environment) to file (disk)
        BufferedWriter printerOutput = Files.newBufferedWriter(ppath);            
        for(int j:primeList){
            printerOutput.write(String.valueOf(j)); //NOTICE output format correction
            printerOutput.newLine();
        }
        
        printerOutput.flush();
        printerOutput.close();                        
        
        System.out.println("Done!");
    }


	private int[] loadBuffer(int stop) {
		
		int[] result = null;
		
		if(pCache == null || pCache[pCache.length-1]<stop) {							
		// read file on initialization / demanding request
		try {
			pReader = Files.newBufferedReader(ppath);
			result = pReader.lines().mapToInt(a-> Integer.parseInt(a)).toArray();
			pReader.close();
			
			System.out.println("Load success!");			
		} catch (NumberFormatException e) {
			System.out.println("Wrong format! Is file crashed?");
		} catch (IOException e) {
			System.out.println("Load fail! ");
		}
		
		} else {
		// take a copy of cache
			result = pCache.clone();
		}
		
		return IntStream.of(result).filter(e -> e <= stop).toArray();
	}
	
	
	/*
	 * public NumberEntity analyzeNumber0(int a) { NumberEntity numbean = new
	 * NumberEntity(); numbean.setValue(a); numbean.setComposition(factorize(a));
	 * int fSum = perfectness(numbean.getComposition()); numbean.setFactorSum(fSum);
	 * return numbean;
	 * }
	 */
	
	public List<Integer> factorize(int x){
        List<Integer> factorList = new ArrayList<>();
		if(x==1) return factorList; //exception

        int[] pBuffer1 = loadBuffer(x);
        int y = x;
        int s = 0; //無 萬物之始
        int m = pBuffer1[s] ; //有 萬物之母

        while(y > 1){
            if(y%m == 0) {
                factorList.add(m);
                y = y/m;
            } else m = pBuffer1[++s];//go to next prime number
        }
        
        factorList.remove(Integer.valueOf(x)); //activated if x is prime
        
        return factorList;
    }
	
    public int perfectness(List<Integer> input){
    	if(input.isEmpty()) return 1; //happens when x is prime number
    	
        input.add(1); //last element dosn't enter iteration
        int sub = 1;
        int previous = 1;
        int temp = 1;
        int total = 1;//after computation =sigma(X)
        int chkInput1 = 1;//after computation =value x
        int chkInput2;//after validation =value x
        
        for (int p : input) {
            if(p == previous){
            	//geometric series,from 1 to p^n
                sub *= p;
                temp += sub;
            }else{
            	//next series
                total *= temp;//multiply by previous series
                sub = p;//renew base
                temp = 1 + sub;//renew series
                previous = p;//renew the compared base
            }
            
            chkInput1 *= p; 
        }
        input.remove((input.indexOf(1)));//recover after use the reference
        
        
		chkInput2 = input.stream().distinct().reduce((a,b) -> a *= b).get();
		
        System.out.println("Validation: : "+chkInput1 +" vs."+chkInput2 );
        
        return total;        
    }
    
    
    
    /**
	 * verify GoldBach conjecture
	 * */
	public HashMap<String, int[]> listGoldBach(int testNumber){
		HashMap<String, int[]> result = new HashMap<String, int[]>();
		int pBuffer1[] = loadBuffer(testNumber);
		
		for(int t = 0; pBuffer1[t] <= testNumber/2; t++) {
			for(int s = pBuffer1.length -1; s >= t; s--) {
				if(pBuffer1[t]+pBuffer1[s] == testNumber) {
					result.put(String.valueOf(t), new int[] {pBuffer1[t], pBuffer1[s]});
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * generates prime pairs for use of RSA encryption
	 * add constraints to strengthen the algorithm 
	 * */
	public int[] lottery() {
		int p1=0, p2=0, bound = pCache.length -1;
		bound = 30; //for demonstration purpose...
		Random generator = new SecureRandom(); //RECOMMANDED random generator
		//bound = 128, support basic Latin
		//bound = 65536, support all Unicodes
		while( !(p1 > 2 && 2 * p1 < p2 && p1 * p2 >= 128) ) {
			p1 = pCache[generator.nextInt(bound)];
			p2 = pCache[generator.nextInt(bound)];						
		}
		return new int[] {p1, p2};
	}

    public void close() {
    	try {
			pReader.close();			
		} catch (IOException e) {
			// happens when file loading fail. nothing to close!
			e.printStackTrace();
		}    	
    }
}
