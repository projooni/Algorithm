package swcertificate_professional_edu;

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
 * [연습A-0002] 키순서
 * 유형 : 플로이드 워셜
 * */
public class Prac_A_0002 {
	
	public static int N,M;
//	public static List<Integer> adjList[];
	public static int arr1[][];
	public static int arr2[][];
	public static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			
			arr1 = new int[N+1][N+1];
			arr2 = new int[N+1][N+1];
			
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				
				// a는 b보다 키가 작다.
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr1[a][b] = 1;
				arr2[b][a] = 1;
				
			}
			
			for(int i=1; i<=N; i++) {
				arr1[i][i] = 1;
				arr2[i][i] = 1;
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(arr1[i][k] + arr1[k][j] == 2) {
							arr1[i][j] = 1;
						}
						if(arr2[i][k] + arr2[k][j] == 2) {
							arr2[i][j] = 1;
						}
					}
				}
			}
			
			int result = 0;
			for(int i=1; i<=N; i++) {
				boolean isOk = true;
				for(int j=1; j<=N; j++) {
					if(arr1[i][j] != 1 && arr2[i][j] != 1) {
						isOk = false;
						break;
					}
				}
				if(isOk) {
					result++;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + result +  "\n");
		}
		bw.close();

	}

}
