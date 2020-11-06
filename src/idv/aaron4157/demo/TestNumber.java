package idv.aaron4157.demo;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import idv.aaron4157.utility.Cheetor;
import idv.aaron4157.utility.NumberEntity;
import idv.aaron4157.utility.OptimusPrime;

public class TestNumber {

	public static void main(String[] args) {
		// ��@�@�ӯ��ƾ�(OptimusPrime)����
		try {
			OptimusPrime autobot = new OptimusPrime();
			NumberEntity a1 = autobot.analyzeNumber(68);
			System.out.println(a1.getValue()); 
			System.out.println(a1.getComposition()); 
			System.out.println(a1.getFactorSum()); 
			autobot.close();
			
			//��@�@�ӱ���(Cheetor)����
			Cheetor aotobot2=new Cheetor();
			List<Integer> list1 = aotobot2.listFactors(a1);
			list1.sort(Comparator.naturalOrder()) ;
			System.out.println(list1);

		} catch (IOException e) {
			// �إ��ɮ׹J����D
			System.out.println(e.getMessage());
		}
		

	}

}
