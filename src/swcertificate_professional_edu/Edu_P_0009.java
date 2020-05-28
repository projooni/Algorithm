package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Edu_P_0009 {
	
	public static int N,M;
	public static List<long[]> adjList[];
	public static long dist[];
	public static long MAX_VAL = 3000000000L;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			dist = new long[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<long[]>();
				dist[i] = MAX_VAL;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adjList[a].add(new long[] {b,c});
				adjList[b].add(new long[] {a,c});
			}
			
			dijkstra(1);
			
			bw.flush();
			bw.write("#" + tc + " " + (dist[N] == MAX_VAL ? -1 : dist[N]) + "\n");
		}
		bw.close();

	}
	
	public static void dijkstra(int start) {
		
		PriorityQueue<long[]> pq = new PriorityQueue<long[]>(new Comparator<long[]>() {

			@Override
			public int compare(long[] arg0, long[] arg1) {
				// TODO Auto-generated method stub
				return (int) (arg0[1] - arg1[1]);
			}
			
		});
		
		pq.add(new long[] {start,0});
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			long curr[] = pq.poll();
			int curr_idx = (int) curr[0];
			
			for(int i=0; i<adjList[curr_idx].size(); i++) {
				long next[] = adjList[curr_idx].get(i).clone();
				next[1] += curr[1];
				if(next[1] < dist[(int) next[0]]) {
					dist[(int) next[0]] = next[1];
					pq.add(next);
				}
			}
			
		}
		
	}

}
