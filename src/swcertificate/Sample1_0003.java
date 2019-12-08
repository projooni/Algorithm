package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 푸는중
// 대탈주
public class Sample1_0003 {
	
	public static int N,M;
	public static int colors[];
	public static int cars[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			colors = new int[M+1];
			cars = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				colors[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				cars[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = recursive(0, true);
			
			bw.flush();
			bw.write("#" + tc + " " + (result == -1 ? 0 : (result+1)) + "\n");
			
		}
		bw.close();
		
		
		

	}
	
	public static int recursive(int idx, boolean isFirst) {
		
		boolean isEnd = true;
		for(int i=0; i<=M; i++) {
			if(colors[i] != 0) {
				isEnd = false;
				break;
			}
		}
		
		if(isEnd) {
			return idx;
		}
		
		for(int i=idx; i<cars.length; i++) {
			int color = cars[i];
			if(colors[color] > 0) {
				colors[color]--;
				if(recursive(i+1, false) >= 0) {
					return i;
				}else {
					if(!isFirst) {
						break;
					}
				}
				colors[color]++;
			}else {
				if(!isFirst) {
					break;
				}
			}
		}
		
		return -1;
		
	}

}
