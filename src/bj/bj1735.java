package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class bj1735 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = 1;
		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int a1 = Integer.parseInt(st.nextToken());
			int b1 = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int a2 = Integer.parseInt(st.nextToken());
			int b2 = Integer.parseInt(st.nextToken());

			int a3 = a1 * b2 + a2 * b1;
			int b3 = b1 * b2;

			int gcd = getGCD(a3, b3);

			bw.flush();
			bw.write((a3 / gcd) + " " + (b3 / gcd));
		}
		bw.close();

	}

	public static int getGCD(int a, int b) {
		int r = a % b;
		if (r == 0) {
			return b;
		} else {
			return getGCD(b, r);
		}
	}

}
