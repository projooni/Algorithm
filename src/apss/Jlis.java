package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 알고리즘 문제해결전략 정답소스 C++ -> JAVA
 * 
 * 풀긴풀었는데, 이해가 잘 안감.
 * -1부터 하는것도 이해가 안가고, JLIS(i, j)로 2중포문을 돌면서 0부터 계산하면 답이 안나오는게 이해가 안됨...
 * 이거만 붙들고 있을순 없으니 일단 패스.
 * */
public class Jlis {
	
	public static int N, M;
	public static int arrN[];
	public static int arrM[];
	public static int cache[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arrN = new int[N];
			arrM = new int[M];
			cache = new int[N+1][M+1];
			
			// dp 초기화
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					cache[i][j] = -1;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				arrN[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++){
				arrM[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = 0;
			
			result =JLIS(-1,-1);
			
			bw.flush();
			bw.write(result-2 + "\n");
			
		}
		
		bw.close();

	}
	
	public static int JLIS(int indexN, int indexM) {
		// 메모이제이션
		if(cache[indexN+1][indexM+1] != -1) {
			return cache[indexN+1][indexM+1];
		}
		
		// A[indexA], B[indexB]가 이미 존재하므로 2개는 항상 있다.
		int ret = 2;
		long a = indexN == -1 ? Long.MIN_VALUE : arrN[indexN];
		long b = indexM == -1 ? Long.MIN_VALUE : arrM[indexM];
		long maxElement = Math.max(a, b);
		
		// 다음 원소를 찾는다.
		for(int nextN = indexN+1; nextN < N; nextN++) {
			if(maxElement < arrN[nextN]) {
				ret = Math.max(ret, JLIS(nextN, indexM) + 1);
			}
		}
		for(int nextM = indexM+1; nextM < M; nextM++) {
			if(maxElement < arrM[nextM]) {
				ret = Math.max(ret, JLIS(indexN, nextM) + 1);
			}
		}
		
		cache[indexN+1][indexM+1] = ret;
		
		return ret;
		
	}

}
