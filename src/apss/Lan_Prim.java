package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Lan_Prim {
	
	public static int N,M,E;
	public static int node[][];
	public static int Set[];
	public static ArrayList<double[]> adjList[];
	public static double sum;
	public static ArrayList<int[]> selected;

	/* Prim */
	// 해당 정점이 트리에 포함되어 있나?
	public static boolean added[];
	// 트리에 인접한 간선 중 해당 정점에 닿는 최소 간선의 정보를 저장
	public static double minWeight[];
	public static int parent[];
	
	public static int firstRoot = 0;
	
	 public static void main(String[] args) throws Exception{
		 
		 System.setIn(new FileInputStream("D:\\sample_input.txt"));
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 
		 int T = Integer.parseInt(br.readLine().trim());
		 for(int tc=1; tc<=T; tc++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 M = Integer.parseInt(st.nextToken());
			 E = N * N;
			 
			 node = new int[N][2];
			 Set = new int[N]; // start with 1. index 0 is not used.
			 adjList = new ArrayList[N];
			 selected = new ArrayList<int[]>();
			 added = new boolean[N];
			 minWeight = new double[N];	// minWeight[i] : i와 트리(T)간의 최소 가중치
			 parent = new int[N];
		  
			 Arrays.fill(added, false);
			 Arrays.fill(minWeight, Double.MAX_VALUE);
			 Arrays.fill(parent, -1);
			 
			 
			 
			 for(int i=0; i<N; i++) {
				 adjList[i] = new ArrayList<double[]>();
			 }
			 
			 // x좌표
			 st = new StringTokenizer(br.readLine());
			 for(int i=0; i<N; i++) {
				 node[i][0] = Integer.parseInt(st.nextToken());
			 }
			 
			 // y좌표
			 st = new StringTokenizer(br.readLine());
			 for(int i=0; i<N; i++) {
				 node[i][1] = Integer.parseInt(st.nextToken());
			 }
			 
			 // join
			 for(int i=0; i<M; i++) {
				 st = new StringTokenizer(br.readLine());
				 int s = Integer.parseInt(st.nextToken());
				 int e = Integer.parseInt(st.nextToken());
				 added[s] = true;
				 added[e] = true;
				 firstRoot = s;
				 minWeight[s] = 0;
				 minWeight[e] = 0;
			 }
			 
			 // make adjList
			 for(int i=0; i<N; i++) {
				 for(int j=0; j<N; j++) {
					 adjList[i].add(new double[] {j,
							 Math.sqrt( Math.pow(Math.abs(node[i][0] - node[j][0]), 2) + Math.pow(Math.abs(node[i][1] - node[j][1]), 2) )
					 });
				 }
			 }
			 
			 bw.flush();
			 bw.write(prim()+"\n");
			 
		 }
		 bw.close();
		 
		 
	 }
	  
	  public static double prim() {
		  

		  
		  // 가중치의 합
		  double ret = 0;
		  
		  // 0번 정점을 시작점으로: 항상 트리에 가장 먼저 추가한다.
		  minWeight[firstRoot] = 0;
//		  parent[0] = 0;
		  
		  for(int iter=0; iter<N; iter++) {
			  // 다음 트리에 추가할 정점 u를 찾는다.
			  int u = -1;
			  for(int v=0; v < N; v++) {
				  if(!added[v] && (u == -1 || minWeight[u] > minWeight[v])) {
					  u = v;
				  }
			  }
			  
//			  if(parent[u] != u) {
//				  selected.add(new int[] {parent[u],u});
//			  }
			  
			  if(u == -1) {
				  break;
			  }
			  
			  ret += minWeight[u];
			  added[u] = true;
			  
			  // u에 인접한 간선 (u,v) 들을 검사한다.
			  for(int i=0; i<adjList[u].size(); i++) {
				  int v = (int)adjList[u].get(i)[0];
				  double weight = adjList[u].get(i)[1];
				  
				  if(!added[v] && minWeight[v] > weight) {
//					  parent[v] = u;
					  minWeight[v] = weight;
				  }
				  
			  }
			  
		  }
		  
		  return ret;
		  
	  }

}
