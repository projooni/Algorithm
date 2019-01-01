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

public class Pretest_201711 {
	public static int N;
	public static int M;
//	public static int adjArr[][];
	public static ArrayList<Integer> adjList[];
	public static boolean visited[];
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
		
		System.setIn(new FileInputStream("E:\\sample_input_2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
//			adjArr = new int[M+1][M+1];
			adjList = new ArrayList[N+1];
			visited = new boolean[N+1];
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
//				adjArr[startNode][endNode] = 1;
//				adjArr[endNode][startNode] = 1;
				adjList[startNode].add(endNode);
				adjList[endNode].add(startNode);
				pair[i] = new Pair(startNode, endNode);
				
			}
			
			count = 0;
			checkVisited();
			
			
			bw.write("#" + tc + " " + count);
			for(int i=0; i<rstList.size(); i++){
				bw.write(" " + rstList.get(i));
			}
			bw.write("\n");
			bw.flush();
			
		}
		
		bw.close();

	}
	public static void checkVisited(){
		
		for(int i=1; i<=M; i++){
			int startNode = pair[i].startNode;
			int endNode = pair[i].endNode;
//			adjList[startNode].remove
//			TODO
			int idxS=0, idxE=0;
			for(int j=0; j<adjList[startNode].size(); j++){
				if(adjList[startNode].get(j) == endNode){
					adjList[startNode].remove(j);
					idxS = j;
					break;
				}
			}
			for(int j=0; j<adjList[endNode].size(); j++){
				if(adjList[endNode].get(j) == startNode){
					adjList[endNode].remove(j);
					idxE = j;
					break;
				}
			}
			
			dfs(1);
			
			boolean isUnvisited = false;
			for(int j=1; j<=N; j++){
				if(visited[j] == false){
					isUnvisited = true;
				}
				visited[j] = false;
			}
			
			if(isUnvisited){
				rstList.add(i);
				count++;
			}
			
			adjList[startNode].add(endNode);
			adjList[endNode].add(startNode);
			
			
			
		}
		
	}
	
	public static void dfs(int here){
		visited[here] = true;
		
		for(int i=0; i<adjList[here].size(); i++){
			int there = adjList[here].get(i);
			
			if(!visited[there]){
				dfs(there);
			}
		}
		
	}

}
