package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Practice1_0011 {
	public static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			bw.flush();
			bw.write("#" + testCase + "\n");
			int max = 0;
			for (int h = 0; h < N; h++) {
				for (int i = 0; i < Math.abs((N / 2) - h); i++) {
					bw.write(" ");
				}
				bw.write("*");
				if (h != N - 1 && h != 0) {
					if (h <= N / 2) {
						for (int j = 0; j < 2 * (h - 1) + 1; j++) {
							bw.write(" ");
							max = j;
						}
					} else {
						for (int k = max - 1; k > 0; k--) {
							bw.write(" ");
						}
						max -= 2;
					}
					bw.write("*");
				}
				bw.write("\n");
			}
		}
		bw.close();
	}
}
