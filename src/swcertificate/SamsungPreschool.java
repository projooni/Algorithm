package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SamsungPreschool {

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
			for(int i=0; i<N; i++){
				stdArr[i] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + testCase + " " + doRecursive(stdArr,N,K,0,0));
			
		}

	}
	
	public static int doRecursive(int[] stdArr, int N, int K, int index, int sum){
		
		if(K == 1){
			return sum + stdArr[stdArr.length-1] - stdArr[index];
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=index; i<N-1; i++){
			int small = stdArr[index];
			int tall = stdArr[i];
			int result = doRecursive(stdArr,N,K-1,i+1,sum + tall - small);
			min = calMin(result, min);
		}
		
		
		return min;
		
	}
	
	public static int calMin(int a, int b){
		return a>=b ? b : a; 
	}
}
