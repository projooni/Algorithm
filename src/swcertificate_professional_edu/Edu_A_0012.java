package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * [교육A-0012] 보석 털이
 * 유형 : DP, knapsack
 * 정답여부 : 오담(0/20) 
 * 모르겠음 하나도 안맞는 이유
 * 
 * 다른방법
 * 1) Toggling
 * D[N%2][W] = max(D[(N-1)%2][W], D[(N-1)%2][W-w[N]] + v[N])
 * 2) 1차원 배열 사용
 * D[W] = max(D[W], D[W-w[N] + v[N]])
 * for i = 1 ~ N
 * 		for j = W ~ w[i]
 * */
public class Edu_A_0012 {
	
	public static int N,M;
	public static int C[], W[];
	public static long dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			C = new int[N+1];
			W = new int[N+1];
			dp = new long[N][M+1];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int c = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				C[i] = c;
				W[i] = w;
			}
			
			for(int m=0; m<=M; m++) {
				for(int i=1; i<N; i++) {
					if(m >= W[i]) {
						dp[i][m] = Math.max(dp[i-1][m], dp[i-1][m-W[i]] + C[i]);
					}
				}
			}
			
			long max = 0;
			for(int i=0; i<N; i++) {
				max = Math.max(dp[i][M], max);
			}
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}

}
