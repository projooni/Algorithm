package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 5일차 >
 *  
 * 문제명    : [사전A-0033] 스마트 팩토리
 * 난이도    : 중
 * 유형       : DP
 * 정답여부  : O (강사소스)
 * 기여도    :  0%
 * 기타			: 
 *   
 * */

public class Sajeon_A_0033_Solution {
	
	public static final int MAX_N = 5000;
	public static final int MAX_M = 100;
	public static final int MAX_K = 5;
	
	public static int N,M,K;
	public static char map[][] = new char[MAX_M+1][MAX_N+1];
	public static char emp[] = new char[MAX_K];
	public static int dp[][] = new int[MAX_K+1][MAX_N+1];
	public static int Count[][] = new int[26][MAX_N+1];
	
	public static int max;
	public static boolean visited[];
	public static int k_order[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			char[] c;
			
			emp = br.readLine().toCharArray();
			
			for(int m=1; m<=M; m++) {
				c = br.readLine().toCharArray();
				for(int n=1; n<=N; n++) {
					map[m][n] = c[n-1];
				}
			}
			
			for(int a=0; a<26; a++) {
				for(int n=1; n<=N; n++) {
					Count[a][n] = 0;
				}
			}
			
			for(int n=1; n<=N; n++) {
				for(int m=1; m <= M; m++) {
					Count[map[m][n] - 'A'][n]++;
				}
			}
			
			max = 0;
			
			visited = new boolean[K+1];
			k_order = new int[K+1];
			backtrack(0,0,k_order);
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}
	
	public static void backtrack(int index, int order, int[] k_order) {
		if(order == K) {
			
			for(int n=1; n<=N; n++) {
				for(int k=0; k<K; k++) {
					if(k-1 >= 0) {
						dp[k_order[k]][n] = Math.max(dp[k_order[k-1]][n], dp[k_order[k]][n-1] + Count[emp[k_order[k]]-'A'][n]);
					}else {
						dp[k_order[k]][n] = dp[k_order[k]][n-1] + Count[emp[k_order[k]]-'A'][n];
					}
					
					if(n==N) {
						max = Math.max(max, dp[k_order[k]][N]);
					}
					
				}
			}
			
			for(int n=1; n<=N; n++) {
				for(int k=0; k<K; k++) {
					dp[k][n] = 0;
				}
			}
			
		}
		
		for(int k=0; k<K; k++) {
			if(!visited[k]) {
				visited[k] = true;
				k_order[order] = k;
				backtrack(k, order+1, k_order);
				visited[k] = false;
			}
		}
		
	}
	

}
