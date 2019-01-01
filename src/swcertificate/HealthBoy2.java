package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HealthBoy2 {

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
			BigInteger[][] dp = new BigInteger[N][N];
			int[] injectedDate = new int[N];
			Map<Integer,int[]> map = new HashMap<Integer,int[]>();
			Arrays.fill(injectedDate, -1);
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				nArr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					dp[i][j] = BigInteger.ZERO;
				}
			}
			
			for(int i=0; i<N; i++){
				BigInteger sum = BigInteger.ZERO;
				BigInteger tired = BigInteger.ZERO;
				for(int j=i+1; j<N; j++){
//					sum += nArr[j] * (nArr[j-1] + tired);
					sum = sum.add( BigInteger.valueOf(nArr[j]).multiply( BigInteger.valueOf(nArr[j-1]).add(tired) ) );
//					tired += nArr[j-1];
					tired = tired.add( BigInteger.valueOf(nArr[j-1]));
					dp[i][j] = sum;
				}
			}
			
			BigInteger min = doRecursive(nArr, dp, N, M, 0, BigInteger.ZERO);
//			int[] resultArr = map.get(min);
			
			System.out.println("#" + testCase + " " + min);
//			for(int i=0; i<resultArr.length; i++){
//				if(resultArr[i] != -1){
//					System.out.print(" " + (i+1));
//				}
//			}
//			System.out.println("");
			
			
		}
	}
	
	public static BigInteger doRecursive(int[] nArr, BigInteger[][] dp, int N, int M, int index, BigInteger sum){
		
		if(M == 0){
//			return sum + dp[index][N-1];
			return sum.add(dp[index][N-1]);
		}
		
		BigInteger min = BigInteger.valueOf((long) 10000000000.0);
		for(int i=index+1; i<N-1; i++){
			
		
			BigInteger sumOfSection = dp[index][i];
			BigInteger result = BigInteger.ZERO;
			if(sumOfSection.compareTo(min) == -1){
				result = doRecursive(nArr,dp,N,M-1,i+1,dp[index][i]);
				min = calMin(sum.add(result), min);
			}
			
			
		}
		
		
		return min;
		
	}
	
	public static BigInteger calMin(BigInteger a, BigInteger b){
		if(a.compareTo(b) == -1){
			return a;
		}else{
			return b;
		}
		
	}
	
}
