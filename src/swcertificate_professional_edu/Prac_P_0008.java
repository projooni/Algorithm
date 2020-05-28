package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 연습 P-0008
 * 유형 : 벨만포드
 * 26/35
 * 아직 못풀었음 이유를 알 수 없음
 * */
public class Prac_P_0008 {
	
	public static int N,M,W;
	public static int arr[][];
	public static int dist[];
	public static final int MAX_VALUE = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			arr = new int[M+W][3];
			dist = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				dist[i]  = MAX_VALUE;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				arr[i][0] = s;
				arr[i][1] = e;
				arr[i][2] = t;
			}
			
			for(int i=M; i<M+W; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				arr[i][0] = s;
				arr[i][1] = e;
				arr[i][2] = t*(-1);
			}
			
			String ans1 = bellman(1,0);
//			for(int i=1; i<=N; i++) {
//				dist[i]  = MAX_VALUE;
//			}
//			String ans2 = bellman(1,1);
			
//			String ans = "YES".equals(ans1) && "YES".equals(ans2) ? "YES" : "NO";
			
			bw.flush();
			bw.write("#" + tc + " " + ans1 + "\n");
		}
		bw.close();

	}
	
	public static String bellman(int start, int type) {
		
		String ret = "NO";
		
		dist[start] = 0;
		
		for(int i=1; i<N; i++) {
			for(int j=0; j<M+W; j++) {
				int s = arr[j][0];
				int e = arr[j][1];
				if(type == 1) {
					e = arr[j][0];
					s = arr[j][1];
				}
				int t = arr[j][2];
				if(dist[s] != MAX_VALUE && dist[s] + t < dist[e]) {
					dist[e] = dist[s] + t;
				}
			}
		}
		
		for(int j=0; j<M+W; j++) {
			int s = arr[j][0];
			int e = arr[j][1];
			if(type == 1) {
				e = arr[j][0];
				s = arr[j][1];
			}
			int t = arr[j][2];
			if(dist[s] != MAX_VALUE && dist[s] + t < dist[e]) {
				ret = "YES";
			}
		}
		
		return ret;
		
	}

}

