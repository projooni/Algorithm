package swcertificate_mock_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class mocktest_P_0001 {
	
	public static int N;
	public static List<Integer> adjList[];
	public static boolean inbound[];
	public static boolean visited[];
	public static int dupNode;
	public static int edge[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			
			adjList = new ArrayList[N+1];
			inbound = new boolean[N+1];
			visited = new boolean[N+1];
			edge = new int[N+1][2];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adjList[s].add(e);
				edge[i] = new int[] {s,e};
				inbound[e] = true;
			}	
			
			// 루트를 찾는다.
			int root = 0;
			for(int i=1; i<=N; i++) {
				if(!inbound[i]) {
					root = i;
					break;
				}
			}
			
			dupNode = 0;
			if(root == 0) {
				dfsAll();
			}else {
				visited[root] = true;
				dfs(root);
			}
			
			int idx = 1;
			for(int i=N; i>=1; i--) {
				if(edge[i][1] == dupNode) {
					if(edge[i][0] != root) {
						idx = i;
						break;
					}
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + edge[idx][0] + " " + edge[idx][1] + "\n");
		}
		bw.close();

	}
	
	public static void dfsAll() {
		
		for(int i=1; i<=N; i++) {
			if(dupNode != 0 ) {
				break;
			}
			visited = new boolean[N+1];
			visited[i] = true;
			dfs(i);
		}
		
	}
	
	public static void dfs(int curr) {
		
		for(int i=0; i<adjList[curr].size(); i++) {
			int next = adjList[curr].get(i);
			if(visited[next]) {
				dupNode = next;
				break;
			}else {
				visited[next] = true;
				dfs(next);
			}
		}
		
	}

}
