package apss.ch10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Lunchbox {
	
	public static int N;
	public static int[][] arr;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			bw.flush();
			bw.write(0 + "\n");
		}
		bw.close();

	}
	
	// 점심을 먹는데 걸리는 최소시간 
	public static long greed() {
		
		long burn = 0;
		long eat = 0;
		
		for(int i=0; i<arr.length; i++) {
			burn += arr[i][0];
			eat = Math.max(eat, burn + arr[i][1]);
		}
		
		return eat;
		
		
	}

}
