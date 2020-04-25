package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class bj3955 {
	
	public static int K,C;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			int gcd = getGCD(K, C);
			
			bw.flush();
			if(gcd != 1) {
				bw.write("IMPOSIBLE");
			}else {
				
			}
			
		}
		bw.close();
	}
	
	// ¾Ï±â
	public static int getGCD(int p, int q) {
		if(q == 0) {
			return p;
		}
		return getGCD(q, p%q);
	}

}
