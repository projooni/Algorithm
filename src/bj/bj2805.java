package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 이진탐색 시간복잡도 : O(logN)
 * */

public class bj2805 {
	
	public static int N,M;
	public static int maxHeight = 0;
	public static int tree[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			tree = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				if(tree[i] > maxHeight) {
					maxHeight = tree[i];
				}
			}
			
			long result = search();
			
			bw.flush();
			bw.write(result + "\n");
			
			
		}
		bw.close();

	}
	
	public static long search() {
		int left = 0;
		int right = maxHeight;
		long sum = 0;
		int mid = 0;
		int result = 0;
		
		while(left <= right) {
			sum = 0;
			
			mid = (left + right) / 2;
			for(int i=0; i<tree.length; i++) {
				sum += (tree[i] - mid) < 0 ? 0 : (tree[i] - mid);
			}
			
			// 적어도 M미터 = M미터 이상을 갖고 갈 수 잇는 최대 Height
			// 좀 더 알아볼 것 -> 왜 mid가 아닌 mid+1 또는 mid-1로 갱신하는가?
			if(sum >= M) {
				result = Math.max(result, mid);
				left = mid+1;
			}else {
				right = mid-1;
			}
			
			
		}
		
		return result;
		
	}

}
