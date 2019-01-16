package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A_0023 {
	
	public static int N;	// 5이상 34이하
	public static int K;	// 최대 10억
	public static int cards[];
	public static boolean visited[];
	public static long dp[][][];
	public static long result;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase <= T; testCase++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			cards = new int[N];
			visited = new boolean[N];
			dp = new long[N+1][34*N][2];
			
			for(int i=0; i<N+1; i++) {
				for(int j=0; j<34*N; j++) {
					dp[i][j][0] = -1;
					dp[i][j][1] = -1;
				}
			}	
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<cards.length; i++) {
				cards[i] = Integer.parseInt(st.nextToken());
			}
			
			result = 1;
			result += recursive(N,K);
			
			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
			
		}
		bw.close();

	}
	
	public static long recursive(int n, int k) {
		
		int isNegative = k<0?1:0;
		
		if(k == 0) {
			return 1;
		}
		
		if(dp[n][isNegative == 1?(k*-1):k][isNegative] != -1) {
			return dp[n][isNegative == 1?(k*-1):k][isNegative];
		}
		
		long ret = 0;
				
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				ret += recursive(n-1, k-cards[i]);
				visited[i] = false;
			}
		}
		dp[n][isNegative == 1?(k*-1):k][isNegative] = ret;
		
		return ret;
		
	}
	

}
