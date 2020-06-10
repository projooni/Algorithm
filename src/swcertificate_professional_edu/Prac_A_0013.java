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

public class Prac_A_0013 {
	
	public static int K,N,M;
	public static int sp[];
	public static List<Integer> adjList[];
	public static boolean visited[];
	public static int posible[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sp = new int[K+1];
			
			// 각 소의 시작 목초지
			for(int i=1; i<=K; i++) {
				sp[i] = Integer.parseInt(br.readLine());
			}
			
			adjList = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList[s].add(e);
			}
			
			posible = new int[N+1]; 
			bfsAll();

			int result = 0;			
			for(int i=1; i<=N; i++) {
				if(posible[i] == K) {
					result++;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.close();

	}
	
	public static void bfsAll() {
		for(int i=1; i<=K; i++) {
			visited = new boolean[N+1];
			bfs(sp[i]);
		}
	}
	
	public static void bfs(int start) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			
			int curr = q.poll();
			posible[curr]++;
			// TODO 기존에는 여기서 방문처리를 했었는데, 오답이어서 변경함. 왜 여기서 처리하면 안되는지 아직 이해 못함.
			// visited[curr] = true;
			
			for(int i=0; i<adjList[curr].size(); i++) {
				int next = adjList[curr].get(i);
				if(!visited[next]) {
					q.add(next);
					visited[next] = true;
				}
			}
			
		}
		
		
		
	}

}
