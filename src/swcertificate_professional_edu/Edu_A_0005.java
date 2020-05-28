package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*내가 한 정답*/
public class Edu_A_0005 {
	
	public static int Q;
	public static long dp[][];
	public static final int MOD = 1000000007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		dp = new long[5001][5001];
		
		int Q = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=Q; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			bw.flush();
			bw.write("#" + tc + " " + combination(n,k) +  "\n");
		}
		bw.close();

	}
	
	public static long combination(int n, int k) {
		
		if(n==k || k == 0) {
			return 1;
		}
		
		if(dp[n][k] == 0) {
			dp[n][k] = (combination(n-1,k-1) + combination(n-1, k))%MOD;
		}
		
		return dp[n][k];
	}

}
