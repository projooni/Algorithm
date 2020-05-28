package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*
 * [교육A-0010] 가장 큰 정사각형
 * 유형 : DP
 * 스스로 풀이 여부 : 인터넷에서 점화식을 보고 말았음
 * 이해도 : 점화식이 이해가지 않음
 * 다시 풀이여부 : 반드시 필요(아무것도 참고하지 않고)
 * */
public class Edu_A_0010 {
	
	public static int N,M;
//	public static int arr[][];
	public static int dp[][];

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
			
//			arr = new int[N+1][M+1];
			dp = new int[N+1][M+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=1; j<=N; j++) {
					dp[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = dp[i][j] == 0 ? 1 : 0;
				}
			}
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(dp[i][j] == 1) {
						dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
					}
				}
			}
			
			int max = 0;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					max = Math.max(dp[i][j], max);
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}

}
