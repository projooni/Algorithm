package bj;

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

public class bj1922 {
	
	public static int N, M;
	public static int edge[][];
	public static int p[];
	public static int minCost;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			p = new int[N+1];
			edge = new int[M][3];
			
			for(int i=0; i<p.length; i++) {
				p[i] = i;
			}
			
			for(int i=0; i<M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edge[i][0] = a;
				edge[i][1] = b;
				edge[i][2] = c;
			}
			
			minCost = 0;
			kruscal();
			
			bw.flush();
			bw.write(minCost + "\n");
		}
		bw.close();

	}
	
	public static class Comp implements Comparator<int[]>{

		@Override
		public int compare(int[] o1, int[] o2) {
			// TODO Auto-generated method stub
			return o1[2] - o2[2];
		}
		
	}
	
	public static void kruscal() {
		
		// edge sort
		Arrays.sort(edge, new Comp());
		
		
		//간선의 수 만큼 순회 
		for(int i=0; i<M; i++) {
			
			int a = edge[i][0];
			int b = edge[i][1];
			int c = edge[i][2];
			
			if(find(a) != find(b)) {
				union(a,b);
				minCost += c;
			}
			
			
		}
		
		
		
	}
	
	public static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if(A != B) p[A] = B;
	}
	
	public static int find(int n) {
		if(p[n] == n) return n;
		return p[n] = find(p[n]);
	}

}
