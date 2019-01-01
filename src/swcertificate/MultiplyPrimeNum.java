package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplyPrimeNum {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase = 1; testCase <= T; testCase++){
			int K = Integer.parseInt(br.readLine().trim());
			int N = Integer.parseInt(br.readLine().trim());
			
			int[] primeNumArr = new int[K+1];
			for(int i = 1; i <= K; i++){
				primeNumArr[i] = Integer.parseInt(br.readLine().trim());
			}
			
			// ���ؾ� �� �ε������� �迭
			int[] idxArr = new int[K+1];
			Arrays.fill(idxArr, 1);
			
			List<Long> resultList = new ArrayList<Long>();
			resultList.add((long)1);
			
			while(resultList.size() <= N){
				
				long min = Long.MAX_VALUE;
				int minIdx = 0;
				long value = 0;
				for(int j = 1; j <= K; j++){
					value = primeNumArr[j] * resultList.get(idxArr[j]-1);
					
					if( value < min){
						min = value;
						minIdx = j;
						
					}
					
				}
				
				idxArr[minIdx]++;
				if(resultList.get(resultList.size()-1) != min){
					resultList.add(min);
				}
				
				
			}
			
			
			bw.write("#" + testCase + " " + resultList.get(resultList.size()-1));
			bw.flush();
			
			
		}
		
		bw.close();

	}

}
