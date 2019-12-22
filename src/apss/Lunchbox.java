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

public class Lunchbox {
	
	public static int N;
	public static int[][] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o2[1] - o1[1];
				}
				
			});
			
			int result = greed();
			
			bw.flush();
			bw.write(result + "\n");
		}
		bw.close();

	}
	
	// 점심을 먹는데 걸리는 최소시간 
	public static int greed() {
		
		int burn = 0;
		int eat = 0;
		
		for(int i=0; i<arr.length; i++) {
			burn += arr[i][0];
			eat = Math.max(eat, burn + arr[i][1]);
		}
		
		return eat;
		
	}

}
