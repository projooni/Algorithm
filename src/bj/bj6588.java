package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class bj6588 {
	
	public static int N;
	public static boolean arr[];
	public static List<Integer> arrList = new ArrayList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true){
			N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			
			eratosthenes();
			String result = solve();
			
			bw.flush();
			bw.write(result);
			
		}
		bw.close();

	}
	
	public static void eratosthenes() {
		
		arr = new boolean[N+1];
		arr[0] = false;
		arr[1] = false;
		for(int i=2; i<arr.length; i++) {
			arr[i] = true;
		}
		
		for(int i=2; i<arr.length; i++) {
			if(!arr[i]) {
				continue;
			}
			for(int j=i+i; j<arr.length; j+=i) {
				if(!arr[j]) {
					continue;
				}
				arr[j] = false;
			}
		}
		
		for(int i=3; i<arr.length; i++) {
			if(arr[i] && i%2 == 1) {
				arrList.add(i);
			}
		}
		
		
	}
	
	public static String solve() {
		String ret = "Goldbach's conjecture is wrong.\n";
		
		for(int i=0; i<arrList.size(); i++) {
			int value = arrList.get(i);
			if(arr[N-value]) {
				ret = N + " = " + value + " + " + (N-value) + "\n";
				break;
			}
		}
		
		/*
		for(int i=2; i<=N/2; i++) {
			if((arr[i] != -1) && (arr[N-i] != -1)) {
				ret[0] = N-i;
				ret[1] = i;
				break;
			}
		}
		*/
		
//		for(int i=arrList.size()-1; i>=0; i--) {
//			int max_value = arrList.get(i);
//			for(int j=0; j<arrList.size(); j++) {
//				if(i == j) {
//					continue;
//				}
//				int min_value = arrList.get(j);
//				if(min_value + max_value == N) {
//					ret[0] = max_value;
//					ret[1] = min_value;
//				}
//			}
//		}
		
		return ret;
		
	}

}
