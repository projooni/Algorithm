package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class A_0031 {
	
	public static int N, M;
	public static List<Integer> adjList[];
	public static int[][] matrix;
	public static int START, END;
	public static int diff[];
	public static int minmax[][];
	public static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase<=T; testCase++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			diff = new int[N + 1];
			adjList = new ArrayList[N + 1];
			minmax = new int[N+1][2];
			visited = new boolean[N+1];
			
			for (int i = 0; i < N + 1; i++) {
				adjList[i] = new ArrayList<Integer>();
				diff[i] = Integer.MAX_VALUE;
				minmax[i][0] = Integer.MAX_VALUE;
				minmax[i][1] = Integer.MAX_VALUE;
			}

			matrix = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());
				matrix[A][B] = S;
				matrix[B][A] = S;
				adjList[A].add(B);
				adjList[B].add(A);
			}
	
			st = new StringTokenizer(br.readLine());
			START = Integer.parseInt(st.nextToken());
			END = Integer.parseInt(st.nextToken());
	
			search(START, -1);
		    int positive_diff = diff[END];
		    visited = new boolean[N+1];
		    for (int i = 0; i < N + 1; i++) {
				diff[i] = Integer.MAX_VALUE;
				minmax[i][0] = Integer.MAX_VALUE;
				minmax[i][1] = Integer.MAX_VALUE;
			}
		    search(END, -1);
		    int negative_diff = diff[START];
		    
		    int result_min = Math.min(positive_diff, negative_diff);
	
		    
			bw.flush();
			bw.write("#" + testCase + " " + result_min + "\n");
		}
		bw.close();
	}

	public static void search(int start, int dest) {
		
		Queue<Integer> Q = new LinkedList<>(); 
		
		 //노드번호, max, min 
		 Q.add(start); 
		 
		 while(!Q.isEmpty()) { 
			 int curr = Q.poll();
			 
			 if(visited[curr]) {
				 continue;
			 }
			 
			 visited[curr] = true;
			 
			 if(curr == dest) { 
				 break; 
			}
			 
			 for(int i=0; i<adjList[curr].size(); i++) { 
				 int next = adjList[curr].get(i);
				 int S = matrix[curr][next]; 
				 
				 // 한번도 갱신된적 없는 경우
				int next_max = 0;
				int next_min = 0;
				if(Integer.MAX_VALUE == minmax[curr][0] || Integer.MAX_VALUE == minmax[curr][1]) {
					next_max = S;
					next_min = S;
				}else {
					next_max = Math.max(minmax[curr][0], S);
					next_min = Math.min(minmax[curr][1], S);
				}
				 
				 int next_diff = Math.abs(next_max - next_min); 
				 if(next_diff < diff[next]) {
					 minmax[next][0] = next_max;
					 minmax[next][1] = next_min;
					 diff[next] = next_diff;
					 Q.add(next); 
				 } 
			} 
			 
		} 
		 
	}

}
