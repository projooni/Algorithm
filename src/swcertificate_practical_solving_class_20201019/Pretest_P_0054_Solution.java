package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 2일차 >
 *  
 * 문제명    : [기출P-0054] 자동차 여행
 * 난이도    : 
 * 유형       : DP
 * 정답여부  : O (강사소)
 * 기여도    :  0%
 * 기타			: 
 *   
 * */

public class Pretest_P_0054_Solution {
	
	public static int N,M,B;
	public static int arr[][];
	public static int edge[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			arr = new int[1001][N+1];
			for(int i=0; i<=1000; i++) {
				Arrays.fill(arr[i], B);
			}
			
			edge = new int[M][3];
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edge[i][0] = a;
				edge[i][1] = b;
				edge[i][2] = c;
			}
			
			arr[0][1] = 0;
			int step = 0;
			
			for(int i=1; i<=1000; i++) {
				boolean update = false;
				for(int j=0; j<M; j++) {
					
					if( arr[i][edge[j][1]] >= arr[i-1][edge[j][0]] + edge[j][2] ) {
						arr[i][edge[j][1]] = arr[i-1][edge[j][0]] + edge[j][2];
						step = i;
						update = true;
					}
					
				}
				if(!update) {
					break;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + step + "\n");
		}
		bw.close();

	}

}
