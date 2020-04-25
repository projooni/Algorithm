package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class bj6588 {
	
	public static int N;
	public static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(N != 0) {
			N = Integer.parseInt(br.readLine());
			
			eratosthenes();
			int result[] = solve();
			
			bw.flush();
			bw.write(N + " = " + result[0] + " + " + result[1]);
		}

	}
	
	public static void eratosthenes() {
		
		arr = new int[N+1];
		arr[0] = -1;
		arr[1] = -1;
		for(int i=2; i<arr.length; i++) {
			arr[i] = i;
		}
		
		for(int i=2; i<arr.length; i++) {
			for(int j=i+i; j<arr.length; j+=i) {
				arr[j] = -1;
			}
		}
		
	}
	
	public static int[] solve() {
		int ret[] = new int[2];
		ret[0] = -1;
		ret[1] = -1;
		
		for(int i=arr.length-1; i>3; i--) {
			if(arr[i] == -1) {
				continue;
			}
			for(int j=3; j<arr.length; j++) {
				if(arr[j] == -1) {
					continue;
				}
				
				if(arr[j] + arr[i] == N) {
					ret[0] = j;
					ret[1] = i;
				}
				
			}
		}
		
		return ret;
		
	}

}
