package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj2014 {
	
	public static int K; // 1<= K <=100
	public static int N; // 1<= N <=100000
	public static int arr[];
	public static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			arr  = new int[K];
			pq = new PriorityQueue<Integer>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				pq.add(arr[i]);
			}
			
			
 			int result = search();
			
			bw.flush();
			bw.write(result + "\n");
			
		}
		bw.close();
		
	}
	
	public static int search() {
		
		int i = 1;
		while(i < N) {
			
			int n = pq.poll();
			for(int j=0; j<K; j++) {
				pq.add(n * arr[j]);
				
				if(n % arr[j] == 0) {
					break;
				}
			}
			
			i++;
			
		}
		
		return pq.poll();
		
	}

}
