package idv.aaron4157.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NumberEntity implements Serializable {

	/**
	 * Description: 癸礛计だ猂挡狦ゴ
	 * Fileds: 俱计计だ秆Α计㎝
	 * Constaints: setters 杆 ぃ idv.aaron4157.utility 砆秸ノ
	 */
	private static final long serialVersionUID = 4157L;

	public NumberEntity() {
		// JAVA Bean 惠璶把计篶
	}
	private int value; //
	private List<Integer> composition = new ArrayList<>(); //计
	private int factorSum; //计㎝

	public int getFactorSum() {
		return factorSum;
	}
	 void setFactorSum(int factorSum) {
		this.factorSum = factorSum;
	}
	public int getValue() {
		return value;
	}
	 void setValue(int value) {
		this.value = value;
	}
	public List<Integer> getComposition() {
		return composition;
	}
	 void setComposition(List<Integer> composition) {
		this.composition = composition;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		// when invoked directly, return value
		return String.valueOf(value);
	}
	
	
	
}
