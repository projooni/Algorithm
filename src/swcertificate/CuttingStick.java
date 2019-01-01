package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CuttingStick {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
					System.setIn(new FileInputStream("D:\\sample_input.txt"));
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					
					int C = Integer.parseInt(br.readLine().trim());
					
					for(int test_case=1; test_case<=C; test_case++){
						int N = Integer.parseInt(br.readLine().trim());
						BigInteger cache[] = new BigInteger[N+1];
						Arrays.fill(cache, BigInteger.valueOf(-1));
						
						StringTokenizer st = new StringTokenizer(br.readLine());
						
						int arr[] = new int[N+1];
						for(int i=1; i<N+1; i++){
							arr[i] = Integer.parseInt(st.nextToken());
						}
						
						System.out.println("#" + test_case + " " + doProcess(N,cache,arr));
					}

	}
	
	public static BigInteger doProcess(int N, BigInteger[] cache, int[] arr){
		
		if(N == 1){
			return BigInteger.valueOf(arr[1]);
		}
		
		BigInteger max = BigInteger.ONE;
		for(int i=0; i<=(N/2); i++){
			
			if(i == 0){
				max = BigInteger.valueOf(arr[N]);
				continue;
			}else{
				BigInteger value1 = cache[i].compareTo(BigInteger.valueOf(-1)) != 0 ? cache[i] : doProcess(i, cache, arr);
				BigInteger value2 = cache[N-i].compareTo(BigInteger.valueOf(-1)) != 0 ? cache[N-i] : doProcess(N-i, cache, arr);
				BigInteger sum = value1.add(value2);
				
				max = max.compareTo(sum) == 1 ? max : sum;
			}
			
		}
		
		cache[N] = max;
		return max;
		
	}

}
