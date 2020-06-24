package swcertificate_professional_edu_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * SW���� Professional �缺���� ����Ǯ�̹�
 * 2����
 * [����P-0004] ����
 * ���� : �Ķ��Ʈ����ġ(N*log1000000), DP(N^2)
 * - ��Ǯ����, �ٽ� ������
 * - �ҽ��� ����� �ڵ�
 * */
public class Pretest_P_0004 {
	
	public static int N,K;
	public static int arr[];
	public static int cost;
	public static int MAX_COST;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				MAX_COST = Math.max(MAX_COST, arr[i]);
			}
			
			// �������� :  �־��� K���� �������� �־��� cost�� ������ �� �ִ°�?
			// �Ǵܿ� ���� �ִ밪�� 1000000�̹Ƿ� ����Ž���� 2^20 = 20������ ���� ã�� �� ����.
			// �����ռ��� �ð����⵵�� N*N���� ����(20*N*N)
			// ���� ������ N
			
			// ��������
			// Cost���� �ް� min, max ���� �����Ͽ� ���ϸ� �� ���� cost���� ũ�ٸ� ������ �߶󳻰�
			// K�� ���� �� �ٽ� ����
			// * ������ �� ���̰� �ϳ��� �Ǿ��ٸ� �� ������ cost���� 0
			// (min = max : max = min = 0)
			
			// �ݺ� �� �־��� K �� �̳� �������� �ߴٰ� �ϸ� ��� �ƴϸ� ����.
			
			//cost = Integer.MAX_VALUE;
			//cut(0,K,0);
			int result =solve(N,K);
			
			
			bw.flush();
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.close();

	}
	
	public static boolean isPossible(int cost, int N, int K) {
		int min, max, count = 0;
		min = max = arr[0];
		
		for(int i=1; i<N; i++) {
			
			if(min>arr[i]) {
				min = arr[i];
			}else if(max < arr[i]){
				max = arr[i];
			}
			
			if(max - min  > cost) {
				min = max = arr[i];
				count++;
				if(count > K) {
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	public static int solve(int N, int K) {
		int start = 0, middle, end = MAX_COST;
		while(start <=end) {
			middle = (start+end) / 2;
			if(isPossible(middle,N,K)) {
				end = middle-1;
			}else {
				start = middle+1;
			}
		}
		return start;
	}
	
	public static void cut(int s, int k, int costMax) {
		
		if(k == 0) {
			int min = Integer.MAX_VALUE;
			int max = 0;
			for(int h=s; h<N; h++) {
				min = Math.min(min, arr[h]);
				max = Math.max(max, arr[h]);
			}
			costMax  = Math.max(costMax, max-min);
			cost = Math.min(costMax, cost);
		}
		
		for(int i=s; i<N; i++) {
			
			for(int j=s; j<N; j++) {
				
				int min = Integer.MAX_VALUE;
				int max = 0;
				for(int h=i; h<j; h++) {
					min = Math.min(min, arr[h]);
					max = Math.max(max, arr[h]);
				}
				
				if(cost < max-min) {
					cut(j+1,k-1,Math.max(costMax, max-min));
				}
				
			}
			
		}
		
	}

}
