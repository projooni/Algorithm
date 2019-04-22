package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Pi {

	public static String str;
	public static int dp[][];
	public static final int max = 123456789;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for (int testCase = 1; testCase <= T; testCase++) {
			str = br.readLine().trim();

			dp = new int[str.length()][6];
			
			// DP 초기화
			for(int i=0; i<str.length(); i++) {
				dp[i][0] = -1;
				dp[i][1] = -1;
				dp[i][2] = -1;
				dp[i][3] = -1;
				dp[i][4] = -1;
				dp[i][5] = -1;
			}

			int result = getAllMinDifficult(0);

			bw.flush();
			bw.write(testCase + " " + result + "\n");
		}

		bw.close();
	}

	// 3개, 4개, 5개 패턴을 재귀호출한다.
	public static int getAllMinDifficult(int idx) {
		int ret = max;

		ret = Math.min(ret, getMinDifficult(idx, 3));
		ret = Math.min(ret, getMinDifficult(idx, 4));
		ret = Math.min(ret, getMinDifficult(idx, 5));

		return ret;
	}

	public static int getMinDifficult(int idx, int type) {
		
		if(idx == str.length()) {
			return 0;
		}
		// 남은 글자수 부족
		if(idx > str.length()-3) {
			return 10;
		}

		int ret = max;

		for (int i = idx + type; i <= str.length(); i++) {

			char ch1 = str.charAt(idx);
			char ch2 = str.charAt(idx + 1);
			char ch3 = 'n';
			char ch4 = 'n';
			char ch5 = 'n';

			int diff1 = ch2 - ch1;
			int diff2 = -1;
			int diff3 = -1;
			int diff4 = -1;

			if (type >= 3) {
				ch3 = str.charAt(idx + 2);
				diff2 = ch3 - ch2;
			}

			if (type >= 4) {
				ch4 = str.charAt(idx + 3);
				diff3 = ch4 - ch3;
			}

			if (type >= 5) {
				ch5 = str.charAt(idx + 4);
				diff4 = ch5 - ch4;
			}

			if (dp[idx][type] == -1) {
				dp[idx][type] = getAllMinDifficult(idx + type);
			}

			// 3개 패턴
			if (type == 3) {
				if (diff1 == 0 && diff2 == 0) {
					ret = Math.min(ret, 1 + dp[idx][type]);
				} else if ((diff1 == 1 && diff2 == 1) || (diff1 == -1 && diff2 == -1)) {
					ret = Math.min(ret, 2 + dp[idx][type]);
				} else if (diff1 + diff2 == 0) {
					ret = Math.min(ret, 4 + dp[idx][type]);
				} else if (diff1 == diff2) {
					ret = Math.min(ret, 5 + dp[idx][type]);
				} else {
					ret = Math.min(ret, 10 + dp[idx][type]);
				}
			} else if (type == 4) {
				if (diff1 == 0 && diff2 == 0 && diff3 == 0) {
					ret = Math.min(ret, 1 + dp[idx][type]);
				} else if ((diff1 == 1 && diff2 == 1 && diff3 == 1) || (diff1 == -1 && diff2 == -1 && diff3 == -1)) {
					ret = Math.min(ret, 2 + dp[idx][type]);
				} else if ((diff1 + diff2 + diff3) == diff1) {
					ret = Math.min(ret, 4 + dp[idx][type]);
				} else if (diff1 == diff2 && diff2 == diff3) {
					ret = Math.min(ret, 5 + dp[idx][type]);
				} else {
					ret = Math.min(ret, 10 + dp[idx][type]);
				}
			} else if (type == 5) {
				if (diff1 == 0 && diff2 == 0 && diff3 == 0 && diff4 == 0) {
					ret = Math.min(ret, 1 + dp[idx][type]);
				} else if ((diff1 == 1 && diff2 == 1 && diff3 == 1 && diff4 == 1)
						|| (diff1 == -1 && diff2 == -1 && diff3 == -1 && diff4 == -1)) {
					ret = Math.min(ret, 2 + dp[idx][type]);
				} else if (diff1 + diff2 + diff3 + diff4 == 0) {
					ret = Math.min(ret, 4 + dp[idx][type]);
				} else if (diff1 == diff2 && diff2 == diff3 && diff3 == diff4) {
					ret = Math.min(ret, 5 + dp[idx][type]);
				} else {
					ret = Math.min(ret, 10 + dp[idx][type]);
				}
			}

		}

		return ret;

	}

}
