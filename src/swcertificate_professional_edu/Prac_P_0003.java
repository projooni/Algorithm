package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.TreeSet;

/*
 * 연습 P-0003 소수의 곱
 * 
 *  연관문제 : 백준 2014 소수의 곱
 */
public class Prac_P_0003 {
	
	public static int N, K;
	public static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			K = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine());
			
			arr = new int[K];
			
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			TreeSet<Integer> set = new TreeSet<Integer>();
			
			for(int i=0; i<K; i++) {
				arr[i] = Integer.parseInt(br.readLine());
				pq.add(arr[i]);
				set.add(arr[i]);
			}
			
			int cnt = 0;
			int curr = 0;
			int mx = Integer.MAX_VALUE;
			while(cnt != N && !pq.isEmpty()){
				
				curr = pq.poll();
				cnt++;
				
				for(int j=0; j<K; j++) {
					
					if((long)curr*arr[j] < (long)mx &&  !set.contains(curr*arr[j])) {
						pq.add(curr*arr[j]);
						set.add(curr*arr[j]);
					}
					
					// pq가 무한히 커지는것을 방지
					if(set.size() >= 100000 && mx == Integer.MAX_VALUE) {
						mx = set.last();
					}
					
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + curr + "\n");
		}
		bw.close();

	}

}
