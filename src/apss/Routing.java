package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Routing {
	
	public static int N,M;
	public static ArrayList<double[]> adjList[];
	public static double visited[];
	
//	public static class Pair{
//		int there;
//		double x;
//		public Pair( int there, double x) {
//			this.there = there;
//			this.x = x;
//		}
//	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			visited = new double[N];
			adjList = new ArrayList[N];
			for(int i=0; i<N; i++) {
				adjList[i] = new ArrayList<double[]>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				double x = Double.parseDouble(st.nextToken());
				adjList[s].add(new double[] {e,x});
				adjList[e].add(new double[] {s,x});
			}
			
			dijkstra(0,N-1);
			
			DecimalFormat df = new DecimalFormat("0.0000000000");
			
			bw.flush();
			bw.write(df.format(visited[N-1]) + "\n");
			
		}
		bw.close();
		
		
		

	}
	
	public static void dijkstra(int start, int dest) {
		
		PriorityQueue<double[]> PQ = new PriorityQueue<>( new Comparator<double[]>() {

			@Override
			public int compare(double[] arg0, double[] arg1) {
				// TODO Auto-generated method stub
				return arg0[1] - arg1[1] >= 0 ? 1 : -1;
			}

			
		});
		
		for(int i=0; i<N; i++) {
			visited[i] = Double.MAX_VALUE;
		}
		
		visited[start] = 0;
		
		PQ.add(new double[] {start, 1});
		
		while(!PQ.isEmpty()) {
			
			double curr[] = PQ.poll();
			
			if(curr[0] == dest) {
				break;
			}
			
			for(int i=0; i<adjList[(int) curr[0]].size(); i++) {
				double next[] = adjList[(int) curr[0]].get(i).clone();
				next[1] *= curr[1];
				
				if(next[1] < visited[(int) next[0]]) {
					visited[(int) next[0]] = next[1];
					PQ.add(next);
				}
				
				
			}

		}
		
	}

}
