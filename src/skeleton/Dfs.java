package skeleton;
// DFS.java  
// 시간복잡도 : O(V+E)

import java.util.ArrayList;  

public class Dfs {
	
	static final int _M_size_ = 100; // max number of vertices  

    // adjacent list  
    static ArrayList<Integer> AdjList[] = new ArrayList[_M_size_];  

    // check if the vertex is visited  
    static boolean visited[] = new boolean[_M_size_];  

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static void dfsAll(){
		
		// component�� �������� ��츦 ���� ��� ��带 ���ư��鼭 dfs�� �����Ѵ�
		for(int i=0; i<AdjList.length; i++){
			dfs(i);
		}
		
	}
	
	static void dfs(int curr_vertex) {  

        visited[curr_vertex] = true;  

        // find the next vertex and go.  
        for(int i = 0; i < AdjList[curr_vertex].size(); i++) {  
            if(!visited[AdjList[curr_vertex].get(i)]) {  
                dfs(AdjList[curr_vertex].get(i)); // recursion  
            }  
        }  

    }

}
