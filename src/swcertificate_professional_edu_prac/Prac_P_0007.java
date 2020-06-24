package swcertificate_professional_edu_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * SW검정 Professional 양성과정 문제풀이반
 * 3일차
 * [연습P-0007] 고속도로 건설
 * */
public class Prac_P_0007 {
	
	public static int N,M;
	public static int edge[][];
	public static int p[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			
			p = new int[N+1];
			for(int i=0; i<=N; i++) {
				p[i] = i;
			}
			
			edge = new int[M][3];
			
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edge[i][0] = s;
				edge[i][1] = e;
				edge[i][2] = c;
			}
			
			int result = kruskal();
			
			
			bw.flush();
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.close();

	}
	
	public static int kruskal() {
		
		Arrays.sort(edge,new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[2] - arg1[2];
			}
			
		});
		
		int sum = 0;
		for(int i=0; i<M; i++) {
			
			if(find(edge[i][0]) != find(edge[i][1])) {
				sum += edge[i][2];
				union(edge[i][0],edge[i][1]);
			}
			
		}
		
		return sum;
		
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
