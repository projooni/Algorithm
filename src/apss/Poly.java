package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Poly {
	
	public static int N;
	public static final int MOD = 10*1000*1000;
	public static int[][] cache;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase <= T; testCase++) {
			
			N = Integer.parseInt(br.readLine().trim());
			
			cache = new int[101][101];
			
			for(int i=0; i<101; i++) {
				for(int j=0; j<101; j++) {
					cache[i][j] = -1;
				}
			}
			
			int result = 0;
			for(int i=1; i<=N; i++) {
				result += poly(N,i);
				result %= MOD;
			}
			
			
			bw.flush();
			bw.write(result + "\n");
			
			
		}
		
		bw.close();

	}
	
	public static int poly(int n, int first) {
		// 기저 사례: n == first
		if(n==first) {
			return 1;
		}
		// 메모이제이션
		if(cache[n][first] != -1) {
			return cache[n][first];
		}
		
		int ret = 0;
		for(int second=1; second <= n-first; ++second) {
			int add = second + first - 1;
			add *= poly(n-first, second);
			add %= MOD;
			ret += add;
			ret %= MOD;
		}
		
		cache[n][first] = ret;
		
		return ret;
		
	}

}
