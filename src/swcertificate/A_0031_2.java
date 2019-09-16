package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class A_0031_2 {

	public static int N, M;
	public static int[][] matrix;
	public static int START, END;
	public static int Set[];
	public static int pre[];

	public static void main(String[] args) throws NumberFormatException, IOException {
// TODO Auto-generated method stub
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Set = new int[N+1];
			pre = new int[N+1];

			matrix = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());
				matrix[i][0] = A;
				matrix[i][1] = B;
				matrix[i][2] = S;
			}

			st = new StringTokenizer(br.readLine());
			START = Integer.parseInt(st.nextToken());
			END = Integer.parseInt(st.nextToken());

			long result = kruskal(N, M);

			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
		}
		bw.close();
	}


	static int group(int n) {  

        if (Set[n] == 0) return n;  

        return Set[n] = group(Set[n]); // path compression  

    }  

   

    static void join(int a, int b) {  

        int A = group(a), B = group(b);  

        if (A != B) Set[A] = B;  

    }  
    
    static class Comp implements Comparator<int[]>  

    {  

        @Override 

        public int compare(int[] arg0, int[] arg1) {  

            // TODO Auto-generated method stub  

            return arg0[2] - arg1[2];  

        }  

    }  

       

    static long kruskal(int V, int E)  

    {  

        long max_cost = 0;
        long min_cost = Long.MAX_VALUE;

        int selected = 0;  

        // Initialize disjoint set  

        for(int i = 1 ; i <= V ; i++) Set[i] = 0;  

           

        Arrays.sort(matrix, 0, E, new Comp());  

           

        for(int i = 0 ; i < E ; i++)  

        {  

        	// 사이클 확인. from 정점과 to 정점이 같은 group이면 사이클이다.
            if(group(matrix[i][0]) != group(matrix[i][1]))  

            {  

                //mst_cost += edge[i].cost;  

                max_cost = Math.max(matrix[i][2], max_cost);  
                min_cost = Math.min(matrix[i][2], min_cost);

                selected++;  

                join(matrix[i][0], matrix[i][1]);  
                pre[matrix[i][0]] = matrix[i][1];

            }  

        }  


        // 선택된 간선들의 수가 정점의 수보다 1적으면 최소신장트리 완성.
        if(selected == V-1) return max_cost - min_cost; // return mst cost  

        else return -1; // can't make mst  

    }  
    
}
