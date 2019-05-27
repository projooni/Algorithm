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

	public static int N, D, P, T;
	public static int target;
	public static int[][] v;
	public static int[] t;
	public static List<Integer> vList[];
	public static double cache[];
	public static boolean visited[];

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

			cache = new double[N];
			visited = new boolean[N];
			v = new int[N][N];

			vList = new ArrayList[N];
			for (int i = 0; i < vList.length; i++) {
				vList[i] = new ArrayList<Integer>();
				cache[i] = -1;
			}

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					v[i][j] = Integer.parseInt(st.nextToken());
					vList[i].add(j);
				}
			}

			T = Integer.parseInt(br.readLine().trim());
			t = new int[T];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < T; i++) {
				t[i] = Integer.parseInt(st.nextToken());
			}

			bw.flush();
			for (int i = 0; i < T; i++) {
				target = t[i];
				double rate = getRate(P);
				bw.write(rate + " ");
			}
			bw.write("\n");

		}
		bw.close();

	}

	// vertex번 마을에서 시작해서 target 마을에도착할 확률
	public static double getRate(int vertex) {

		visited[vertex] = true;
		double rate = 1 / vList[vertex].size();
		
		if(D <= 0) {
			return 1;
		}

		if (vertex == target) {
			return rate;
		}
		
		D--;

		// 인접리스트
		List<Integer> adjList = vList[vertex];

		double ret = 1;
		for (int i = 0; i < adjList.size(); i++) {
			int next = adjList.get(i);
			if (!visited[next]) {
				if (cache[next] == -1) {
					cache[next] = getRate(next);
				}
				ret = rate * cache[next];
			}
		}

		return ret;

	}

}
