package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 5일차 >
 *  
 * 문제명    : [사전A-0017] 보드게임2
 * 난이도    : 중
 * 유형       : 
 * 정답여부  : X (푸는중)
 * 기여도    : 
 * 기타
 *   
 * */

public class Sajeon_A_0017 {
	
	public static int N,K,M;
	public static int board[];
	public static int dp[][];
	public static int k[];
	public static final int MAX = 987564321;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			board = new int [N+1];
			dp = new int [N+K+1 + 1][K+1];
			k = new int[K+1];
			
			Arrays.fill(k, M);
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<=N; i++) {
				board[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<=N+K+1; i++) {
				
				for(int j=1; j<=K; j++) {
					dp[i+1][j] = -1;
				}
				
				if(i >= 0 && i <=K) {
					dp[i+1][1] = board[i+1];
				}
				
			}

			int ans = search(N+K, -1);
			
//			int min = Integer.MAX_VALUE;
//			for(int i=N; i<=N+K; i++) {
//				min = Math.min(min, dp[i]);
//			}
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();
		

	}
	
	public static int search(int idx, int prev) {
		
		if(dp[idx][prev] != -1) {
			return dp[idx][prev];
		}
		
		int min = MAX;
		for(int i=1; i<=K; i++) {
			
			if(prev == i) {
				continue;
			}
			
			if(k[i] == 0) {
				continue;
			}
			
			int prevMin = 0;
			k[i]--;
			prevMin = search(idx - i, i);
			k[i]++;
			
//			if(prevMin != Integer.MAX_VALUE) {
				min = Math.min(min, prevMin + board[(idx%N == 0 ? N : idx%N)]);
//			}
			
		}
		
//		if(min != Integer.MAX_VALUE) {
			dp[idx][prev] = min;
//		}
		return min;
		
	}

}
