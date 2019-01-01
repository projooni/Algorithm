package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class healthBoy {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] nArr = new int[N];
			int[][] dp = new int[N][N];
			int[] injectedDate = new int[N];
			Map<Integer,int[]> map = new HashMap<Integer,int[]>();
			Arrays.fill(injectedDate, -1);
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				nArr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<N; i++){
				int sum = 0;
				int tired = 0;
				for(int j=i+1; j<N; j++){
					sum += nArr[j] * (nArr[j-1] + tired);
					tired += nArr[j-1];
					dp[i][j] = sum;
				}
			}
			
			int min = doRecursive(nArr, dp, N, M, 0, injectedDate, map);
			int[] resultArr = map.get(min);
			
			System.out.print("#" + testCase + " " + min);
			for(int i=0; i<resultArr.length; i++){
				if(resultArr[i] != -1){
					System.out.print(" " + (i+1));
				}
			}
			System.out.println("");
			
			
		}
	}
	
	public static int doRecursive(int[] nArr, int[][] dp, int N, int M, int index, int[] injectedDate, Map<Integer, int[]> map){
		
		if(index >= nArr.length){
			return nArr[index-1];
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=index; i<N; i++){
			
			int result = 0;
				if(M == 0){
					result += dp[i][N-1];
					min = calMin(min, result);
					M++;
					break;
				}else{
					
					result = doRecursive(nArr, dp,N,M-1, i+1, injectedDate, map) + dp[index][i];
					int compareMin = calMin(min, result);
					
					if(compareMin < min){
						min = compareMin;
						
							if(i > 0){
								injectedDate[i-1] = -1;
							}
							injectedDate[i] = 9;
						
					}
					
					
				}
				
				if(index == 0){
					if(map.get(min) == null){
						
						map.put(min, injectedDate.clone());
					}
					
				}
				
			
		}
		
		return min;
		
		
	}
	
	public static int calMin(int a, int b){
		if(a <= b){
			return a;
		}else{
			return b;
		}
		
	}
	
}
