package swcertificate_professional_edu_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Edu_P_0026 {
	
	public static int N,Q;
	public static int p[][];
	public static int depth[];
	public static int base;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			base = (int)(Math.log(N-1)/Math.log(2))+1;
			p = new int[N+1][base];
			depth = new int[N+1];
			depth[1] = 0;
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<N; i++) {
				int node = Integer.parseInt(st.nextToken());
				p[i+1][0] = node;
				depth[i+1] = depth[node] + 1;
			}
			
			for(int n=1; n<=N; n++) {
				for(int k=1; k<base; k++) {
					// Q : p[p[n][k-1]][k-1] ��� p[p[n][k-1]][0]���� ������ ������ ������ ������?
					// -> ������ ����
					p[n][k] = p[p[n][k-1]][k-1];
				}
			}
			
			bw.flush();
			bw.write("#" + tc);
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int result = lca(a,b);
				bw.write(" " + result);
			}
			bw.write("\n");
			
		}
		bw.close();

	}
	
	public static int lca(int a, int b) {
		
		// swap : ������ b�� a���� �Ʒ���
		if(depth[a] > depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		// ���� �����ֱ�
		if(depth[b] > depth[a]) {
			// Q : �ּ�ó���� �������� �ϸ� �ð��ʰ��� ������ ������?
			// -> ������ ����
			/*
			int k = 0;
			while(depth[b] != depth[a]) {
				b = p[b][k];
			}
			*/
			for(int k=base-1; k>=0; k--) {
				if(depth[b] - depth[a] >= (1<<k)) {
					b = p[b][k];
				}
			}
		}
		
		if(a == b) {
			return a;
		}
		
		// ��Ʈ�������� Ž���ؼ� ������������ ã��
		for(int k=base-1; k>=0; k--) {
			if(p[a][k] != p[b][k]){
				a = p[a][k];
				b = p[b][k];
			}
		}
		
		/*
		int k = base-1;
		while(k >= 0) {
			int A = p[a][k];
			int B = p[b][k];
			
			if(A == B) {
				k--;
			}else {
				
				if(p[a][0] == p[b][0]) {
					break;
				}else {
					a = p[a][k];
					b = p[b][k];
				}
				
			}
			
		}
		*/
		
		return p[a][0];
		
	}

}
