package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 4일차 >
 *  
 * 문제명    : [기출P-0038] 키컸으면
 * 난이도    : 중상
 * 유형       : 인덱스트리 or 세그먼트 트리 or 펜윅 트리
 * 정답여부  : O
 * 기여도    : 80% (강사 힌트 듣고 품)
 * 기타
 *   
 * */

public class Pretest_P_0038 {
	
	public static int N,Q;
	public static long indexTree[];
	public static int base;
	public static int arr[][];
	public static int q[][];
	public static long ans[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			arr = new int[N+1][2];
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<=N; i++) {
				arr[i][0] = i;
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o2[1] - o1[1];
				}
				
			});
			
			base = 1 << (int)(Math.log(N-1)/Math.log(2)) + 1;
			indexTree = new long[2*base];
			q = new int[Q][4];
			ans = new long[Q];
			
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				q[i][0] = i;
				q[i][1] = a;
				q[i][2] = b;
				q[i][3] = x;
			} 
			
			Arrays.sort(q, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o2[3] - o1[3];
				}
				
			});
			
			int k = 0;
			for(int i=0; i<q.length; i++) {
				int orgIdx = q[i][0];
				int a = q[i][1];
				int b = q[i][2];
				int c = q[i][3];
				
				for(int j=k; j<N; j++) {
					int idx = arr[j][0];
					int h = arr[j][1];
					if(h > c) {
						update(idx ,1);
						k++;
					}else {
						break;
					}
				}
				
				long result = query(a,b);
				ans[orgIdx] = result;
				
			}
			
			bw.flush();
			bw.write("#" + tc);
			for(int i=0; i<ans.length; i++) {
				bw.write(" " + ans[i]);
			}
			bw.write("\n");
			
		}
		bw.close();
		
	}
	
public static void update(int idx, int value) {
		
		int i = base + idx - 1;
		indexTree[i] = value;
		while(i > 1) {
			i /= 2;
			indexTree[i] = indexTree[i*2] + indexTree[i*2+1];
		}
		
	}
	
	public static long query(int a, int b) {
		
		long sum = 0;
		
		int l = base + a - 1;
		int r = base + b - 1;
		
		while(l < r) {
			
			if(r%2 == 0) {
				sum += indexTree[r];
				r -= 1;
				r /= 2;
			}else {
				r /=2;
			}
			
			if(l%2 == 1) {
				sum += indexTree[l];
				l += 1;
				l /= 2;
			}else {
				l /=2;
			}
		}
		
		if(l == r) {
			sum += indexTree[l];
		}
		
		return sum;
		
		
	}

}
