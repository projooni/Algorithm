package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class destinedToBeTogether {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			System.out.println("Test Case : " + test_case);
			System.out.println("N : " + N);
			System.out.println("M : " + M);
			System.out.println("K : " + K);
			
			for(int i=0; i<K; i++){
				
				StringTokenizer st2 = new StringTokenizer(br.readLine()); 
				
				int startNode = Integer.parseInt(st2.nextToken());
				int endNode = Integer.parseInt(st2.nextToken());
				double ratio = Double.parseDouble(st2.nextToken());
				
				System.out.println(startNode + " " + endNode + " " + ratio);
				
				
				
			}
			
			
			
			

		}
		
	}


}
