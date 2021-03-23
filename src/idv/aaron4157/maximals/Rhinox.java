package idv.aaron4157.maximals;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * This maximal class performs RSA encryption, based on OptimusPrime's prime generator
 * */
public class Rhinox {
	private OptimusPrime commander;
	//Default key pairs, support some Latin B-extensions
	private int[] privateKey = new int[] {497,269}; //example 89*269%132 = 1
	private int[] publicKey = new int[] {497,89}; //example 497 = 7*71; Eu =6*70= 140; (497, 89) = 1
	private StringBuilder cheetor;
		

	public int[] getPublicKey() {
		return publicKey;
	}

	public Rhinox(OptimusPrime OptimalOptimus) {
		// TODO Auto-generated constructor stub
		this.commander = OptimalOptimus;
		//keyGen();
	}
	
	public void keyGen() {
		int[] pPairs = commander.lottery();
		int mainNumber = pPairs[0] * pPairs[1];
		int EulerCount = (pPairs[0] - 1) * (pPairs[1] - 1);
		
		// a random which is relatively prime to EulerCount (r, Eu) = 1		
		Random generator = new SecureRandom();
		int r = 0;
		boolean isPrime;
		do {
			r = generator.nextInt(EulerCount);			
			int rfactors[] = commander.factorize(r).stream().mapToInt(e -> (int) e).distinct().toArray();
			if(rfactors.length > 0) {
			isPrime = IntStream.of(rfactors).allMatch(e -> EulerCount % e != 0);
			} else isPrime = true;
		} while(!isPrime);
		
		this.publicKey = new int[] {mainNumber, r};
		System.out.println("Public key: "+ mainNumber +","+r);
		
		//find (least) mod inverse of r over EulerCount ... check here!
		// m * r = 1 (mod Eu)
		int m;
		int k = 0;
		long test = 4157;
		long temp = 1;
		do {
			temp += EulerCount; //temp = Eu * k + 1
			test = Math.floorMod(temp, r);
			k++; //k may be very large, and cause memory leakage !
		} while(test != 0);
		
		m = (int) temp / r;
		
		this.privateKey = new int[] {mainNumber, m};
		System.out.println("Private key: "+mainNumber+","+m+" #branch="+k);
		
	}
	
	public String enctypt(String msg) {
		cheetor = new StringBuilder();
		int main = this.getPublicKey()[0];
		int idx = this.getPublicKey()[1];
		byte[] msgBytes;
		try {
			msgBytes = msg.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
			msgBytes = new byte[] {};
		}
		PowerMod dinobot = new PowerMod(main, idx);
		
		msg = Base64.getEncoder().encodeToString(msgBytes);
		System.out.println("base64: "+msg);
		msg.chars().map(dinobot::calculate).forEach(e -> cheetor.append((char)e)); 
		return cheetor.toString();		
	}
	
	public void decrypt(String msg) {
		cheetor = new StringBuilder();
		int main = this.privateKey[0];
		int idx = this.privateKey[1];
		byte[] msgBytes;		
		PowerMod dinobot = new PowerMod(main, idx);
		
		msg.chars().map(dinobot::calculate).forEach(e -> cheetor.append((char)e));
		
				
		String recovered = cheetor.toString();
		try {
			msgBytes = recovered.getBytes();
			recovered = new String( Base64.getDecoder().decode(msgBytes), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			recovered = "decode failed under this charset";
		}
		
		System.out.println(recovered);
	}
	
	
	class PowerMod{
		private int main;
		private String idx;
		
		private int factor;
		private BigInteger temp;

		
		PowerMod(int main, int idx) {
			super();
			this.main = main;
			this.idx = new StringBuilder(Integer.toBinaryString(idx))
					.reverse()
					.toString();
		}				
		
		int calculate(int code) {
			this.temp = new BigInteger("1");//reset before use!!
			this.factor = code % main;
			
			idx.chars().forEach(this::stacking);//refresh temp
			
//			return (int) (temp % main); 
			BigInteger mainBig = new BigInteger(String.valueOf(main));
			return temp.mod(mainBig).intValue();
		}
		
		void stacking(int e) {
//			if(e == '1') temp *= factor; 
			if(e == '1') temp = temp.multiply(new BigInteger(String.valueOf(factor)));
			
			if(factor > main/2) factor = main - factor; //reduce memory load
		    factor = (factor * factor) % main;
		}
		
		
	}
	
}
