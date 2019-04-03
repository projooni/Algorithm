
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
	
	public static List<Integer>[] adjList;
	public static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		adjList = new ArrayList[10];
		visited = new boolean[10];
		
		dfsAll();

	}
	
	public static void dfsAll() {
		for(int i=0; i<adjList.length; i++) {
			dfs(i);
		}
	}
	
	public static void dfs(int curr_vertex) {
		
		visited[curr_vertex] = true;
		
		for(int i=0; i<adjList[curr_vertex].size(); i++) {
			if(!visited[adjList[curr_vertex].get(i)]) {
				dfs(adjList[curr_vertex].get(i));
			}
		}
		
	}
	
}
