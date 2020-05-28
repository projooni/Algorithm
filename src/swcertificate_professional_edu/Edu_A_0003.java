package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * [교육A-0003] 폐지 줍기
 * */
public class Edu_A_0003 {
	
	public static int N;
	public static int arr[][];
	public static int dp[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp = new int[N+1][N+1];
			
			// dp[i][j] : i, j까지 오면서 주울 수 있는 폐지의 최대값
			// dp[i][j] = Math.max(dp[i-1][j] + arr[i][j], dp[i][j-1] + arr[i][j])
			
			dp[1][1] = arr[1][1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i == 1 && j == 1) {
						continue;
					}
					dp[i][j] = Math.max(dp[i-1][j] + arr[i][j], dp[i][j-1] + arr[i][j]);
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + dp[N][N] + "\n");
		}
		bw.close();

	}

}
