package swcertificate;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import java.util.StringTokenizer;

public class Interstellar2 {

	

	public static class Pair{

		public int x;
		public int y;
		public int weight;

		public Pair(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

	}

	// 각 정점에서의 간선 리스트
	public static ArrayList<Pair> adj[][];
	
	// 가로 길이
	public static int X;
	// 세로 길이
	public static int Y;
	// 운석 개수
	public static int M;
	// 워프 개수
	public static int W;
	// 운석 인접 행렬
	public static boolean[][] meteorLocArr;
	// 워프 인접 행렬
	public static Pair[][] warpLocArr;
	
	public static boolean infWarp;

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(br.readLine());
			
			adj = new ArrayList[X][Y];
			
			// 운석 세팅!
			meteorLocArr = new boolean[X][Y];
			for(int i=0; i<M; i++){
				st = new StringTokenizer(br.readLine());
				meteorLocArr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			// 워프 세팅!
			W = Integer.parseInt(br.readLine());
			warpLocArr = new Pair[X][Y];
			
			for(int i=0; i<W; i++){
				st = new StringTokenizer(br.readLine());
				warpLocArr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 
						new Pair(
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken())
								);
			}
						
			for(int i=0; i<X; i++){
				for(int j=0; j<Y; j++){
					// 각 정점에 연결된 간선을 전부 add한다.
					adj[i][j] = new ArrayList<Pair>();
					int w = 1;	// 기본 가중치
					
					if(meteorLocArr[i][j]){	// 운석이라면
						// 패스한다
						continue;
					}
					
					if(i != X-1 || j!= Y-1){	// 도착지는 간선이 없다 (즉시탈출)
						if(warpLocArr[i][j] != null){	// 워프라면
							adj[i][j].add(new Pair(warpLocArr[i][j].x, warpLocArr[i][j].y, warpLocArr[i][j].weight));
						}else{
							if(j >= 1){
								if(!meteorLocArr[i][j-1]){	// 운석이 아니면
									adj[i][j].add(new Pair(i, j-1, w));	// top
								}
							}
							if(i <= (X-2)){
								if(!meteorLocArr[i+1][j]){
									adj[i][j].add(new Pair(i+1, j, w));	// right
								}
							}
							if(j <= (Y-2)){
								if(!meteorLocArr[i][j+1]){
									adj[i][j].add(new Pair(i, j+1, w));	// bottom
								}
							}
							if(i >= 1){
								if(!meteorLocArr[i-1][j]){
									adj[i][j].add(new Pair(i-1, j, w));	// left
								}
							}
						}
					}
					
				}
			}

			long result[][] = bellmanFord(0,0);
			if(result[X-1][Y-1] >= (100000000 - 9000000)){
				bw.write("#" + testCase + " " + "noway");
			}else{
				bw.write("#" + testCase + " " + (result[X-1][Y-1] == (long)-1 || infWarp ? "mininf" : result[X-1][Y-1]));
			}
			bw.write("\n");
			bw.flush();
			
			
		}
		
		bw.close();

	}

	

	public static long[][] bellmanFord(int x, int y){

		// 시작점을 제외한 모든 정점가지의 최단 거리 상한을 INF로 둔다.

		long upper[][] = new long[X][Y];
		
		for(int i=0; i<X; i++){
			for(int j=0; j<Y; j++){
				upper[i][j] = 100000000;
			}
		}

		upper[x][y] = 0;

		boolean updated = false;
		infWarp = false;

		// 노드의 수만큼 순회한다.

		for(int i=0; i<(X*Y); i++){

			updated = false;

			for(int here_x=0; here_x<X; here_x++){
				for(int here_y=0; here_y<Y; here_y++){
					
					for(int k=0; k<adj[here_x][here_y].size(); k++){

						int there_x = adj[here_x][here_y].get(k).x;
						int there_y = adj[here_x][here_y].get(k).y;
						long cost = adj[here_x][here_y].get(k).weight;

						// (here, there) 간선을 따라 완화를 시도한다. (탈출지점 제외)
						
						if(upper[there_x][there_y] > upper[here_x][here_y] + cost){
							// 성공
							upper[there_x][there_y] = upper[here_x][here_y] + cost;
							updated = true;
						}
						
						if(here_x == there_x && here_y == there_y && cost < 0){	// 워프 출발지와 도착지가 동일할 때 그리고 비용이 음수일때
							infWarp = true;
						}

					}
					
					
				}

				

			}
			
			// 모든 간선에 대해 완화가 실패했을 경우 V-1번도 돌 필요 없이 곧장 종료.
			if(!updated) break;

		}

		// V번째 순회에서도 완화가 성공했다면 음수 사이클이 있다.
		if(updated) upper[X-1][Y-1] = -1;
		return upper;

	}



}

