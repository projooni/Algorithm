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
/*
 * 연습A-0006
 * 유형 : 다익스트라
 * */
public class Prac_A_0006 {
	
	public static int N,R;
	public static List<int[]> adjList[];
	public static int dist[];
	public static final int MAX_DIST = 1000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			R = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			dist = new int[N+1];
			adjList = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<int[]>();
				dist[i] = MAX_DIST;
			}
			
			for(int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				adjList[s].add(new int[] {e,d});
				adjList[e].add(new int[] {s,d});
			}
			
			// 다익스트라
			search();
			
			bw.flush();
			bw.write("#" + tc + " " + dist[1] + "\n");
		}
		bw.close();

	}
	
	public static void search() {
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[1] - arg1[1];
			}
			
		});
		
		pq.add(new int[] {N,0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			for(int i=0; i<adjList[curr[0]].size(); i++) {
				int[] next = adjList[curr[0]].get(i).clone();
				next[1] += curr[1];
				if(next[1] < dist[next[0]]) {
					dist[next[0]] = next[1];
					pq.add(next);
				}
			}
			
		}
		
	}

}
