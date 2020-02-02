package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class bj10828 {
	
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
			int pointer = -1;
			bw.flush();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String cmd = st.nextToken();
				
				if("push".equals(cmd)){
					int param = Integer.parseInt(st.nextToken());
					pointer++;
					arr[pointer] = param;
				}else if("pop".equals(cmd)){
					if(pointer == -1) {
						bw.write(-1 + "\n");
					}else {
						bw.write(arr[pointer] + "\n");
						pointer--;
					}
				}else if("size".equals(cmd)) {
					bw.write(pointer+1 + "\n");
				}else if("empty".equals(cmd)) {
					if(pointer == -1) {
						bw.write(1 + "\n");
					}else {
						bw.write(0 + "\n");
					}
				}else if("top".equals(cmd)) {
					if(pointer == -1) {
						bw.write(-1 + "\n");
					}else {
						bw.write(arr[pointer] + "\n");
					}
				}
			}
			
		}
		bw.close();

	}

}
