package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Drunken_Solution {
	
	public static int V,E;
	public static int adj[][], via[][], W[][];
	public static int delay[], delayPair[][];;
	public static int S,D;
	public static int minRst;
	public static ArrayList<Integer> path;
	public static ArrayList<Integer> maxDelayVertex;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new int[V+1][V+1];
		W = new int[V+1][V+1];
		via = new int[V+1][V+1];
		delay = new int[V+1];
		delayPair = new int[V+1][2];
		
		// ì´ˆê¸°?™”
		for(int i=0; i<=V; i++){
			for(int j=0; j<=V; j++){
				adj[i][j] = -1;
			}
		}
		
		// ê°? ? •? ?—?„œ ?Œì£¼ë‹¨?† ?”œ? ˆ?´
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=V; i++){
			int cost = Integer.parseInt(st.nextToken());
			delay[i] = cost;
			delayPair[i][0] = i;
			delayPair[i][1] = cost;
		}
		
		Arrays.sort(delayPair, new Comparator<int[]>(){
			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[1] - arg1[1];
			}
		});
		
		// ê°„ì„  ê°?ì¤‘ì¹˜
		for(int i=1; i<=E; i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adj[s][e] = t;
			adj[e][s] = t;
		}
		
		for(int i=1; i<=V; i++){
			for(int j=1; j<=V; j++){
				if(i == j){
					adj[i][j] = 0;
					W[i][j] = 0;
				}else{
					if(adj[i][j] == -1){
						adj[i][j] = 123456789;
						W[i][j] = 123456789;
					}
				}
			}
		}
		floyd();
		
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++){
			
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			bw.flush();
			bw.write(W[S][D] + "\n");
			
		}
		
		bw.close();

	}
	
	public static void floyd(){
		
		for(int k=1; k<=V; k++){
			int w = delayPair[k][0];
			for(int i=1; i<=V; i++){
				for(int j=1; j<=V; j++){
					adj[i][j] = Math.min(adj[i][j], adj[i][w]+adj[w][j]);
					W[i][j] = Math.min(adj[i][w] + delay[w] + adj[w][j], W[i][j]);
				}
			}
		}
		
	}

}
