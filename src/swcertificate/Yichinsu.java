package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Yichinsu {

		public static void main(String[] args) throws NumberFormatException, IOException {
			// TODO Auto-generated method stub
			
			System.setIn(new FileInputStream("D:\\sample_input.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			int C = Integer.parseInt(br.readLine().trim());
			
			for(int test_case=1; test_case<=C; test_case++){
				int N = Integer.parseInt(br.readLine().trim());
				BigInteger cache[] = new BigInteger[N+1];
				
				Arrays.fill(cache, BigInteger.valueOf(-1));
				cache[0] = BigInteger.ONE;
				cache[1] = BigInteger.ONE;
				
				
				
				System.out.println("#" + test_case + " " + doProcess(N,cache));
			}
			
			
		}
		
		public static BigInteger doProcess(int N, BigInteger[] cache){
			
			if(N == 0 || N==1 || N==2){
				return BigInteger.ONE;
			}
			
			BigInteger sum = BigInteger.ZERO;
			for(int i=0; i<=N-2; i++){
				sum = sum.add(cache[i].compareTo(BigInteger.valueOf(-1)) != 0 ? cache[i] : doProcess(i, cache));
			}
			cache[N] = sum;
			
			
			return cache[N];
			
		}
		
		


}
