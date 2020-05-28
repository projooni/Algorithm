package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Pretest_A_0037 {
	
	public static int V,E;
	public static List<Integer> adjList[];
	public static boolean visited[];
	public static int lowArr[];
	public static int visitCnt;
	public static boolean result[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[V+1];
			visited = new boolean[V+1];
			lowArr = new int[V+1];
			result = new boolean[V+1];
			
			for(int i=1; i<=V; i++) {
				adjList[i] = new ArrayList<Integer>();
				lowArr[i] = Integer.MAX_VALUE;
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			visitCnt = 0;
			
			dfs(1, 0);
			
			int resultCnt = 0;
			for(int i=1; i<=V; i++) {
				if(result[i]) {
					resultCnt++;
				}
			}
			
			bw.flush();
			if(resultCnt == 0) {
				bw.write("#" + tc + " " + 0 + "\n");
			}else {
				bw.write("#" + tc + " " + resultCnt);
				for(int i=1; i<=V; i++) {
					if(result[i]) {
						bw.write(" " + i);
					}
				}
				bw.write("\n");
			}
			
			
		}
		bw.close();

	}
	
	public static int dfs(int start, int prev) {
		
		int low = visitCnt;
		lowArr[start] = visitCnt;
		visited[start] = true;
		boolean isCut = true;
		boolean isLeaf = true;
		int cnt = 0;
		
		for(int i=0; i<adjList[start].size(); i++) {
			int next = adjList[start].get(i);
			if(!visited[next]) {
				isLeaf = false;
				visitCnt++;
				int nextLow = dfs(next, start);
				if(nextLow < low) {
					isCut = false;
					low = nextLow;
				}
				cnt++;
			}else {
				if(next != prev) {
					if(lowArr[next] < low) {
						low = lowArr[next];
						isCut = false;
					}
					isLeaf = false;
				}
			}
		}
		
		if(start == 1 && cnt < 2) {
			isCut = false;
		} 
		
		if(isLeaf) {
			result[prev] = true;
		}
		
		if(!isLeaf && isCut) {
			result[start] = true;
		}
		
		lowArr[start] = low;
		return lowArr[start];
		
	}

}
