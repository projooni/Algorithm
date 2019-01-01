package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SamsungPreschool2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] stdArr = new int[N];
			int[] diffArr = new int[N-1];
			int preValue = 0;
			for(int i=0; i<N; i++){
				stdArr[i] = Integer.parseInt(st.nextToken());
				if(i>0){
					diffArr[i-1] = -1 * (stdArr[i] - preValue);
					preValue = stdArr[i];
				}else{
					preValue = stdArr[i];
				}
			}
			
			Arrays.sort(diffArr);
			
			int sumTotal = 0;
			int sumDiff = 0;
			for(int i=0; i<N-1; i++){
				sumTotal += diffArr[i];
				if(K > 1){
					sumDiff += diffArr[i];
					K--;
				}
				
			}
			
			System.out.println("#" + testCase + " " + (sumDiff-sumTotal));
			
		}

	}
	
}
