package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * [교육A-0011] 동전 교환
 * 유형 : DP
 * 정답여부 : 정답
 * 스스로 풀었는지 여부 : ★★★★☆
 * 기타 : 보석 털이범이 어려워서 쉬운 DP 문제를 내줌
 * */
public class Edu_A_0011 {
	
	public static int N, W;
	public static int c[];
	public static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			c = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				c[i] = Integer.parseInt(st.nextToken());
			}
			
			W = Integer.parseInt(br.readLine().trim());
			
			dp = new int[W+1];
			
			for(int i=0; i<=W; i++) {
				dp[i] = 64001;
			}
			
			dp[0] = 0;
			for(int w=1; w<=W; w++) {
				for(int i=0; i<N; i++) {
					if(w >= c[i]) {
						dp[w] = Math.min(dp[w-c[i]] + 1, dp[w]);
					}
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + dp[W] + "\n");
		}
		bw.close();

	}

}

/*
 * <강사님 풀이>
 * coin: 1, 4, 6
 * 8원 (W)
 * 
 * 1*8					8개
 * 1*4 + 4*1			5개
 * 1*2 + 6*1			3개
 * 4*2					2개
 * 
 * W원을 거슬러주는 필요한 최소 동전 개수
 * =  MIN(
 * 		1원짜리 동전을 쓴 경우  ->  W-1원을 거슬러주는데 필요한 최소 동전 개수 + 1
 * 		4원짜리 동전을 쓴 경우  ->  W-4원을 거슬러주는데 필요한 최소 동전 개수 + 1
 * 		6원짜리 동전을 쓴 경우  ->  W-6원을 거슬러주는데 필요한 최소 동전 개수 + 1
 * 		)
 * 
 * 0원을 거슬러주는데 필요한 최소 동전 개수 : 0개
 * 
 * D[i] = i원을 거슬러주는데 필요한 최소 동전 개수
 * 
 * D[0] = 0
 * D[i] = min( D[i-coin[1]] +1, D[i-coin[2]] + 1, D[i-coin[3]] + 1 .. D[i-coin[N]] + 1 )
 * D[i] = min(D[i-coin[j]] + 1)
 * 
 * if i-coin[j] != 0 && D[i-coin[j]] == 0 ? ==> i-coin[j]원을 만들 수 없다.
 * 위 조건은 INF로 초기화한다면 불필요한 조건?
 * */
