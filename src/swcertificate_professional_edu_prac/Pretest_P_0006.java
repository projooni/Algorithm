package swcertificate_professional_edu_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SW검정 Professional 양성과정 문제풀이반
 * 2일차
 * [기출P-0006] 아름다운 비트문자열
 * */
public class Pretest_P_0006 {
	
	public static int N,K;
	public static long X;
	public static long p[][];
	public static StringBuffer sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			X = Long.parseLong(st.nextToken());
			
			p = new long[N+1][N+1];
			sb = new StringBuffer();
			
			makePaskal();
			printBinary(N, K, X);
			
			bw.flush();
			bw.write("#" + tc + " " + sb + "\n");
			
		}
		bw.close();

	}
	
	public static void printBinary(int n, int k, long x) {
		
		if(k == 0) {
			if(n != 0) {
				for(int i=0; i<n; i++) {
					sb.append(0);
				}
			}
			return;
		}
		
		for(int i=k; i<=n; i++) {
			if(p[i][k] >= x) {
				
				for(int j=0; j<n-i; j++) {
					sb.append(0);
				}
				
				sb.append(1);
				
				printBinary(i-1, k-1, x-p[i-1][k]);
				
				break;
				
			}
		}
		
	}
	
	public static void makePaskal() {
		
		for(int n=0; n<=N; n++) {
			p[n][0] = 1;
			p[n][n] = 1;
			for(int r=1; r<n; r++) {
				p[n][r] = p[n-1][r] + p[n-1][r-1];
				if(p[n][r] < 0) p[n][r] = Long.MAX_VALUE;
			}
		}
		
	}

}
