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
 * 유형       : DP
 * 정답여부  : O
 * 기여도    :  0% (다른사람 소스)
 * 기타
 *   
 * */

public class Sajeon_A_0017_Solution {
	
	public static int N,K,M, Answer, board[];
	public static int count[] = new int[7];
	public static int[] position = new int[6*6*6*6*6*6];
	public static int[][] dp = new int[7][6*6*6*6*6*6];
	public static int[] add = {0, 1, 6, 6*6, 6*6*6, 6*6*6*6, 6*6*6*6*6};
	public static final int INF = 987564321;

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
			board = new int [N];
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0; i<N; i++) {
				board[i] = Integer.parseInt(st.nextToken());
			}
			
			Answer = getAnswer();
			
			bw.flush();
			bw.write("#" + tc + " " + Answer + "\n");
		}
		bw.close();
		

	}
	
	public static int getAnswer() {
		
		for(int i=0; i<=K; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		Arrays.fill(count, 0);
		int ans = solve(0,0,0) - board[0];
		return ans;
		
	}
	
	public static int solve(int pre, int num, int pos) {
		
		if(pos >= N) {
			return board[pos-N];
		}
		
		if(dp[pre][num] != -1) {
			return dp[pre][num];
		}
		
		int ret = INF;
		for(int i=1; i<=K; i++) {
			if(i == pre || count[i] == M) {
				continue;
			}
			count[i]++;
			ret = Math.min(ret, solve(i, num+add[i], pos+i) + board[pos]);
			count[i]--;
		}
		
		return dp[pre][num] = ret;
		
	}

}
