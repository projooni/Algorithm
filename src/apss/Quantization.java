package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quantization {
	
	public static int N, S;
	public static int[] A, pSum, pSqSum;
	public static int[][] cache;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			pSum = new int[N];
			pSqSum = new int[N];
			cache = new int[101][11];
			
			for(int i=0; i<101; i++) {
				for(int j=0; j<11; j++) {
					cache[i][j] = -1;
				}
			}
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0; i<N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			precalc();
			int result = quantize(0,S);
			
			bw.flush();
			bw.write(result + "\n");
		}
		bw.close();

	}
	
	// A를 정렬하고 각 부분합을 계산한다.
	public static void precalc() {
		Arrays.sort(A);
		pSum[0] = A[0];
		pSqSum[0] = A[0] * A[0];
		for(int i=1; i<N; i++) {
			pSum[i] = pSum[i-1] + A[i];
			pSqSum[i] = pSqSum[i-1] + (A[i] * A[i]);
		}
	}
	
	// A[lo] ... A[hi] 구간을 하나의 숫자로 표현할 때 최소 오차 합을 계산한다.
	public static int minError(int lo, int hi) {
		// 부분합을 이용해 A[lo] ~ A[hi]까지의 합을 구한다.
		int sum = pSum[hi] - (lo == 0 ? 0 : pSum[lo-1]);
		int sqSum = pSqSum[hi] - (lo == 0 ? 0 : pSqSum[lo-1]);
		
		// 평균을 반올림한 값으로 이 수들을 표현한다.
		int m = (int)(0.5 + (double)sum / (hi - lo + 1));
		// sum(A[i]-m)^2를 전개한 결과를 부분합으로 표현
		int ret = sqSum -2*m*sum + m*m*(hi-lo+1);
		return ret;
	}
	
	public static int quantize(int from, int parts) {
		// 기저 사례: 모든 숫자를 다 양자화했을때
		if(from == N) return 0;
		// 기저 사례: 숫자는 아직 남았는데 더 묶을 수 없을 때 아주 큰 값을 반환한다.
		if(parts == 0) return 123456789;
		
		int ret = cache[from][parts];
		if(ret != -1) {
			return ret;
		}
		ret = 123456789;
		
		// 조각의 길이를 변화시켜 가며 최소치를 찾는다.
		for(int partSize=1; from+partSize<=N; partSize++) {
			ret = Math.min(ret, minError(from, from+partSize-1) + quantize(from + partSize, parts-1));
			cache[from][parts] = ret;
		}
		
		return ret;
	}
	
	

}
