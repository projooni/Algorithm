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
 * [연습P-0023] 동맹의 동맹은 동맹
 * */
public class Prac_P_0023 {
	
	public static int N,Q;
	public static int p[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			Q = Integer.parseInt(br.readLine().trim());
			
			p = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				p[i] = i;
			}
			
			int cnt = 0;
			for(int i=0; i<Q; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int q = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(q == 0) {
					union(a,b);
				}else {
					int A = find(a);
					int B = find(b);
					if(A == B) {
						cnt++;
					}
				}
				
			}
			
			bw.flush();
			bw.write("#" + tc + " " + cnt + "\n");
		}
		bw.close();

	}
	
	public static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		if(A != B) {
			p[A] = B;
		}
		
	}
	
	public static int find(int n) {
		if(p[n] == n) {
			return n;
		} 
		return p[n] = find(p[n]);
	}

}
