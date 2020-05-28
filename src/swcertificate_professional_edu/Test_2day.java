package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 2일차 모의고사
 * - 투포인터?
 * - 첫번째 시도 (정렬 x)
 * - 아직 못품
 * - 투포인터가 모든 케이스를 포괄하는지에 대해 이해 x
 * */
public class Test_2day {
	
	public static int X, N;
	public static int arr[];
	public static final int NANO_TO_CENTI = 10000000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			X = Integer.parseInt(br.readLine().trim());
			N = Integer.parseInt(br.readLine().trim());
			
			arr = new int[N];
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(br.readLine().trim());
			}
			
			int start = 0;
			int end = 0;
			boolean ansExist = false;
			long max = 0;
			
			int max_idx_s = 0;
			int max_idx_e = 0;
			while(N != 0 && (start != N || end != N)) {
				
				if((start != end) && (X*NANO_TO_CENTI == (arr[start] + arr[end]))) {
					if(max < Math.abs(arr[end]-arr[start])) {
						max = Math.abs(arr[end]-arr[start]);
						max_idx_s = start;
						max_idx_e = end;
					}
					ansExist = true;
				}
				
				if(end < N-1) {
					end++;
				}else if(start < N-1) {
					start++;
				}else {
					break;
				}
				
			}
			
			String result = "";
			if(!ansExist) {
				 result = "danger";
			}else {
				int result_small = 0;
				int result_big = 0;
				if(arr[max_idx_s] <= arr[max_idx_e]) {
					result_small = arr[max_idx_s];
					result_big = arr[max_idx_e];
				}else {
					result_small = arr[max_idx_e];
					result_big = arr[max_idx_s];
				}
				result = "yes" + " " + result_small + " " + result_big;
			}
			bw.flush();
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.close();
		
	}

}
