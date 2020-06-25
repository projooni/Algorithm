package swcertificate_professional_edu_prac;

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
 * SW검정 Professional 양성과정 문제풀이반
 * 4일차
 * [연습P-0038] 키컸으면
 * 상태 : 푸는중
 * 유형 : 구간트리
 * */

public class Pretest_P_0038 {
	
	public static int N,Q;
	public static int h[];
	public static int q[][];
	public static int indexTree[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0; i<N; i++) {
				h[i] =  Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(h);
			
			q = new int[Q][5];
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine().trim());
				q[i][0] = i;
				q[i][1] = Integer.parseInt(st.nextToken());
				q[i][2] = Integer.parseInt(st.nextToken());
				q[i][3] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(q, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[3] - o2[3];
				}
				
			});
			
			int base = 1 << (int)(Math.log(N-1)/Math.log(2)) + 1;
			indexTree = new int[base*2];
			
			for(int i=0; i<Q; i++) {
				int a = q[i][1];
				int b = q[i][2];
				int height = q[i][3];
				
				for(int j=a; j<=b; j++) {
					update(j+base-1,1);
				}
				
			}
			
			
			
			
			bw.flush();
			bw.write("#");
		}
		bw.close();

	}
	
	public static void update(int idx, int val) {
		indexTree[idx] = val;
		while(idx > 1) {
			indexTree[idx] = indexTree[idx*2] + indexTree[idx*2+1];
		}
	}

}
