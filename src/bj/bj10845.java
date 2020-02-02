package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class bj10845 {
	
	public static int N;
	public static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[10001];
			int left = 0;
			int right = 0;
			bw.flush();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				
				if("push".equals(cmd)){
					int param = Integer.parseInt(st.nextToken());
					arr[right] = param;
					right++;
				}else if("pop".equals(cmd)){
					if(left == right) {
						bw.write(-1 + "\n");
					}else {
						bw.write(arr[left] + "\n");
						left++;
					}
				}else if("size".equals(cmd)) {
					if(left == right) {
						bw.write(0 + "\n");
					}else {
						bw.write(right-left + "\n");
					}
				}else if("empty".equals(cmd)) {
					if(left == right) {
						bw.write(1 + "\n");
					}else {
						bw.write(0 + "\n");
					}
				}else if("front".equals(cmd)) {
					if(left == right) {
						bw.write(-1 + "\n");
					}else {
						bw.write(arr[left] + "\n");
					}
				}else if("back".equals(cmd)) {
					if(left == right) {
						bw.write(-1 + "\n");
					}else {
						bw.write(arr[right-1] + "\n");
					}
				}
			}
			
		}
		bw.close();

	}

}
