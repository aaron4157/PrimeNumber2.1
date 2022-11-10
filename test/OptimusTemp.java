import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import idv.aaron4157.maximals.OptimusPrime;

/**
 * 
 */

/**
 * This stub class provides all public/package methods from OptimusPrime,
 * without real operations on data system or inner logics.
 * The outputs types adhere to the super class.
 * In most cases, OptimusPrime and its stub are implementation from an interface, which is the input data type for the maximals.
 * @author aaron
 *
 */
class OptimusTemp extends OptimusPrime {

	/**
	 * @throws IOException
	 */
	public OptimusTemp() throws IOException {
		// TODO Auto-generated constructor stub
		System.out.println("stub initialized.");
	}

	@Override
	public List<Integer> factorize(int x) {
		// TODO Auto-generated method stub
		List<Integer> result = new ArrayList<>();
		result.add(2);
		result.add(3);
		return result;
	}

	@Override
	public int factorSum(List<Integer> input) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public HashMap<String, int[]> listGoldBach(int testNumber) {
		// TODO Auto-generated method stub
		Map<String, int[]> result = new HashMap<>(); 
		result.put("test1", new int[] {1,2});
		return super.listGoldBach(testNumber);
	}

	@Override
	public int[] lottery() {
		// TODO Auto-generated method stub
		return new int[] {3,7};
	}
	
	

}
