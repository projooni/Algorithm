package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 기출 P-0006 아름다운 비트문자열
 * 정답여부 : 정답
 * 풀이여부 : 스스로 풀었음
 * - combination 코드 암기 필요 (반복문 구현코드 이해 및 암기 필요)
 * */
public class Pretest_P_0006 {
	
	public static int N,K;
	public static long X;
	public static long dp[][];
	public static StringBuffer ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			X = Long.parseLong(st.nextToken());
			
			dp = new long[1001][1001];
			
			dp[0][0] = 1;
			dp[1][0] = 1;
			dp[1][1] = 1;
			for(int i=2; i<=1000; i++) {
				dp[i][0] = 1;
				dp[i][i] = 1;
				for(int j=1; j<i; j++) {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
					if(dp[i][j] < 0) dp[i][j] = Long.MAX_VALUE;
				}
			}
			
			ans = new StringBuffer();
			search(N, K, X);
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

	}
	
	public static void search(int n, int k, long x) {
		
		if(k == 0) {
			for(int j=0; j<=n; j++) {
				ans.append(0);
			}
			return;
		}
		
		int i = k;
		while(true) {
			long c = dp[i][k];
			if(x < c) {
				
				for(int j=0; j<=n-i; j++) {
					if(j != n-i) {
						ans.append(0);
					}else {
						ans.append(1);
					}
				}
				
				long p = dp[i-1][k];
				search(i-1, k-1, x-p);
				break;
			}else if(x == c) {
				
				for(int j=0; j<n; j++) {
					if(j >= n-i && j < n-i+k) {
						ans.append(1);
					}else {
						ans.append(0);
					}
				}
				break;
			}
			
			i++;
		}
		
	}
	
	public static long combination(int n, int k) {
		
		if(n==0) {
			return 0;
		}
		
		if(n==k || k==0) {
			return 1;
		}
		
		if(dp[n][k] == 0) {
			dp[n][k] = combination(n-1, k-1) + combination(n-1, k);
		}
		
		return dp[n][k];
		
	}

}
