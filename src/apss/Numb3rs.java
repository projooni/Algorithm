package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Numb3rs {

	public static int N, D, P, T, D_COPY;
	public static int target;
	public static int[][] v;
	public static int[] t;
	public static List<Integer> vList[];
	public static double cache[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int TT = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TT; testCase++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());

			cache = new double[N][101];
			for(int i=0; i<N; i++) {
				for(int j=0; j<101; j++) {
					cache[i][j] = -1;
				}
			}
			
			v = new int[N][N];

			vList = new ArrayList[N];
			for (int i = 0; i < vList.length; i++) {
				vList[i] = new ArrayList<Integer>();
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					v[i][j] = Integer.parseInt(st.nextToken());
					if(v[i][j] == 1) {
						vList[i].add(j);
					}
				}
				
			}

			T = Integer.parseInt(br.readLine().trim());
			t = new int[T];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < T; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}

			bw.flush();
			
//			getRate(P, 0);
			for (int i = 0; i < T; i++) {
				target = t[i];
				for(int k=0; k<N; k++) {
					for(int j=0; j<101; j++) {
						cache[k][j] = -1;
					}
				}
				double rate = getRate(P, 0);
				bw.write(String.format("%.8f", rate) + " ");
			}
			bw.write("\n");

		}
		bw.close();

	}

	// vertex번 마을에서 시작해서 target 마을에도착할 확률
	public static double getRate(int here, int days) {
		
		if(days == D) {
			if(target == here) {
				return 1.0;
			}
			return 0.0;
		}
		
		if (cache[here][days] > -0.5) {
			return cache[here][days];
		}

		// 인접리스트
		List<Integer> adjList = vList[here];

		double ret = 0.0;
		for (int i = 0; i < adjList.size(); i++) {
			int next = adjList.get(i);
			double result = getRate(next, days+1) / adjList.size();
			ret += result;
		}
		
		cache[here][days] = ret;

		return ret;

	}

}
