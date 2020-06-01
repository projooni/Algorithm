package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 숫자 배치하기
 * */
public class Pretest_A_0013 {
	
	public static int N;
	public static int arr[][];
	public static int max;
	public static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1][N+1];
			visited = new boolean[N+1];
			max = 0;
			
			// 초기화
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			getMaxValue(0,0);			
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}
	
	public static void getMaxValue(int sum, int len) {
		
		if(len == N) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				getMaxValue(sum+arr[i][len+1], len+1);
				visited[i] = false;
			}
			
		}
		
	}

}
