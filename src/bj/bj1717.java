package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class bj1717 {
	
	public static int N, M;
	public static int p[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			p = new int[N+1];
			
			for(int i=0; i<p.length; i++) {
				p[i] = i;
			}
			
			bw.flush();
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cmd == 0) {
					union(a, b);
				}else {
					int A = find(a);
					int B = find(b);
					if(A == B) {
						bw.write("YES\n");
					}else {
						bw.write("NO\n");
					}
				}
				
			}
			
		}
		bw.close();
		

	}
	
	public static void union(int a, int b) {
		
		int A  = find(a);
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
