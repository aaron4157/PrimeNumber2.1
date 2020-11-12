package idv.aaron4157.maximals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NumberEntity implements Serializable {

	/**
	 * Description: ���@�Ӧ۵M�ƪ����R���G���]
	 * Fileds: �@�Ӿ�ƪ��ȡB���]�Ƥ��Ѧ��B���]�ƩM
	 * Constaints: setters �ʸ� ����b idv.aaron4157.maximals �H�~�Q�ե�
	 */
	private static final long serialVersionUID = 4157L;

	public NumberEntity() {
		// JAVA Bean �ݭn�ŰѼƫغc�l
	}
	private int value; //�D��
	private List<Integer> composition = new ArrayList<>(); //���]�ƪ�
	private int factorSum; //���]�ƩM

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
