package idv.aaron4157.demo;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import idv.aaron4157.maximals.NumberEntity;
import idv.aaron4157.maximals.OptimusPrime;
import idv.aaron4157.maximals.Rattrap;

public class TestNumber {

	public static void main(String[] args) {
		OptimusPrime autobot = null;
		HashMap<String, int[]> gList;
		try {
			autobot = new OptimusPrime();
			
			gList = autobot.listGoldBach(78);
			//System.out.println(autobot.listGoldBach(78));
			gList.forEach((key,elem) -> System.out.println(key+": "+elem[0]+","+elem[1]));			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//依賴注入的實例
		Rattrap autobot2 = new Rattrap(autobot);
		//產生自然數的資料物件 查看結果
		NumberEntity a1 = autobot2.analyzeNumber(79);
		System.out.println(a1.getValue()); 
		System.out.println(a1.getComposition()); 
		System.out.println(a1.getFactorSum()); 
		List<Integer> list1 = autobot2.listFactors(a1);
		list1.sort(Comparator.naturalOrder());
		System.out.println(list1);
		
		autobot.close();
		

	}

}
