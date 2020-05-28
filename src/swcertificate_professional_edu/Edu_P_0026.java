package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Edu_P_0026 {
	
	public static int N, Q;
	public static int h;
	public static int p[][];
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
			
			depth = new int[N+1];
			h = 17;
			p = new int[h+1][N+1];
			
			depth[1] = 0;
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<N; i++) {
				int parent = Integer.parseInt(st.nextToken());
				p[0][i+1] = parent;
				depth[i+1] = depth[parent]+1;
			}
			
			// 부모 배열 초기화
			for(int i=1; i<=h; i++) {
				for(int v=1; v<=N; v++) {
					p[i][v] = p[i-1][p[i-1][v]];
				}
			}
			
			int ans = 0;
			bw.flush();
			bw.write("#" + tc);
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				ans = lca(a, b);
				bw.write(" " + ans);
			}
			bw.write("\n");
			
		}
		bw.close();

	}
	
	public static int lca(int a, int b) {
		
		// b가 a보다 깊으면 swap
		int tmp = 0;
		if(depth[a] < depth[b]) {
			tmp = a;
			a = b;
			b = tmp;
		}
		
		// a가 b보다 깊으면 a를 b의 깊이에 맞춰주기
		if(depth[a] > depth[b]) {
			for(int k=h; k>=0; k--) {
				if(depth[a] - depth[b] >= (1<<k)) {
					a = p[k][a];
				} 
			}
		}
		
		if(a == b) {
			return a;
		}
		
		// a와 b의 2^k 번째 부모가 같다면 무시 다르면 이동
		for(int k=h; k>=0; k--) {
			if(p[k][a] != p[k][b]) {
				a = p[k][a];
				b = p[k][b];
			}
		}
		
		return p[0][a];
		
	}

}
