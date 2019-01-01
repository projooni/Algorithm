package swcertificate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class EmptyBoard {
	
	static final int _M_size_ = 100;
	static ArrayList<Integer> AdjList[] = new ArrayList[_M_size_];
	static boolean visited[] = new boolean[_M_size_];
	
	public void BFS(int start_vertex){
		
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(start_vertex);
		visited[start_vertex] = true;
		
		while(!Q.isEmpty()){
			int curr_vertex = Q.poll();
			
			for(int i=0; i<AdjList[curr_vertex].size(); i++){
				if(!visited[AdjList[curr_vertex].get(i)]){
					visited[AdjList[curr_vertex].get(i)] = false;
					Q.add(AdjList[curr_vertex].get(i));
				}
			}
		}
		

	}
	

}
