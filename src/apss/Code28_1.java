package apss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code28_1 {
	
	// 노드 개수 (임시 값)
	public static int N = 30;
	// 방문 여부
	public static boolean[] visited = new boolean[N];
	// 각 노드 별 인접한 노드들의 List (인접 List)
	public static List<Integer>[] adj = new List[N];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// List 배열에 ArrayList 할당
		for(int i=0; i<adj.length; i++){
			adj[i] = new ArrayList<Integer>();
		}
		
		dfsAll();

	}
	
	public static void dfs(int here){
		visited[here] = true;
		
		for(int i=0; i<adj[here].size(); ++i){
			int there = adj[here].get(i);
			// 아직 방문한 적 없다면 방문한다.
			if(!visited[there]){
				dfs(there);
			}
		}
		
	}
	
	public static void dfsAll(){
		// visited를 모두 false로 초기화한다.
		Arrays.fill(visited, false);
		// 모든 정점을 순회하면서, 아직 방문한 적 없으면 방문한다.
		for(int i=0; i<adj.length; ++i){
			if(!visited[i]){
				dfs(i);
			}
		}
	}

}
