package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Asymtiling {
	
	public static int N;
	public static long cache[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			cache = new long[N+1];
			
			for(int i=0; i<cache.length; i++) {
				cache[i] = -1;
			}
			
			long result = tiling(N);
			long dup = tiling((N/2)+1);
			
			bw.flush();
			
			// 1000000007 로 나눈 나머지를 빼면 음수가 나올 수 있으므로 MOD를 더했다가 나누는 기법을 사용한다.
			// 예를들어 MOD+1 만큼의 경우의수 - 100 만큼의 경우의수를 하면 모듈라연산에의해 -99가 나온다.
			// MOD+1-100 이므로 -99에 MOD를 더하면 제대로 된 경우의 수가 나온다. 초과될 수 있으므로 다시 MOD로 나눈다.
			bw.write((result-dup+1000000007)%1000000007 + "\n");
		}
		bw.close();
	}
	
	public static long tiling(int n) {
		
		if(n <= 1) {
			return 1;
		}
		
		if(cache[n] != -1) {
			return cache[n];
		}
		
		cache[n] = (tiling(n-1) + tiling(n-2))%1000000007;
		
		return cache[n];
		
	}

}
