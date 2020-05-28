package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * [기출P-0051] 조상이 키컸으면
 * 테스트케이스 2/30 : 아직 못풀었음
 * */

public class Pretest_P_0051 {
	
	public static int N,Q,K,H;
	public static int p[][];
	public static int h[];
	public static int depth[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			H = 14;
			p = new int[H+1][N+1];
			h = new int[N+1];
			depth = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int parent = Integer.parseInt(st.nextToken());
				int height = Integer.parseInt(st.nextToken());
				p[0][i] = parent;
				h[i] = height;
				depth[i] = depth[parent]+1;
			}
			
			// 부모 배열 초기화
			for(int k=1; k<=H; k++) {
				for(int i=1; i<=N; i++) {
					p[k][i] = p[k-1][p[k-1][i]];
				}
			}
			
			bw.flush();
			bw.write("#" + tc);
			for(int i=1; i<=Q; i++) {
				
				st = new StringTokenizer(br.readLine().trim());
				
				K = Integer.parseInt(st.nextToken());
				
				int num1 = Integer.parseInt(st.nextToken());
				for(int j=2; j<=K; j++) {
					int num2 = Integer.parseInt(st.nextToken());
					num1 = lca(num1, num2);
				}
				
				// TODO 미리 구해놓을 수 있음(DFS)
				int max = h[num1];
				while(p[0][num1] != 0) {
					max = Math.max(max, h[p[0][num1]]);
					num1 = p[0][num1];
				}
				
				bw.write(" " + max);
				
			}
			bw.write("\n");
			
		}
		
		bw.close();
		
	}
	
	public static int lca(int a, int b) {
		
		int tmp = 0;
		if(depth[a] > depth[b]) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		if(depth[a] < depth[b]) {
			for(int k=H; k>=0; k--) {
				if(depth[b] - depth[a] == (1<<k)) {
					b = p[k][b];
				}
			}
		}
		
		if(a == b) {
			return a;
		}
		
		for(int k=H; k>=0; k--) {
			if(p[k][a] != p[k][b]) {
				a = p[k][a];
				b = p[k][b];
			}
		}
		
		return a;
		
	}

}
