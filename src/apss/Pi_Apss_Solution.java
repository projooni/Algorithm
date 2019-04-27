package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
 *PI(원주율 외우기) 알고리즘 문제해결전략 풀이
 * */

public class Pi_Apss_Solution {

	public static String str;
	public static int dp[];
	public static final int max = 123456789;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			str = br.readLine().trim();
			dp = new int[str.length()];
			
			// DP 초기화
			for(int i=0; i<str.length(); i++) {
				dp[i] = -1;
			}

			int result = memorize(0);

			bw.flush();
			bw.write(result + "\n");
		}

		bw.close();
	}
	
	public static int classify(int a, int b) {
		
		// 숫자 조각을 가져온다.
		String M = str.substring(a, b+1);
		// 첫 글자만으로 이루어진 문자열과 같으면 난이도는 1
		String pat1 = "";
		for(int i=0; i<M.length(); i++) {
			pat1 += M.charAt(0);
		}
		if(pat1.equals(M)){
			return 1;
		}
		// 등차수열인지 검사한다.
		boolean progressive = true;
		for(int i=0; i<M.length()-1; i++) {
			if(M.charAt(i+1) - M.charAt(i) != M.charAt(1) - M.charAt(0)) {
				progressive = false;
				break;
			}
		}
		
		// 등차수열이고 공차가 1 혹은 -1이면 난이도는 2
		if(progressive && Math.abs(M.charAt(1) - (int)M.charAt(0)) == 1) {
			return 2;
		}

		// 두 수가 번갈아 등장하는지 확인
		boolean alternating = true;
		for(int i=0; i<M.length(); i++) {
			if(M.charAt(i) != M.charAt(i%2)) {
				alternating = false;
				break;
			}
		}
		
		if(alternating) return 4;
		if(progressive) return 5;
		
		return 10;
		
	}
	
	public static int memorize(int begin) {
		
		// 기저 사례 : 수열의 끝에 도달했을 경우
		if(begin == str.length()) {
			return 0;
		}
		
		// 메모이제이션
		if(dp[begin] != -1) {
			return dp[begin];
		}
		
		int ret = max;
		
		for(int L=3; L<=5;  L++) {
			if(begin + L <= str.length()) {
				ret = Math.min(ret, memorize(begin+L) + classify(begin, begin+L-1));
			}
		}
		
		dp[begin] = ret;
		return ret;
		
	}


}
