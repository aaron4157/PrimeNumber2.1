import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import idv.aaron4157.maximals.NumberEntity;
import idv.aaron4157.maximals.Rattrap;

/**
 * This test class examines the class Rattrap
 * The DOC is OptimusPrime. Here its subclass, OptimusTemp, incorporated for isolation.
 * The Rattrap uses NumberEntity. Here an example instance is incorporated. 
 * Thus this class is a "mock"; a "dummy" NumberEntity instance is used.
 * */
public class RattrapTest {
	Rattrap rattrap;
	TestHelper testHelper;
	NumberEntity numberDummy = new NumberEntity(); //cannot be edited outside the package, and remains initial state

	public RattrapTest(){
		// arrange Rattrap and helpers	
		try {
			rattrap = new Rattrap(new OptimusTemp());
			testHelper = new TestHelper();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("INIT FAIL: "+ e.getMessage());
		}
		
	}
	
	@AfterClass
	public static void testConstructorError(){
		// no static resources here
	}
	
	
	@Test
	public void testAnalyzeNumber(){
		//arange
		int[] expectedList = new int[] {2, 2, 3, 3, 3, 5};
		int expectedFactorSum = 1+2+3+4+5+6+9+10+12+15+18+20+27+30+36+45+54+60+90+108+135+180+270;
		//act
		NumberEntity aftermath = rattrap.analyzeNumber(4 * 27 * 5);
		
		//assert
		assertEquals(aftermath.getValue(), 540);
		assertTrue(testHelper.listVerifyEqual(aftermath.getComposition(), expectedList));
		assertEquals(aftermath.getFactorSum(), expectedFactorSum );
		assertTrue(Integer.valueOf(aftermath.toString()) == 540);
	}
	
	@Test
	public void testPrime() {
		
	}
	
	@Test
	public void testListMersennesPrime() {
		
	}
	
	@Test
	public void testPerfectness() {
		NumberEntity numberTest = rattrap.analyzeNumber(540);
		//1140
		assertEquals(rattrap.perfectness(numberTest),"abundant number");
	}
	
	@Test
	public void testListFactors() {
		//the dummy has empty composition
		int[] expectedList1 = new int[] {1};
		assertTrue(testHelper.listVerifyEqual(rattrap.listFactors(numberDummy), expectedList1));
		
		//list factors of 540
		NumberEntity numberTest = rattrap.analyzeNumber(540);
		int[] expectedList = new int[] {1,2,3,4,5,6,9,10,12,15,18,20,27,30,36,45,54,60,90,108,135,180,270};
		assertTrue(testHelper.listVerifyEqual(rattrap.listFactors(numberTest), expectedList));
	}
	
}


