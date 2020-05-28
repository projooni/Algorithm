package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 2일차 모의고사
 * - 투포인터?
 * - 강정묵 프로님한테 받은 소스
 * */
public class Test_2day_2 {
	
	public static int X, N;
	public static long arr[];
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
			
			arr = new long[N];
			
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(br.readLine().trim());
			}
			
			Arrays.sort(arr);
			
			int start = 0;
			int end = N-1;
			long sum = N == 0 ? 0 : arr[start] + arr[end];
			int cnt = 0;
			while(start < end) {
				/*
				if((start != end) && (X*NANO_TO_CENTI == sum)) {
					if(max < Math.abs(arr[end]-arr[start])) {
						max = Math.abs(arr[end]-arr[start]);
						result_idx_s = start;
						result_idx_e = end;
					}
					cnt++;
				}
				*/
				
				sum = arr[start] + arr[end];
				
				if(sum == X*NANO_TO_CENTI) {
					cnt++;
					break;
				}else if(sum > X*NANO_TO_CENTI) {
					end--;
				}else {
					start++;
				}
				
			}
			
			String result = "";
			if(cnt == 0) {
				 result = "danger";
			}else {
				result = "yes" + " " + arr[start] + " " + arr[end];
			}
			bw.flush();
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.close();
		
	}

}
