package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Drunken {
	
	public static int V,E;
	public static int adj[][], via[][], adjCopy[][];
	public static int delay[];
	public static int S,D;
	public static int minRst;
	public static ArrayList<Integer> path;
	public static ArrayList<Integer> maxDelayVertex;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://dev/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new int[V+1][V+1];
		via = new int[V+1][V+1];
		delay = new int[V+1];
		
		// 초기화
		for(int i=0; i<=V; i++){
			for(int j=0; j<=V; j++){
				adj[i][j] = -1;
			}
		}
		
		// 각 정점에서 음주단속 딜레이
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=V; i++){
			delay[i] = Integer.parseInt(st.nextToken());
		}
		
		// 간선 가중치
		for(int i=1; i<=E; i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adj[s][e] = t;
			adj[e][s] = t;
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++){
			adjCopy = adj.clone();
			for(int i=0; i<=V; i++){
				for(int j=0; j<=V; j++){
					via[i][j] = -1;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			maxDelayVertex = new ArrayList<Integer>();
			
			// 플로이드
			floyd();
			
			// 각 경유점을 통과하는 path를 찾는다
			for(int i=1; i<=V; i++){
				
				if(i == S || i == D){
					continue;
				}
				int max = 0;
				int maxVtx = -1;
				path = new ArrayList<Integer>();
				reconstruct(S,i);
				for(int j=1; j<path.size()-1; j++){
					int currVtx = path.get(j);
					if(max < delay[currVtx]){
						max = delay[currVtx];
						maxVtx = currVtx;
					}
				}
				path = new ArrayList<Integer>();
				reconstruct(i,D);
				for(int j=0; j<path.size()-1; j++){
					int currVtx = path.get(j);
					if(max < delay[currVtx]){
						max = delay[currVtx];
						maxVtx = currVtx;
					}
				}
				
				maxDelayVertex.add(maxVtx);
			}
			
			// 각 경유점을 지나면서 딜레이가 적용될때 소요시간 비교 -> 최소값 찾는다
			minRst = Integer.MAX_VALUE;
			for(int i=0; i<maxDelayVertex.size(); i++){
				int currVertex = maxDelayVertex.get(i);
				// 시작/도착 지점이 아닐때 음주단속 가능성
				if(currVertex != S && currVertex != D){
					minRst = Math.min(minRst, adjCopy[S][currVertex] + adjCopy[currVertex][D] + delay[currVertex]);
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + minRst + "\n");
			
		}
		
		bw.close();

	}
	
	public static void reconstruct(int u, int v){
		//기저 사례
		if(via[u][v] == -1){
			path.add(u);
			if(u != v){
				path.add(v);
			}
		}else{
			int w = via[u][v];
			reconstruct(u,w);
			path.remove(path.size()-1);
			reconstruct(w,v);
		}
	}
	
	public static void floyd(){
		
		for(int i=1; i<=V; i++){
			for(int j=1; j<=V; j++){
				if(i == j){
					adjCopy[i][j] = 0;
				}else{
					if(adjCopy[i][j] == -1){
						adjCopy[i][j] = 123456789;
					}
				}
			}
		}
		
		for(int i=1; i<=V; i++){
			for(int j=1; j<=V; j++){
				for(int k=1; k<=V; k++){
					if(adjCopy[i][j] > adjCopy[i][k]+adjCopy[k][j]){
						via[i][j] = k;
						adjCopy[i][j] = adjCopy[i][k]+adjCopy[k][j];
					}
				}
			}
		}
		
	}

}
