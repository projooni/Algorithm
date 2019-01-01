package skeleton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue; 

public class Bfs {
	// 시간복잡도 : O(|V|+|E|)
	
	static final int _M_size_ = 100; // max number of vertices    
    // Adjacent list
    static ArrayList<Integer> AdjList[] = new ArrayList[_M_size_];  
    //check if the vertex is visited    
    static boolean visited[] = new boolean[_M_size_];

     

    static void BFS(int start_vertex) {

        Queue <Integer> Q = new LinkedList<Integer>();  
        Q.add(start_vertex);  
        visited[start_vertex] = true;    

        while(!Q.isEmpty())  

        {  
            int curr_vertex = Q.poll();  
            // find the next vertex and go.    
            for (int i = 0; i < AdjList[curr_vertex].size(); i++) {
            	int there = AdjList[curr_vertex].get(i);
                if (!visited[there]) {    
                    visited[there] = true;  
                    Q.add(there);    // add queue  
                }    
            }    

        }  

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
