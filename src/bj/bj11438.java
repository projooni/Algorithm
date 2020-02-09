package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 내가 짠 코드가 아님! 알고리즘에 대한 이해가 부족하므로 더 알아봐야함.
 * */

public class bj11438 {
	
	public static int N, M;
	public static List<Integer> adjList[];
	public static int tree[];
	public static int par[][];
	public static int tmp;
	private static final int MAX_N = 100000;
	private static final int MAX_D = 17;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {

			par = new int[MAX_D + 1][MAX_N + 1];
			N = Integer.parseInt(br.readLine());
			adjList = new ArrayList[N+1];
			tree = new int[N+1];
			
			for(int i=1; i<N+1; i++) {
				tree[i] = -1;
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			dfs(1,0);
			
			bw.flush();
			M = Integer.parseInt(br.readLine());
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int result = lca2(a,b);
				bw.write(result + "\n");
			}
			
		}
		bw.close();

	}
	
	public static void dfs(int node, int depth) {
		// depth가 갱신되었다면 리턴한다.
		if (tree[node] != -1) {
			return;
		}

		// 현재 노드의 깊이를 depth로 갱신한다.
	    tree[node] = depth;
	    
	   // 자식 노드 순회 
	    for (int next : adjList[node]) {
	    	
	     // 깊이가 이미 갱신이 되었다면 넘어간다. -> 이미 방문했기 때문에 패스?
	      if (tree[next] != -1) {
	    	  continue;
	      }
	        
	      // next노드의 0번째 부모는 node이다. 
	      par[0][next] = node;
	      
	     // max depth까지 순회하면서 par배열을 완성한다.
	      for (int i = 1; i <= MAX_D; i++) {
                if(par[i - 1][next] == 0) break;
                par[i][next] = par[i - 1][par[i - 1][next]];
            }
	        
	      dfs(next, depth + 1);
	    }
	}
	
	private static int lca2(int a, int b) {
		
		// 왜 교환을? -> b의 depth를 더 크게 하기 위해
	    if (tree[a] > tree[b]) {
	      tmp = a;
	      a = b;
	      b = tmp;
	    }
	    
	    // max depth로부터 순회하여, a와b의 depth의 차이가 2의 i
	    for (int i = MAX_D; i >= 0; i--) {
	      if (tree[b] - tree[a] >= (1 << i))
	        b = par[i][b];
	    }
	    
	    // 같아지면 리턴
	    if (a == b)
	      return a;
	    
	    
	    for (int i = MAX_D; i >= 0; i--) {
	      if (par[i][a] != par[i][b]) {
	        a = par[i][a];
	        b = par[i][b];
	      }
	    }
	    
	    return par[0][a];
	    
	  }

}
