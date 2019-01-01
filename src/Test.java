import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Test {
	
	static final int _M_size_ = 100;
	static final int _INTMAX_ = 2087654321; // infinite
	static ArrayList<Integer> adjList[] = new ArrayList[_M_size_];
	static boolean visited[] = new boolean[_M_size_];
	
	static int AdjMatrix[][] = new int[_M_size_][_M_size_];
	static int dist[][] = new int[_M_size_][_M_size_];
	
	// Union-Find
	static int Set[] = new int[_M_size_ +1];
	
	// kruskal
	static int edge[][] = new int[_M_size_][3]; // [0] : from vertex, [1] : to vertex, [2] : edge cost     
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// dfs all

	}

	static int group(int u) {
		if(Set[u] == u) {
			return u;
		}
		
		return Set[u] = group(Set[u]);
	}
	
	static void join(int a, int b) {
		int A = group(a), B = group(b);
		if(A!=B) Set[A] = B;
	}
	
	static class Comp implements Comparator<int[]>{

		@Override
		public int compare(int[] arg0, int[] arg1) {
			// TODO Auto-generated method stub
			return arg0[2] - arg1[2];
		}
		
	}
	
	static int kruskal(int V, int E) {
		int mst_cost = 0;
		int selected = 0;
		
		for(int i=1; i<=V; i++) {
			Set[i] = i;
		}
		
		Arrays.sort(edge,0,E,new Comp());
		
		for(int i=0; i<E; i++) {
			if(group(edge[i][0]) != group(edge[i][1])) {
				mst_cost = edge[i][2];
				selected++;
				join(edge[i][0], edge[i][1]);
			}
		}
		
		if(selected == V-1) {
			return mst_cost;
		}else {
			return -1;
		}
	}
	
	

}
