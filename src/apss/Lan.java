package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Lan {
	
	public static int N,M,E;
	public static int node[][];
	public static int Set[];
	public static double Edge[][];
	public static double sum;
	public static int selected;
	
	 public static void main(String[] args) throws Exception{
		 
		 System.setIn(new FileInputStream("D:\\sample_input.txt"));
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 
		 int T = Integer.parseInt(br.readLine().trim());
		 for(int tc=1; tc<=T; tc++) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 M = Integer.parseInt(st.nextToken());
			 E = N * N;
			 
			 node = new int[N][2];
			 Set = new int[N]; // start with 1. index 0 is not used.
			 Edge = new double[E][3];
			 sum = 0;
			 selected = 0;
			 
			 // x좌표
			 st = new StringTokenizer(br.readLine());
			 for(int i=0; i<N; i++) {
				 node[i][0] = Integer.parseInt(st.nextToken());
			 }
			 
			 // y좌표
			 st = new StringTokenizer(br.readLine());
			 for(int i=0; i<N; i++) {
				 node[i][1] = Integer.parseInt(st.nextToken());
			 }
			 
			 Arrays.fill(Set, -1);
			 
			 // join
			 for(int i=0; i<M; i++) {
				 st = new StringTokenizer(br.readLine());
				 int s = Integer.parseInt(st.nextToken());
				 int e = Integer.parseInt(st.nextToken());
				 join(s,e);
				 selected++;
			 }
			 
			 for(int i=0; i<E; i++) {
					 Edge[i][0] = i/N;
					 Edge[i][1] = i%N;
					 Edge[i][2] = Math.sqrt( 
							 Math.abs(node[(int)Edge[i][0]][0] - node[(int)Edge[i][1]][0]) * Math.abs(node[(int)Edge[i][0]][0] - node[(int)Edge[i][1]][0]) + 
							 Math.abs(node[(int)Edge[i][0]][1] - node[(int)Edge[i][1]][1]) * Math.abs(node[(int)Edge[i][0]][1] - node[(int)Edge[i][1]][1])
					 );
			 }
			 
			 Arrays.sort(Edge, new Comparator<double[]>() {

				@Override
				public int compare(double[] o1, double[] o2) {
					// TODO Auto-generated method stub
					return o1[2] - o2[2] > 0 ? 1 : -1;
				}

				 
			 });
			 
			 for(int i=0; i<E; i++) {
				 
				 if(group((int)Edge[i][0]) != group((int)Edge[i][1])) {
					 sum += Edge[i][2];
					 selected++;
					 join((int)Edge[i][0], (int)Edge[i][1]);  
				 }
				 
			 }
			 
			 
			 bw.flush();
			 bw.write(sum+"\n");
			 
			 
		 }
		 bw.close();
		 
		 
	 }
	 
	 // Union-Find : group
	 public static int group(int n) {  

	        if (Set[n] == -1) return n;  

	        return Set[n] = group(Set[n]); // path compression  

	    }  

	 //	Union-Find : join
	  public static void join(int a, int b) {  

	        int A = group(a), B = group(b);  

	        if (A != B) Set[A] = B;  

	    } 

}
