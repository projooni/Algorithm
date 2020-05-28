package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Pretest_P_0006_Solution {
	
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
			int oneCnt = 0;
			for(int i=0; i<N; i++) {
				// N-1개의 숫자 중 남은 1의 개수를 뽑는 방법의 가짓수
				if(N-i-1 >= K-oneCnt) {
					long ord = dp[N-i-1][K-oneCnt];
					// 이 가지수가 뽑아야 할 순번보다 많이 남았다면?
					if(ord >= X) {
						ans.append(0);
					}else {
						// 첫자리가 1이라면
						ans.append(1);
						X = X - ord;
						oneCnt++;
					}
				}else {
					ans.append(1);
					oneCnt++;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

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
