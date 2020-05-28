package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 연습 P-0019 구간합
 * 유형 : 인덱스 트리(이진트리)
 * */
public class Prac_P_0019 {
	
	public static int N,Q;
	public static int indexTree[];
	public static int base;
	public static final int MOD = 1000000007;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());
			
			// java에서는 log2를 구할 수 없으므로, log10/log10(2)로 계산해 주어야 한다.
			// N-1을 하는 이유는 5~8 범위에서 8만 3으로 떨어지므로 이런 케이스를 보완하기 위함이다.
			// 아래 식을 포함해서 index tree 암기 필요
			base = 1 << (int)(Math.log(N-1) / Math.log(2)) + 1;
			
			indexTree = new int[base*2];
			
			for(int i=1; i<=N; i++) {
				indexTree[base + i -1] = i;
			}
			
			for(int i=base-1; i>=1; i--) {
				indexTree[i] = indexTree[i*2] + indexTree[i*2+1];
			}
			
			long result = 0;
			for(int i=0; i<Q; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y =Integer.parseInt(st.nextToken());
				
				if(q == 0) {
					// 변경
					update(x, y);
				}else {
					// 구간합
					result = (result + sumRange(x, y))%MOD;
				}
				
			}
			
			bw.flush();
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.close();

	}
	
	public static void update(int x, int y) {
		
		int idx = base+x-1;
		indexTree[idx] = y;
		while(idx > 1) {
			idx /= 2; // 부모이동
			indexTree[idx] = (int)((long)(indexTree[idx*2] + indexTree[idx*2+1]) % MOD); 
		}
		
	}
	
	public static long sumRange(int x, int y) {
		x = base + x -1;
		y = base + y -1;
		long sum = 0;
		while(x <= y) {
			
			if(x%2 == 0) {
				x /= 2; // left 인경우 바로 부모로 이동
			}else {
				sum += indexTree[x];
				x = x/2 + 1;
			}
			
			if(y%2 == 1) {
				y /= 2;
			}else {
				sum += indexTree[y];
				y = y/2 -1;
			}
			
			sum = sum % MOD;
			
		}
		
		return sum;
		
	}

}
