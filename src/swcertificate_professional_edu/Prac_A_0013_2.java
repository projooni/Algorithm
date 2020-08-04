package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 2020-08-04
 * [연습A-0013] 피크닉 2번째 풀이
 * */
public class Prac_A_0013_2 {
	
	public static int K,N,M;
	public static int sp[];
	public static List<Integer> adjList[];
	public static int count[];
	public static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sp = new int[K+1];
			adjList = new ArrayList[N+1];
			count = new int[N+1];
			visited = new boolean[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=K; i++) {
				sp[i] = Integer.parseInt(br.readLine().trim());
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList[s].add(e);
			}
			
			bfsAll();
//			dfsAll();
			
			int ans = 0;
			for(int i=1; i<=N; i++) {
				if(count[i] == K) {
					ans++;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

	}
	
	public static void bfsAll() {
		for(int i=1; i<=K; i++) {
			visited = new boolean[N+1];
			visited[sp[i]] = true;
			bfs(sp[i]);
		}
	}
	
	public static void bfs(int start) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			count[curr]++;
			for(int i=0; i<adjList[curr].size(); i++) {
				int next = adjList[curr].get(i);
				if(!visited[next]) {
					visited[next] = true;
					bfs(next);
				}
			}
		}
		
	}
	
	
	public static void dfsAll() {
		for(int i=1; i<=K; i++) {
			visited = new boolean[N+1];
			visited[sp[i]] = true;
			dfs(sp[i]);
		}
	}
	
	public static void dfs(int start) {
		
		count[start]++;
		
		for(int i=0; i<adjList[start].size(); i++) {
			int next = adjList[start].get(i);
			if(!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
		
	}

}
