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
 *�غc�l:���J���ƪ�w�sbuffer size�ۭq
 * ���ƪ���@�G��s�C��üg�^�h
 * ��sbuffer�G���^��
 * �P�_����:�Y�����n�䤣��]�ơA�^��true�Fbuffer �Χ��N��s�F�ߥX"���դ�����"�ҥ~
 * ���]�Ƥ���:�`�����k�A�^�ǦC��
 * ������:�ھڦC��p����]�ƩM�m(n)
 * 
 * ��sbean:�ߤ@��~���f�A�����æ^��numbean����
 * @author aaron
 */
public class OptimusPrime {
	private int bufferSize = 100;
	private String filePath = "PrimeList.txt";
	private Path ppath = null;
	private BufferedReader pReader;
	private int[] pBuffer = new int[bufferSize];	

	public OptimusPrime() throws IOException {
		// ��l�� �ˬd�ɮרø��J
		ppath = Paths.get(filePath); //���M�ת��ڥؿ�
		if(ppath.toFile()==null) {
			mkFile(1000);			
		}
		pReader=Files.newBufferedReader(ppath);
		loadBuffer(1);		
	}

    private void mkFile(int upper){
        //���Ͳ�����upper number�����ƪ�
        List<Integer> primeList = new ArrayList<>();
        int i = 2;//����Ʊ��y
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
        
        //��J�ɮ�
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
		// ���J�C���w�s
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
			System.out.println("��Ʈ榡����!");
		} catch (IOException e) {
			System.out.println("Ū�ɹJ����D!");
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
        int s = 0; //�L �U�����l
        int m = pBuffer[s]; //�� �U������

        while(y > 1){
            if(y%m ==0) {
                factorList.add(m);
                y = y/m;
            }else if(s==bufferSize) {
                //buffer �Χ� ����j��?            	
            	loadBuffer(m);
            	s = 0;
            }
            else m = pBuffer[s++];//go to next prime number
        }
        if(s == bufferSize) System.out.println("buffer�e�q�ݭn�X�W");
        return factorList;
    }
	
    public int perfectness(List<Integer> input){
        input.add(1); //last element dosn't enter iteration
        int sub = 1;
        int previous = 1;
        int temp = 1;
        int total = 1;//�B��L�� total=sigma(X)
        int X = 1;//�B��L��AX= ��Ӫ���
        
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
        input.remove((input.indexOf(1)));//�ǰѦҡA�Χ���n�٭�
        System.out.println("�٭�: "+X);
        return total;        
    }

    public void close() {
    	try {
			pReader.close();			
		} catch (IOException e) {
			// �p�G�}�ɥ��ѴN�S���귽�i�H����
			e.printStackTrace();
		}    	
    }
}
