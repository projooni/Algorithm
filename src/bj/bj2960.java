package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class bj2960 {
	
	public static int N, K;
	public static int num[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = 1;
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			num = new int[N+1];
			
			for(int i=0; i<N+1; i++) {
				num[i] = i;
			}
			num[0] = -1;
			num[1] = -1;
			
			int result = 0;
			int cnt = 0;
			int k = 2;
			while(k < N+1) {
				for(int i=k; i<N+1; i+=k) {
					if(num[i] != -1) {
						cnt++;
						if(cnt == K) {
							result = num[i];
							break;
						}
						num[i] = -1;
					}
				}
				if(cnt == K) {
					break;
				}
				k++;
			}
			
			bw.flush();
			bw.write(result + "\n");
		}
		bw.close();

	}

}
