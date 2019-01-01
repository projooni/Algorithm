package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 알고스팟
// ID : EDITORWARS
// 알고리즘 : Union-Find
// 난이도 : 중
// 상태 : 풀이완료(답안)

public class EditorWars_Solution {

	public static int N, M;
	public static int[] parent, rank, size, enemies;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("C://dev/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N];
			rank = new int[N];
			size = new int[N];
			enemies = new int[N];
			
			// 초기화
			UnionFind();

			boolean isPossible = true;
			int impsIdx = -1;
			// TODO String 타입의 크기비교가 가능한지 알아볼것
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int type = "ACK".equals(st.nextToken()) ? 0 : 1;
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				if(!isPossible){
					continue;
				}
				
				if(type == 0){	// ACK
					isPossible = ack(u,v);
				}else{
					isPossible = dis(u,v);
				}
				
				if(!isPossible){
					impsIdx = i+1;
				}
			}
			
			bw.flush();
			if(isPossible){
				bw.write("MAX PARTY SIZE IS " + maxParty() + "\n");
			}else{
				bw.write("CONTRADICTION AT " + impsIdx + "\n");
			}
			
		}
		bw.close();

	}

	public static void UnionFind() {
		
		Arrays.fill(enemies, -1);
		Arrays.fill(size, 1);
		
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	public static int find(int u) {
		if (u == parent[u])
			return u;
		return find(parent[u]);
	}

	public static int merge(int u, int v) {
		
		int rtVal = 0;
		
		// u나 v가 공집합인 경우 나머지 하나를 반환한다.
		if(u == -1 || v == -1) return Math.max(u,v);
		
		u = find(u);
		v = find(v);

		// 이미 둘이 같은 트리에 속한 경우
		if (u == v)
			return u;

		if (rank[u] > rank[v]) { // v를 u에 편입시킨다
			parent[v] = u;
			size[u] += size[v];
			rtVal = u;
		} else { // u를 v에 편입시킨다
			parent[u] = v;
			size[v] += size[u];
			rtVal = v;
		}

		if (rank[u] == rank[v])
			++rank[v];

		return rtVal;

	}
	
	public static boolean dis(int u, int v){
		
		u = find(u);
		v = find(v);
		
		// 같은 집합에 속해 있으면 모순!
		if(u == v) return false;
		
		// 적의 적은 나의 동지
		int a = merge(u, enemies[v]);
		int b = merge(v, enemies[u]);
		
		enemies[a] = b;
		enemies[b] = a;
		
		return true;
		
	}
	
	public static boolean ack(int u, int v){
		
		u = find(u);
		v = find(v);
		
		// 두 집합이 서로 적대 관계라면 모순!
		if(enemies[u] == v) return false;
		
		// 동지의 적은 나의 적
		int a = merge(u, v);
		int b = merge(enemies[u], enemies[v]);
		enemies[a] = b;
		if(b != -1) enemies[b] = a;
		
		return true;
		
	}
	
	public static int maxParty(){
		int ret = 0;
		for(int node=0; node<N; node++){
			if(parent[node] == node){ // 루트일경우
				int enemy = enemies[node];
				
				// 같은 모임쌍을 두 번 세지 않기 위해, enemy < node인 경우만 센다.
				// enemy == -1인 경우도 정확히 한 번씩 세게 된다.
				if(enemy > node) continue;
				
				int mySize = size[node];
				int enemySize = (enemy == -1 ? 0 : size[enemy]);
				
				// 두 집합 중 큰 집합을 더한다.
				ret += Math.max(mySize, enemySize);
				
			}
		}
		return ret;
	}

}
