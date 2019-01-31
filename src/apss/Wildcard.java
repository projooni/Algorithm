package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Wildcard {
	
	public static String W;
	public static int N;
	public static String fileName[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream(""));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			W = br.readLine().trim();
			N = Integer.parseInt(br.readLine().trim());
			
			for(int i=0; i<N; i++) {
				fileName[i] = br.readLine().trim();
			}
			
			
			
			
			
		}
		
		

	}

}
