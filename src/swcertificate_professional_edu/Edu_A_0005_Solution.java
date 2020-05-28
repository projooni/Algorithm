package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Edu_A_0005_Solution {
	
	public static int Q;
	public static int D[][];
	public static final int MOD = 1000000007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		D = new int[5001][5001];
		
		D[0][0] = 1;
		D[1][0] = 1;
		D[1][1] = 1;
		for(int i=2; i<=5000; i++) {
			D[i][0] = 1;
			D[i][i] = 1;
			for(int j=1; j<i; j++) {
				D[i][j] = D[i-1][j-1] + D[i-1][j];
				D[i][j] %= 1000000007;
			}
		}
		
		int Q = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=Q; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			bw.flush();
			bw.write("#" + tc + " " + D[n][k] +  "\n");
		}
		bw.close();

	}

}
