package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class StrJoin {
	
	public static int N;
	public static int arr[];
	public static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;
			getMinStrJoinCost();
			
			bw.flush();
			bw.write(result + "\n");
			
		}
		bw.close();

	}
	
	public static void getMinStrJoinCost() {
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(int i=0; i<N; i++) {
			pq.add(arr[i]);
		}
		
		while(!pq.isEmpty()) {
			int a = pq.poll();
			int b = pq.isEmpty() ? 0 : pq.poll();
			int joinCost = a+b;
			result += joinCost;
			if(!pq.isEmpty()) {
				pq.add(joinCost);
			}
		}
		
	}

}
