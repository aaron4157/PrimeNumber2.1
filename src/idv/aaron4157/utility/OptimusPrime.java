package idv.aaron4157.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *建構子:載入素數表緩存buffer size自訂
 * 素數表維護：更新列表並寫回去
 * 更新buffer：不回傳
 * 判斷素數:若直到√n找不到因數，回傳true；buffer 用完就更新；拋出"測試不完全"例外
 * 素因數分解:循環除法，回傳列表
 * 完美數:根據列表計算全因數和σ(n)
 * 
 * 更新bean:唯一對外接口，接收並回傳numbean物件
 * @author aaron
 */
public class OptimusPrime {
	private int bufferSize = 100;
	private String filePath = "PrimeList.txt";
	private Path ppath = null;
	private BufferedReader pReader;
	private int[] pBuffer = new int[bufferSize];	

	public OptimusPrime() throws IOException {
		// 初始化 檢查檔案並載入
		ppath = Paths.get(filePath); //找到專案的根目錄
		if(ppath.toFile()==null) {
			mkFile(1000);			
		}
		pReader=Files.newBufferedReader(ppath);
		loadBuffer(1);		
	}

    private void mkFile(int upper){
        //產生略高於upper number的素數表
        List<Integer> primeList = new ArrayList<>();
        int i = 2;//正整數掃描
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
        }while(i<= upper);
        
        //押入檔案
        try {
            BufferedWriter printerOutput = Files.newBufferedWriter(ppath);            
            for(int j:primeList){
                printerOutput.write(j);
                printerOutput.newLine();
            }
            
            printerOutput.flush();
            printerOutput.close();
            
        } catch (IOException ex) {
            System.out.println("Cannot create the list!\n"+ex);
        }       
        
        System.out.println("Done!");
    }


	private void loadBuffer(int start) {
		// 載入列表到緩存
		try {
			pReader = Files.newBufferedReader(ppath);
			int s=0;
			do {
				String line = pReader.readLine();
				int p1=Integer.parseInt(line);
				if(line!=null) {
					if(p1>start) { 
						pBuffer[s++] = p1;
					}
				}else {
					//EOF
					break;
				}
			}while(s<bufferSize); //EOB
			System.out.println("Load success!");			
			pReader.close();
		} catch (NumberFormatException e) {
			System.out.println("資料格式不對!");
		} catch (IOException e) {
			System.out.println("讀檔遇到問題!");
		}
		
	}
	
	
	public NumberEntity analyzeNumber(int a) {
		NumberEntity numbean = new NumberEntity();
		numbean.setValue(a);
		numbean.setComposition(factorize(a));
		int fSum=perfectness(numbean.getComposition());
		numbean.setFactorSum(fSum);		
		return numbean;
		
	}
	
	public List<Integer> factorize(int x){
        List<Integer> factorList = new ArrayList<>();
        loadBuffer(1);
        
        int y = x;
        int s = 0; //無 萬物之始
        int m = pBuffer[s]; //有 萬物之母

        while(y > 1){
            if(y%m ==0) {
                factorList.add(m);
                y = y/m;
            }else if(s==bufferSize) {
                //buffer 用完 應對大數?            	
            	loadBuffer(m);
            	s = 0;
            }
            else m = pBuffer[s++];//go to next prime number
        }
        if(s == bufferSize) System.out.println("buffer容量需要擴增");
        return factorList;
    }
	
    public int perfectness(List<Integer> input){
        input.add(1); //last element dosn't enter iteration
        int sub = 1;
        int previous = 1;
        int temp = 1;
        int total = 1;//運算過後 total=sigma(X)
        int X = 1;//運算過後，X= 原來的數
        
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
            
            X *= p; 
        }
        input.remove((input.indexOf(1)));//傳參考，用完後要還原
        System.out.println("還原: "+X);
        return total;        
    }

    public void close() {
    	try {
			pReader.close();			
		} catch (IOException e) {
			// 如果開檔失敗就沒有資源可以關閉
			e.printStackTrace();
		}    	
    }
}
