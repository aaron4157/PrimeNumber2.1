package idv.aaron4157.demo;

import java.io.IOException;

import idv.aaron4157.maximals.OptimusPrime;
import idv.aaron4157.maximals.Rhinox;

public class TestEncryption {

	/**
	 * 測試加密物件Rhinox與相關功能
	 * */
	public static void main(String[] args) {
		
		OptimusPrime optimusPrimal  = null;
		
		try {
			optimusPrimal = new OptimusPrime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//產生加密金鑰的種子
		/*
		 * int ppairs[] = optimusPrimal.lottery();
		 * System.out.println(ppairs[0]+","+ppairs[1]);
		 */
		
		Rhinox autobot1 = new Rhinox(optimusPrimal);
		//產生加密金鑰 自動更新
		/*
		 * autobot1.keyGen(); autobot1.keyGen();
		 */
		 
		
		//Only English is allowed for example key		
		String encrypted = autobot1.enctypt("玄之又玄 ，眾妙之門"); //輸出祕文
		System.out.println(encrypted); autobot1.decrypt(encrypted);//主控台輸出還原的明文				 
		  
	}

}
