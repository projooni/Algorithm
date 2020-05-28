package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * ±³À° P-0016 µ¿±¼
 * */
public class Edu_P_0016 {
	// 2 <= N <= 200000 (Ç×»ó Â¦¼ö)
	// 2 <= H <= 500000
	public static int N, H;
	public static int len[];
	public static int A1[], A2[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			len = new int[N];
			A1 = new int[H+1];
			A2 = new int[H+1];
			
			for(int i=0; i<N; i++) {
				len[i] = Integer.parseInt(br.readLine());
				if(i%2 == 0) {
					A1[len[i]]++;
				}else {
					A2[H-len[i]+1]++;
				}
			}
			
			for(int i=H-1; i>=1; i--) {
				A1[i] += A1[i+1];
			}
			
			for(int i=1; i<=H-1; i++) {
				A2[i+1] += A2[i];
			}
			
			int min = Integer.MAX_VALUE;
			int count = 0;
			for(int i=1; i<=H; i++) {
				if(min > A1[i] + A2[i]) {
					min = A1[i] + A2[i];
					count = 1;
				}else if(min == A1[i] + A2[i]) {
					count++;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + min + " " + count +  "\n");
		}
		bw.close();

	}

}
