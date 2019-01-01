package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Timetrip {
	
	public static int V,W;
	public static ArrayList<int[]> adjList[];
	public static ArrayList<int[]> adjListNagative[];
	public static int upper[];
	public static int adj[][];
	public static int adjClone[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[V];
			adjListNagative = new ArrayList[V];
			upper = new int[V];
			adj = new int[V][V];
			adjClone = new int[V][V];
			
			for(int i=0; i<V; i++) {
				adjList[i] = new ArrayList<int[]>();
				adjListNagative[i] = new ArrayList<int[]>();
			}
			
			for(int i=0; i<V; i++){
				for(int j=0; j<V; j++){
					// overflow 방지를 위해 MAX_VALUE 사용안함
					// TODO MAX_VALUE에 왜 가/감산 연산이 되는지 확인 필요
					adj[i][j] = 123456789; 
				}
			}
			
			for(int i=0; i<W; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adjList[s].add(new int[] {e,c});
				adjListNagative[s].add(new int[] {e,-1*c});
				adj[s][e] = c;
			}
			
			int result = BellmanFord(0,1,false);
			if(result == 1) {
				bw.write("UNREACHABLE");
			}else {
				bw.write("" + (result == 0 ? "INFINITY" : upper[1]) + " ");
				adjList = adjListNagative;
				result = BellmanFord(0,1, true);
				bw.write("" + (result == 0 ? "INFINITY" : upper[1]));
			}
			bw.flush();
			bw.write("\n");
			
		}
		
		bw.close();
		
		

	}
	
	public static void floyd(boolean isNagative){
		
		// floyd 할때마다 초기화
		for(int i=0; i<V; i++){
			for(int j=0; j<V; j++){
				adjClone[i][j] = adj[i][j];
			}
		}
		
		for(int i=0; i<V; i++){
			adjClone[i][i] = 0;
		}
		
		for(int k=0; k<V; k++){
			for(int i=0; i<V; i++){
				for(int j=0; j<V; j++){
					adjClone[i][j] = Math.min(adjClone[i][j], adjClone[i][k]+adjClone[k][j]);
				}
			}
		}
	}
	
	public static int BellmanFord(int start, int dest, boolean isNagative) {
		// 플로이드
		floyd(isNagative);
		int maxValue = (Integer.MAX_VALUE - 100000);
		for(int i=0; i<upper.length; i++) {
			upper[i] = (Integer.MAX_VALUE - 100000);
		}
		upper[start] = 0;
		
		boolean isUpdate = false;
		for(int iter=0; iter<V; iter++) { // V번 순회
			
			isUpdate = false;
			for(int i=0; i<V; i++) {
				for(int j=0; j<adjList[i].size(); j++) {
					int[] next = adjList[i].get(j);
					if(upper[next[0]] > upper[i] + next[1]) { // 완화
						upper[next[0]] = upper[i] + next[1];
						if(iter == V-1){ // 사이클 존재 여부 확인 이터레이션
							// 음수사이클 i를 경유해서 0에서 1로 가는 경로가 하나라도! 있다면 INFINITY
							if(adjClone[0][i] < 123456789-100000 && adjClone[i][1] < 123456789-100000){
								isUpdate = true;
							}
						}else{
							isUpdate = true;
						}
					}
				}
			}
			
		}
		
		if(isNagative){
			for(int i=0; i<upper.length; i++){
				upper[i] *= -1;
			}
		}
		
		
		if(upper[dest] > maxValue - 100000) { // 경로 없음
			return 1;
		}else if(isUpdate) { // 무한 사이클
			// 사이클인데 경로가 있는 경우
			
			return 0;
		}else {
			return 2;
		}
		
		
	}
	

}
