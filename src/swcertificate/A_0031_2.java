package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class A_0031_2 {
	
	public static int N, M;
	public static int[][] matrix;
	public static int START, END;
	public static int Set[];
	public static int weights[];

	public static void main(String[] args) throws NumberFormatException, IOException {
// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			Set = new int[N+1];
			weights = new int[M];

			matrix = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());
				matrix[i][0] = A;
				matrix[i][1] = B;
				matrix[i][2] = S;
				weights[i] = S;
			}

			st = new StringTokenizer(br.readLine());
			START = Integer.parseInt(st.nextToken());
			END = Integer.parseInt(st.nextToken());
			
			Arrays.sort(weights);
		

			long result = Long.MAX_VALUE;
			for(int i=0; i<weights.length; i++) {
				long hi = kruskal(i);
				if(hi >= weights[M-1]) {
					break;
				}
				result = Math.min(result, hi - weights[i]);
			}

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
       

    static long kruskal(int low)   {  

        // Initialize disjoint set  
        for(int i = 1 ; i <= N; i++) Set[i] = 0;  
           
        Arrays.sort(matrix, 0, M, new Comp());

        for(int i = 0 ; i < M ; i++)  
        {  
        	if(matrix[i][2] < weights[low]) {
        		continue;
        	}
        	if(group(matrix[i][0]) != group(matrix[i][1]))  {
        		
        		join(matrix[i][0], matrix[i][1]);  
            	
            	if(group(START) == group(END)) {
            		return matrix[i][2];
            	}
        	}
        	
        	
        }  

        return Long.MAX_VALUE;
        
    }  
    
}
