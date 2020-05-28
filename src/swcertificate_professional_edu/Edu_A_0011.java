package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * [����A-0011] ���� ��ȯ
 * ���� : DP
 * ���俩�� : ����
 * ������ Ǯ������ ���� : �ڡڡڡڡ�
 * ��Ÿ : ���� ���̹��� ������� ���� DP ������ ����
 * */
public class Edu_A_0011 {
	
	public static int N, W;
	public static int c[];
	public static int dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			c = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				c[i] = Integer.parseInt(st.nextToken());
			}
			
			W = Integer.parseInt(br.readLine().trim());
			
			dp = new int[W+1];
			
			for(int i=0; i<=W; i++) {
				dp[i] = 64001;
			}
			
			dp[0] = 0;
			for(int w=1; w<=W; w++) {
				for(int i=0; i<N; i++) {
					if(w >= c[i]) {
						dp[w] = Math.min(dp[w-c[i]] + 1, dp[w]);
					}
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + dp[W] + "\n");
		}
		bw.close();

	}

}

/*
 * <����� Ǯ��>
 * coin: 1, 4, 6
 * 8�� (W)
 * 
 * 1*8					8��
 * 1*4 + 4*1			5��
 * 1*2 + 6*1			3��
 * 4*2					2��
 * 
 * W���� �Ž����ִ� �ʿ��� �ּ� ���� ����
 * =  MIN(
 * 		1��¥�� ������ �� ���  ->  W-1���� �Ž����ִµ� �ʿ��� �ּ� ���� ���� + 1
 * 		4��¥�� ������ �� ���  ->  W-4���� �Ž����ִµ� �ʿ��� �ּ� ���� ���� + 1
 * 		6��¥�� ������ �� ���  ->  W-6���� �Ž����ִµ� �ʿ��� �ּ� ���� ���� + 1
 * 		)
 * 
 * 0���� �Ž����ִµ� �ʿ��� �ּ� ���� ���� : 0��
 * 
 * D[i] = i���� �Ž����ִµ� �ʿ��� �ּ� ���� ����
 * 
 * D[0] = 0
 * D[i] = min( D[i-coin[1]] +1, D[i-coin[2]] + 1, D[i-coin[3]] + 1 .. D[i-coin[N]] + 1 )
 * D[i] = min(D[i-coin[j]] + 1)
 * 
 * if i-coin[j] != 0 && D[i-coin[j]] == 0 ? ==> i-coin[j]���� ���� �� ����.
 * �� ������ INF�� �ʱ�ȭ�Ѵٸ� ���ʿ��� ����?
 * */
