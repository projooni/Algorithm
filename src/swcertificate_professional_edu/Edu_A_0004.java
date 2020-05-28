package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 교육 A-0004 금괴
 * 관련 알고리즘 : 유클리드 호제법
 * 이해 안감..
 * */
public class Edu_A_0004 {

	public static int N,M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 일단 기약분수를 만들어야 한다.
			// 기약분수가 아닌 경우, 이미 잘려있는 부분에 대한 처리가 안된다.
			// 예를들어 2/4의 경우 | | | | |
			int g = gcd(N,M);
			
			// 최종적으로 나누어지는 조각 수
			int cut = M/g;
			
			// 기약분수로 나눴으므로 다시 공배수를 곱해서 원상복구
			// cut-1 : 나누어지는 조각 수 - 1 이 실제 cut 수 이다.
			int ans = g*(cut-1);
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

	}
	
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a%b);
	}

}
