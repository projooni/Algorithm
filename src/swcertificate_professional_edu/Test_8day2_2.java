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
 * 3차 모의 테스트
 * 유형 : 다익스트라
 * */
public class Test_8day2_2 {
	
	public static int N,M,X;
	public static List<int[]> adjList1[];
	public static List<int[]> adjList2[];
	public static int dist1[];
	public static int dist2[];

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
			X = Integer.parseInt(st.nextToken());
			
			adjList1 = new ArrayList[N+1];
			adjList2 = new ArrayList[N+1];
			dist1 = new int[N+1];
			dist2 = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList1[i] = new ArrayList<int[]>();
				adjList2[i] = new ArrayList<int[]>();
				dist1[i] = Integer.MAX_VALUE;
				dist2[i] = Integer.MAX_VALUE;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				adjList1[s].add(new int[] {e, t});
				adjList2[e].add(new int[] {s, t});
			}

			// X -> 각 기숙사로 돌아오는 최단경로
			dijkstra_from_x(X);
			dijkstra_to_x(X);
			
			int max = 0;
			for(int i=1; i<=N; i++) {
				int d = dist1[i] + dist2[i];
				max = Math.max(d, max);
			}
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}
	
	public static void dijkstra_from_x(int start) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[1] - arg1[1];
			}
			
		});
		
		pq.add(new int[] {start,0});
		dist1[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			for(int i=0; i<adjList1[curr[0]].size(); i++) {
				int[] next = adjList1[curr[0]].get(i).clone();
				next[1] += curr[1];
				if(next[1] < dist1[next[0]]) {
					dist1[next[0]] = next[1];
					pq.add(next);
				}
			}
			
		}
		
	}
	
public static void dijkstra_to_x(int start) {
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[1] - arg1[1];
			}
			
		});
		
		pq.add(new int[] {start,0});
		dist2[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			
			for(int i=0; i<adjList2[curr[0]].size(); i++) {
				int[] next = adjList2[curr[0]].get(i).clone();
				next[1] += curr[1];
				if(next[1] < dist2[next[0]]) {
					dist2[next[0]] = next[1];
					pq.add(next);
				}
			}
			
		}
		
	}

}
