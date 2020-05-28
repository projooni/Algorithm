package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 기출P-0053 공주의 결혼식
 * 푸는중..
 * */
public class Pretest_P_0053 {
	
	public static int W, B;
	public static int dp[][];
	public static double rate_w, rate_b, rate_n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
//			dp = new int[W+B+1][W+B+1];
//			combination();
//			
//			while(true) {
//				
//				
//				
//			}
//			
//		
//			bw.flush();
//			bw.write("#" + tc + "\n");
		}
		bw.close();
		
	}
	
	public static void combination() {
		dp[0][0] = 1;
		dp[1][0] = 1;
		dp[1][1] = 1;
		for(int i=2; i<=1000; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
			for(int j=1; j<i; j++) {
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
	}
	
//	public static void search(int w, int b) {
//		
//		if(W+B == 1) {
//			rate_w = 0;
//			rate_b = 0;
//			rate_n = 1;
//			return;
//		}
//		
//		while(true) {
//			// 백마 1명, 흑마 1명 -> 둘다 집에간다
//			rate_w *= dp[w][1]/dp[w+b][2] * dp[b][1]/dp[w+b][2];
//			search(w-1, b-1);
//			
//			rate_1 *= dp[w][1]/dp[w+b][2] * dp[b][1]/dp[w+b][2];
//			search(w-1, b-1);
//			
//		}
//		
//	}

}
