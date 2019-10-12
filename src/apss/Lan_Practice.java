package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Lan_Practice {
	
	public static int N,M;
	public static int arr[][];
	public static double edge[][];
	public static int[] Set;
	public static double result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N][2];
			edge = new double[N*N][3];
			Set = new int[N];
			result = 0;
			
			for(int i=0; i<N; i++) {
				Set[i] = -1;
				for(int j=0; j<N; j++) {
					edge[i*N+j][0] = -1;
					edge[i*N+j][1] = -1;
					edge[i*N+j][2] = -1;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				edge[start*N+end][0] = start;
				edge[start*N+end][1] = end;
				edge[start*N+end][2] = 0;
				edge[end*N+start][0] = end;
				edge[end*N+start][1] = start;
				edge[end*N+start][2] = 0;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(edge[i*N+j][2] != 0) {
						int x1 = arr[i][0];
						int y1 = arr[i][1];
						int x2 = arr[j][0];
						int y2 = arr[j][1];
						double distance = Math.sqrt(Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y1-y2), 2));
						edge[i*N+j][0] = i;
						edge[i*N+j][1] = j;
						edge[i*N+j][2] = distance;
						edge[j*N+i][0] = j;
						edge[j*N+i][1] = i;
						edge[j*N+i][2] = distance;
					}
				}
			}
			
			kruskal();
			
			bw.flush();
			bw.write(result + "\n");
		}
		
		bw.close();

	}
	
	public static class Comp implements Comparator<double[]>{

		@Override
		public int compare(double[] o1, double[] o2) {
			// TODO Auto-generated method stub
			return (o1[2] - o2[2]) > 0 ? 1 : -1;
		}
		
	}
	
	public static void kruskal() {
		
		Arrays.sort(edge,new Comp());
		
		for(int i=0; i<N*N; i++) {
			int a = (int) edge[i][0];
			int b = (int) edge[i][1];
			if(group(a) != group(b)) {
				result += edge[i][2];
				join(a, b);				
			}
		} 
		
	}
	
	public static int group(int a) {
		if(Set[a] == -1) {
			return a;
		}
		return Set[a] = group(Set[a]);
	}
	
	public static void join(int a, int b) {
		int A = group(a);
		int B = group(b);
		if(A != B) Set[A] = B;
	}


}
