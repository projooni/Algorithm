package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 알고스팟
// ID : EDITORWARS
// 알고리즘 : Union-Find
// 난이도 : 중
// 상태 : 푸는중

public class EditorWars {

	public static class Relation {
		public int idx;
		public int u;
		public int v;
		public int type;

		public Relation(int idx, int u, int v, int type) {
			this.idx = idx;
			this.u = u;
			this.v = v;
			this.type = type;
		}

	}

	public static int N, M;
	public static int[] parent, rank;
	public static Relation relation[];
	public static boolean isShow[];
	public static ArrayList<Integer> disCompList[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N];
			rank = new int[N];
			relation = new Relation[M];
			isShow = new boolean[N];
			disCompList = new ArrayList[N];

			UnionFind();
			
			for(int i=0; i<N; i++) {
				disCompList[i] = new ArrayList<Integer>();
			}

			// TODO String 타입의 크기비교가 가능한지 알아볼것
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int type = "ACK".equals(st.nextToken()) ? 0 : 1;
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				
				isShow[u] =true;
				isShow[v] = true;

				relation[i] = new Relation(i+1, u, v, type);
			}

			// ACK -> DIS 순으로 오름차순 정렬
			Arrays.sort(relation, new Comparator<Relation>() {

				@Override
				public int compare(Relation a, Relation b) {
					// TODO Auto-generated method stub
					return a.type - b.type;
				}

			});

			boolean isPossible = true;
			int impossibleIdx = -1;
			for (int i = 0; i < relation.length; i++) {

				if (relation[i].type == 0) { // 같은 그룹일때 합친다

					merge(relation[i].u, relation[i].v);

				} else { // 이제부터 다른그룹이여야 한다

					int uParent = find(relation[i].u);
					int vParent = find(relation[i].v);

					// 다른그룹이여야하는데, 그룹이 같다?
					if (uParent == vParent) { // 모순 -> 빠져나간다
						isPossible = false;
						impossibleIdx = relation[i].idx;
						break;
					}else {	// 다른 그룹은 명시한다
						disCompList[uParent].add(vParent);
						disCompList[vParent].add(uParent);
					}

				}
			}

			bw.flush();
			if (!isPossible) { // 모순일경우
				bw.write("CONTRADICTION AT " + impossibleIdx + "\n");
			} else { // 가능한 최대 인원은?
				
				int showCnt = 0;
				int cnt[] = new int[N];
				for (int i = 0; i < N; i++) {
					cnt[parent[i]]++;
					if(isShow[i]) {
						showCnt++;
					}
				}
				
				int sum = 0;
				for(int i=0; i<cnt.length; i++) {
					if(cnt[i] > 1) {
						
						int max = 0;
						for(int j=0; j<disCompList[i].size(); j++) {	// 상호배타 그룹
//							if(cnt[i] > cnt[disCompList[i].get(j)])
						}
						
//						sum += cnt[i] > cnt[disComp[cnt[i]]] ? cnt[i] : 0;
					}
				}

				bw.write("MAX PARTY SIZE IS  " + (sum+(N-showCnt)) + "\n");
			}

		}
		bw.close();

	}

	public static void UnionFind() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	public static int find(int u) {
		if (u == parent[u])
			return u;
		return find(parent[u]);
	}

	public static boolean merge(int u, int v) {
		u = find(u);
		v = find(v);

		if (u == v)
			return false;

		if (rank[u] > rank[v]) {
			parent[v] = u;
		} else {
			parent[u] = v;
		}

		if (rank[u] == rank[v])
			++rank[v];

		return true;

	}

}
