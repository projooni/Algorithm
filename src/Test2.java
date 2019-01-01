
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Test2 {
	public static int N;
	public static int M;
	public static ArrayList<Integer> adjList[];
	public static boolean[] visited;
	public static int lastNode;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("E:\\sample_input_3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			
			adjList = new ArrayList[N+1];
			visited = new boolean[N+1];
			lastNode = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++){
				int pre = Integer.parseInt(st.nextToken());
				int post = Integer.parseInt(st.nextToken());
				adjList[pre].add(post);
				adjList[post].add(pre);
				
			}
			
			
			
			bw.write("#" + tc + " " + lastNode + "\n");
			bw.flush();
			
		}
		
		bw.close();

	}
	
	public static void dfsAll(){
		for(int i=1; i<=N; i++){
			if(visited[i] == false){
				dfs(i);
			}
		}
	}
	
	public static void dfs(int here){
		visited[here] = true;
		
		Collections.sort(adjList[here]);
		for(int i=0; i<adjList[here].size(); i++){
			int there = adjList[here].get(i);
			if(!visited[there]){
				lastNode = there;
				dfs(there);
			}
		}
		
	}
}
