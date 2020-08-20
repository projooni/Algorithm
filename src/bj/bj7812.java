package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * [백준-7812] 중앙 트리
 * 유형 : DFS, DP?, AD-HOC
 * 자력풀이정도 : 10%
 * 참고 : https://www.crocus.co.kr/699
 * 기타 의견 : 
 *  - 8월 8일 SW검정 시험을 보고 난 후, 동일한 유형의 문제를 풀어봄.
 * */

public class bj7812 {
	
	public static int N;
	public static List<int[]> adjList[];
	public static int[] cnt;
	public static long[] sum;
	public static long ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while(true){
			
			N = Integer.parseInt(br.readLine().trim());
			
			if(N == 0) {
				break;
			}
			
			cnt = new int[N];
			sum = new long[N];
			
			adjList = new ArrayList[N];
			for(int i=0; i<N; i++) {
				adjList[i] = new ArrayList<int[]>();
			}
			
			for(int i=0; i<N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				adjList[a].add(new int[] {b,w});
				adjList[b].add(new int[] {a,w});
			}
			
			ans = Long.MAX_VALUE;
			getTree(0, -1);
			getSum(0, -1, sum[0]);
			
			bw.flush();
			bw.write(ans + "\n");
		}
		bw.close();

	}
	
	public static void getTree(int here, int prev) {
		cnt[here] = 1;
		
		for(int i=0; i<adjList[here].size(); i++) {
			int next = adjList[here].get(i)[0];
			int cost = adjList[here].get(i)[1];
			
			if(next == prev) {
				continue;
			}
			
			getTree(next, here);
			
			cnt[here] += cnt[next];
			sum[here] += cost * cnt[next] + sum[next];
			
		}
	}
	
	public static void getSum(int here, int prev, long total) {
		
		ans = Math.min(ans, total);
		
		for(int i=0; i<adjList[here].size(); i++) {
			int next = adjList[here].get(i)[0];
			int cost = adjList[here].get(i)[1];
			
			if(next == prev) {
				continue;
			}
			
			getSum(next, here, total - (cnt[next]*cost) + ((N-cnt[next])*cost));
		}
		
	}
	

}
