package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Test_5day {

	public static int N, Q;
	public static int indexTree[][];
	public static int base;
	public static int ans[];
	public static int sum_max, sum_min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			base = 1 << (int)(Math.log(N-1) / Math.log(2)) + 1;
			indexTree = new int[base*2][3];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= base; i++) {
				if(i<=N) {
					indexTree[base + i - 1][0] = Integer.parseInt(st.nextToken());
					indexTree[base + i - 1][1] = indexTree[base + i - 1][0];
					indexTree[base + i - 1][2] = indexTree[base + i - 1][0];
				}else {
					indexTree[base + i - 1][2] = Integer.MAX_VALUE;
				}
				
			}
			for(int i=base-1; i>=1; i--) {
				indexTree[i][0] = indexTree[i*2][0] + indexTree[i*2+1][0];
				indexTree[i][1] = Math.max(indexTree[i*2][1], indexTree[i*2+1][1]);
				indexTree[i][2] = Math.min(indexTree[i*2][2], indexTree[i*2+1][2]);
			}
			
			sum_max = 0;
			sum_min = 0;
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(q == 0) {
					ans = find(a,b);
					sum_max += ans[0];
					sum_min += ans[1];
				}else {
					// a항을 b로 변경
					update(a,b);
				}
				
			}

			bw.flush();
			bw.write("#" + tc + " " + sum_max + " " + sum_min + "\n");
		}
		bw.close();

	}
	
public static int[] find(int a, int b) {
	
	int ret[] = new int[2];
	
	a = base + a-1;
	b = base + b-1;
	
	int max = 0;
	int min = Integer.MAX_VALUE;
	
	while(a <= b) {
		
		if(a%2 == 0) {
			a /= 2;
		}else {
			max = Math.max(indexTree[a][1], max);
			min = Math.min(indexTree[a][2], min);
			a = a/2 + 1;
		}
		
		if(b%2 == 1) {
			b /= 2;
		}else {
			max = Math.max(indexTree[b][1], max);
			min = Math.min(indexTree[b][2], min);
			b= b/2 -1;
		}
		
	}
	
	ret[0] = max;
	ret[1] = min;
	
	return ret;
}
	
public static void update(int x, int y) {
		
		int idx = base+x-1;
		indexTree[idx][0] = y;
		indexTree[idx][1] = y;
		indexTree[idx][2] = y;
		while(idx > 1) {
			idx /= 2; // 부모이동
			indexTree[idx][0] = indexTree[idx*2][0] + indexTree[idx*2+1][0];
			indexTree[idx][1] = Math.max(indexTree[idx*2][1], indexTree[idx*2+1][1]);
			indexTree[idx][2] = Math.min(indexTree[idx*2][2], indexTree[idx*2+1][2]);
		}
		
	}

}
