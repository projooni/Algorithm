package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Firetrucks {
	
	public static int V, E, N, M;
	public static ArrayList<int[]> adjList[];
	public static ArrayList<Integer> placeList, fireStationList;
	public static ArrayList<int[]> visitedArrList;
	public static int visited[];
	public static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			V  = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			N  = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[V+1];
			placeList = new ArrayList<Integer>();
			fireStationList = new ArrayList<Integer>();
			
			for(int i=0; i<=V ; i++) {
				adjList[i] = new ArrayList<int[]>();
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				adjList[s].add(new int[]{e, c});
				adjList[e].add(new int[]{s, c});
			}

			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				placeList.add(Integer.parseInt(st.nextToken()));				
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				int fireStation = Integer.parseInt(st.nextToken());
				fireStationList.add(fireStation);
				// 가상의 정점을 추가한 후 각 소방서까지의 가중치를 0으로 설정한다. 
				// 동시에 소방서-> 화재현장 까지의 최단거리를 계산하기 위해
				adjList[0].add(new int[] {fireStation,0});
			}
			
			result = 0;
			visited = new int[V+1];
			
			dijkstra(0);
			
			for(int i=0; i<placeList.size(); i++) {
				result += visited[placeList.get(i)];
			}
			
			bw.flush();
			bw.write(result + "\n");
			
		}
		
		bw.close();

	}
	
	public static void dijkstra(int start){
		
		PriorityQueue<int[]> PQ = new PriorityQueue<int[]>(new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[1] - arg1[1];
			}
			
		});
		
		for(int i=1; i<=V; i++) {
			visited[i] = Integer.MAX_VALUE;
		}
		visited[start] = 0;
		
		PQ.add(new int[] {start, 0});
		
		while(!PQ.isEmpty()) {
			
			int[] curr = PQ.poll();
			
			for(int i=0; i<adjList[curr[0]].size(); i++) {
				int[] next = adjList[curr[0]].get(i).clone();
				
				next[1] += curr[1];
				
				if(next[1] < visited[next[0]]) {
					visited[next[0]] = next[1];
					PQ.add(next);
				}
				
				
			}
			
		}
		
	}

}
