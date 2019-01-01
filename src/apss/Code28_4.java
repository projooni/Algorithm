package apss;

import java.util.ArrayList;
import java.util.List;

public class Code28_4 {
	// 노드 개수 (임시 값)
	public static int N = 30;
//	public static List<Integer>[] adj = new List[N];
	public static int[][] adj = new int[N][N];
	public static List<Integer> circuit = new ArrayList<Integer>();
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		

	}
	
	
	public static void getEulerCircuit(int here, List<Integer> circuit){
		for(int there = 0; there < adj[here].length; ++there){
			while(adj[here][there] > 0){
				adj[here][there]--; // 양쪽 간선을 모두 지운다
				adj[there][here]--;
				getEulerCircuit(there, circuit);
			}
		}
		circuit.add(here);
	}

}
