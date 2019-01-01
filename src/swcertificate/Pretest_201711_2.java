package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import swcertificate.Pretest_201711.Pair;

public class Pretest_201711_2 {
	public static int N;
	public static int M;
	public static ArrayList<Integer> adjList[];
	public static boolean visited[];
	
	public static int[] depthArr;
	public static int[][] dp;
	public static int count;
	public static List<Integer> rstList;
	
	public static class Pair{
		int startNode;
		int endNode;
		public Pair(int startNode, int endNode) {
			this.startNode = startNode;
			this.endNode = endNode;
		}
	}
	
	public static Pair pair[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
//		LCA 알고리즘 사용
		
		System.setIn(new FileInputStream("E:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			visited = new boolean[N+1];
			depthArr = new int[N+1];
			dp = new int[N+1][11];
			pair = new Pair[M+1];
			
			rstList = new ArrayList<Integer>();
			
			int startNode=0, endNode=0;
			
			for(int i=1; i<=N; i++){
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=M; i++){
				
				st = new StringTokenizer(br.readLine());
				startNode = Integer.parseInt(st.nextToken());
				endNode = Integer.parseInt(st.nextToken());
				adjList[startNode].add(endNode);
				adjList[endNode].add(startNode);
				pair[i] = new Pair(startNode, endNode);
			}
			
			count = 0;
			
			dfs(1,0);
			makeDp();
			check();
			
			bw.write("#" + tc + " " + count);
			bw.write("\n");
			bw.flush();
			
		}
		
		bw.close();

	}
	
	public static void check(){
		for(int i=1; i<=M; i++){
			int startNode = pair[i].startNode;
			int endNode = pair[i].endNode;
			if(lca(startNode, endNode) == 0){
				rstList.add(i);
				count++;
			}
		}
	}

	public static void dfs(int here, int depth){
		
		visited[here] = true;
		
		depthArr[here] = depth;
		
		for(int i=0; i<adjList[here].size(); i++){
			
			int there = adjList[here].get(i);
			dp[there][0] = here; 
			
			if(!visited[there]){
				dfs(there, depth+1);
			}
			
		}
		
	}
	
	public static void makeDp(){
		for(int i=1; i<=10; i++){
			for(int j=1; j<N; j++){
				dp[j][i] = dp[dp[j][i - 1]][i - 1];
			}
		}
	}
	
	public static int lca(int x, int y){
		
		int bigger = depthArr[y] >= depthArr[x] ? y : x;
		int smaller = depthArr[y] < depthArr[x] ? y : x;
	        
	    for (int i = 10; i >= 0; i--) {
	        if ((depthArr[bigger] - depthArr[smaller]) >= (Math.pow(2,i))){
	        	bigger = dp[bigger][i];
	        } 
	            
	    }
	    
	    if (bigger == smaller)return smaller;
	    for (int i = 10; i >= 0; i--) {
	        if (dp[smaller][i] != dp[bigger][i]) {
	            smaller = dp[smaller][i];
	            bigger = dp[bigger][i];
	        }
	    }
	    
	    return dp[smaller][0];

	}
	
	

}
