package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Promises {
	
	public static int V,M,N;
	public static long adj[][],dist[][];
	public static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		// 테스트 커밋2
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken()); // V (2 <= V <= 200)
			M = Integer.parseInt(st.nextToken()); // 0 <= M+N <= 1000
			N = Integer.parseInt(st.nextToken());
			
			adj = new long[V][V];
			dist = new long[V][V];
			
			// 0 <= a,b <= V-1, 1 <= c <= 100000
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				if(adj[a][b] > 0) {
					if(c < adj[a][b]) {
						adj[a][b] = c;
						adj[b][a] = c;
					}
				}else {
					adj[a][b] = c;
					adj[b][a] = c;
				}
				
			}
			
			floyd();
			
			result = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				if( !update(a, b, c)) {
					result++;
				}
			}
		
			bw.flush();
			bw.write(result + "\n");
		}
		
		bw.close();

	}
	
	public  static void floyd() {
		for(int i=0; i<V; i++) {
			for(int j=0; j<V; j++) {
				if(i==j) {
					dist[i][j] = 0;
				}else {
					dist[i][j] = adj[i][j] != 0 ? adj[i][j] : Integer.MAX_VALUE; 
				}
			}
		}
		
		for(int k=0; k<V; k++) {
			for(int i=0; i<V; i++) {
				for(int j=0; j<V; j++) {
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
	}
	
	public static boolean update(int a, int b, int c) {
		if(dist[a][b] <= c) {return false;}
		for(int x=0; x<V; x++) {
			for(int y=0; y<V; y++) {
				dist[x][y] = Math.min(dist[x][y], Math.min(dist[x][a]+c+dist[b][y], dist[x][b]+c+dist[a][y]));
			}
		}
		return true;
	}

}
