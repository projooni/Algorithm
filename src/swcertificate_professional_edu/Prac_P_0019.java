package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * ���� P-0019 ������
 * ���� : �ε��� Ʈ��(����Ʈ��)
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
			
			// java������ log2�� ���� �� �����Ƿ�, log10/log10(2)�� ����� �־�� �Ѵ�.
			// N-1�� �ϴ� ������ 5~8 �������� 8�� 3���� �������Ƿ� �̷� ���̽��� �����ϱ� �����̴�.
			// �Ʒ� ���� �����ؼ� index tree �ϱ� �ʿ�
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
					// ����
					update(x, y);
				}else {
					// ������
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
			idx /= 2; // �θ��̵�
			indexTree[idx] = (int)((long)(indexTree[idx*2] + indexTree[idx*2+1]) % MOD); 
		}
		
	}
	
	public static long sumRange(int x, int y) {
		x = base + x -1;
		y = base + y -1;
		long sum = 0;
		while(x <= y) {
			
			if(x%2 == 0) {
				x /= 2; // left �ΰ�� �ٷ� �θ�� �̵�
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
