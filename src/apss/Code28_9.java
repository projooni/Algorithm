package apss;

import java.util.ArrayList;

public class Code28_9 {
	
	static int N = 30;
	static ArrayList<Integer>[] adj;
	static int[] discovered = new int[N];
	static boolean[] isCutVertex = new boolean[N];
	static int counter = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<N; i++){
			adj[i] = new ArrayList<Integer>();
		}

	}
	
	public static int findCutVertex(int here, boolean isRoot){
		discovered[here] = counter++;
		int ret = discovered[here];
		int children = 0;
		
		for(int i=0; i<adj[here].size(); i++){
			int there = adj[here].get(i);
			if(discovered[there] == 0){
				++children;
				int subtree = findCutVertex(there, false);
				if(!isRoot && subtree >= discovered[here]){
					isCutVertex[here] = true;
				}
				ret = Math.min(ret, subtree);
			}else{
				ret = Math.min(ret, discovered[there]);
			}
			
		}	
		
		if(isRoot){
			isCutVertex[here] = (children >= 2);
		}
		return ret;
		
	}

}
