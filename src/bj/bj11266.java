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

public class bj11266 {
	
	public static int V, E;
	public static List<Integer> adjList[];
	public static boolean result[];
	public static int discover[];
	public static int cnt;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[V+1];
			discover = new int[V+1];
			result = new boolean[V+1];
			
			for(int i=0; i<V+1; i++) {
				discover[i] = -1;
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			cnt = 0;
			for(int i=1; i<=V; i++) {
				if(discover[i] == -1) {
					dfs(i, -1, true);
				}
			}
			
			int size = 0;
			for(int i=1; i<result.length; i++) {
				if(result[i]) {
					size++;
				}
			}
			
			bw.flush();
			bw.write(size + "\n");
			for(int i=1; i<result.length; i++) {
				if(result[i]) {
					bw.write(i + " ");
				}
			}
		}
		bw.close();

	}
	
	public static int dfs(int vertex, int parent, boolean isRoot) {
		
//		if(discover[vertex] != -1) {
//			return discover[vertex];
//		}

		// 발견 카운트를 기록한다.
		int ret = discover[vertex] = cnt++;
		
		int childCount = adjList[vertex].size();
		boolean isArticulation = false;
		for(int i=0; i<childCount; i++) {
			int next = adjList[vertex].get(i);
			
			if(next == parent) {
				continue;
			}
			
			if(discover[next] == -1) {
				// next를 방문하고, 최소 발견카운트를 리턴한다.
				int minDiscover = dfs(next, vertex, false);
				
				// next에서 vertex보다 앞에 발견된 노드에 갈 방법이 없으면 단절점이다.
				if(!isRoot && minDiscover >= discover[vertex]) {
					isArticulation = true;
				}
				
				ret = Math.min(minDiscover, ret);
			}else {
				ret = Math.min(discover[next], ret);
			}
			
		}
		
		if((isRoot && childCount >= 2) || isArticulation) {
			result[vertex] = true;
		}
		
		return ret;
		
	}

}
