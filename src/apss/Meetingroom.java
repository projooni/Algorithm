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


public class Meetingroom {
	
	public static int N;
	public static ArrayList<Integer> adjList[];
	public static boolean visited[];
	public static Pair meetingTimePair[];
	public static int result[];
	
	public static class Pair{
		int s;
		int e;
		public Pair(int si, int ei){
			s = si;
			e = ei;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int C = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st;
		for(int tc=1; tc<=C; tc++){
			
			N = Integer.parseInt(br.readLine().trim());
			adjList = new ArrayList[2*N];
			visited = new boolean[N];
			meetingTimePair = new Pair[2*N];
			result = new int[N];
			
			// O(N)
			for(int i=0; i<2*N; i=i+2){
				st = new StringTokenizer(br.readLine());
				int s1 = Integer.parseInt(st.nextToken());
				int e1 = Integer.parseInt(st.nextToken());
				int s2 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				meetingTimePair[i] = new Pair(s1,e1);
				meetingTimePair[i+1] = new Pair(s2,e2);
			}
			
			// make graph O(2N * 2N)
			for(int i=0; i<2*N; i++){
				int endTime = meetingTimePair[i].e;
				adjList[i] = new ArrayList();
				for(int j=0; j<2*N; j++){
					if( (i/2 != j/2) && endTime <= meetingTimePair[j].s){
						adjList[i].add(j);
					}
				}
			}
			
			
			bw.flush();
			if("POSSIBLE".equals(dfsAll())){
				bw.write("POSSIBLE\n");
				for(int i=0; i<result.length; i++){
					bw.write(meetingTimePair[result[i]].s + " " + meetingTimePair[result[i]].e + "\n");
				}
			}else{
				// IMPOSSIBLE
				bw.write("IMPOSSIBLE\n");
			}
			
			
				
				
		}
		
		bw.close();
		
		
		
		

	}
	
	public static String dfsAll(){
		
		String rtStr = "IMPOSSIBLE";
		
		// (|2N| + |E|) * 2N  = O(2N^2 + 2NE)
		for(int i=0; i<2*N; i++){
			
			result = new int[N];
			
			dfs(i);
			
			boolean isPossible = true;
			for(int j=0; j<N; j++){
				if(!visited[j]){
					isPossible = false;
					break;
				}
			}
			
			if(isPossible){
				rtStr = "POSSIBLE";
				break;
			}
			
		}
		
		return rtStr;
	}
	
	//	O(|V| + |E|)
	public static void dfs(int here){
		
		visited[here/2] = true;
		
		boolean isLeaf = true;
		for(int i=0; i<adjList[here].size(); i++){
			int there = adjList[here].get(i);
			if(!visited[there/2]){
				isLeaf = false;
				dfs(there);
			}
		}
		
		if(isLeaf){
			for(int i=0; i<N; i++){
				if(!visited[i]){
					visited[here/2] = false;
					break;
				}
			}
			if(visited[here/2]){
				result[here/2] = here;
			}
		}else{
			result[here/2] = here;
		}
		
	}

}
