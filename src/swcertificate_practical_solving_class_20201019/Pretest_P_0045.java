package swcertificate_practical_solving_class_20201019;

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
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 4일차 >
 *  
 * 문제명    : [기출P-0045] 검문소
 * 난이도    : 중상
 * 유형       : DFS(단절점)
 * 정답여부 : O
 * 기여도    : 50%
 * 기타
 *   
 * */

public class Pretest_P_0045 {
	
	public static int N,M;
	public static List<Integer> adjList[];
	public static boolean visited[];
	public static int d[];
	public static int inOrder;
	public static long max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 2 <= N <= 100000
			M = Integer.parseInt(st.nextToken()); // 1 <= M <= 500000
			
			visited = new boolean[N+1];
			adjList = new ArrayList[N+1];
			d = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			max = -1;
			inOrder = 0;
			dfs(1, -1);	
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}
	
	public static long dfs(int curr, int parent) {
		
		// in-order 저장하는 방법 (패턴 암기)
		d[curr] = ++inOrder;
		
		// min : 자식 노드들이 접근할 수 있는 최소 in-order 값
		long min = d[curr];
		for(int i=0; i<adjList[curr].size(); i++) {
			int next = adjList[curr].get(i);
			
			if(next == parent) {
				continue;
			}
			
			// in-order 값이 없다면 방문을 안한것이므로 방문한다.
			if(d[next] == 0) {
				long num = dfs(next, curr);
				
				// 단절점 (자식이 접근할 수 있는 최소 in-order가 나의 in-order보다 크면 단절점이다)
				if(num > d[curr]) {
					// 단절점인경우, 가장 큰 in-order에서 자식의 in-order를 빼고 +1 하면 자식 수가 나온다.
					long childCnt = inOrder - num + 1;
					max = Math.max(max, (long)(childCnt * (N-childCnt) * 2));
				}
				
				min = Math.min(min, num);
			}else {
				min = Math.min(min, d[next]);
			}
			
		}		
		
		return min;
		
	}

}
