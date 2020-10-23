package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 1일차 >
 *  
 * 문제명    : [기출P-0065] Shield Up!
 * 난이도    : 중상
 * 유형       : pq?
 * 정답여부 : O
 * 기여도    : 기억안남. 내가 풀긴 했던듯 (힌트얻어서?)
 * 기타
 *   
 * */

public class Pretest_P_0065 {
	
	public static int N,M;
	public static int arr[][];
	public static boolean visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		
			arr = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				String row = br.readLine().trim();
				for(int j=0; j<M; j++) {
					int val = row.charAt(j) - '0';
					arr[i][j] = val;
				}
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[2] - o2[2];
				}
				
			});
			
			// 주변에 0인 외계전함이 있는 지구전함들을 PQ에 넣는다
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					
					if(arr[i][j] > 0) {
						//왼쪽
						if(j==0 || arr[i][j-1] == 0) {
							pq.add(new int[]{i,j,arr[i][j]});
							visited[i][j] = true;
							continue;
						}
						
						//위
						if(i==0 || arr[i-1][j] == 0) {
							pq.add(new int[]{i,j,arr[i][j]});
							visited[i][j] = true;
							continue;
						}
						
						//오른쪽
						if(j==M-1 || arr[i][j+1] == 0) {
							pq.add(new int[]{i,j,arr[i][j]});
							visited[i][j] = true;
							continue;
						}
						
						// 아래
						if(i==N-1 || arr[i+1][j] == 0) {
							pq.add(new int[]{i,j,arr[i][j]});
							visited[i][j] = true;
							continue;
						}
						
					}
					
				}
			}
			
			int count = 0;
			while(!pq.isEmpty()) {
				
				int curr[] = pq.poll();
				int i = curr[0];
				int j = curr[1];
				
				count += curr[2];
				
				//왼쪽
				if(j != 0 && arr[i][j-1] != 0) {
					if(visited[i][j-1] == false) {
						pq.add(new int[]{i,j-1,curr[2]+arr[i][j-1]});
						visited[i][j-1] = true;
					}
				}
				
				//위
				if(i != 0 && arr[i-1][j] != 0) {
					if(visited[i-1][j] == false) {
						pq.add(new int[]{i-1,j,curr[2]+arr[i-1][j]});
						visited[i-1][j] = true;
					}
				}
				
				//오른쪽
				if(j != M-1 && arr[i][j+1] != 0) {
					if(visited[i][j+1] == false) {
						pq.add(new int[]{i,j+1,curr[2]+arr[i][j+1]});
						visited[i][j+1] = true;
					}
				}
				
				// 아래
				if(i != N-1 && arr[i+1][j] != 0) {
					if(visited[i+1][j] == false) {
						pq.add(new int[]{i+1,j,curr[2]+arr[i+1][j]});
						visited[i+1][j] = true;
					}
				}
				
			}
			
			bw.flush();
			bw.write("#" + tc + " " + count + "\n");
		}
		bw.close();

	}

}
