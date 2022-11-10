import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * 抽出測試流程的驗證邏輯，使用委派(delegate)方法明確測試的上下文
 * 
 * */
public class TestHelper {
	<N> boolean listVerifyEqual(List<N> tested, int[] answer) {
		IntStream.of(answer).forEach(e -> tested.remove(tested.indexOf(e)));
		return tested.isEmpty();
	}
	
	boolean arrayVerifyEqual(int[] tested, int[] answer) {
		return false;
	}
	
	<K, V> boolean mapVerifyEqual(Map<K, V> tested, Map<K, V> answer) {
		answer.keySet().forEach(key -> {
			if(tested.containsKey(key)) {
				Object value = tested.remove(key);
				
			}		
		});
		return tested.isEmpty();
	}
}
