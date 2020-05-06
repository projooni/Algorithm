package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class bj6588 {
	
	public static int N;
	public static boolean arr[];
	public static List<Integer> arrList = new ArrayList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 에라토스테네스의 체는 최대 백만까지 한번만 계산하면 된다
		eratosthenes(1000000);
		
		while(true){
			N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			
			String result = solve();
			
			bw.flush();
			bw.write(result);
			
		}
		bw.close();

	}
	
	public static void eratosthenes(int n) {
		
		// 소수는 true, 그외는 false
		arr = new boolean[n+1];
		
		// 0과 1은 소수가 아니다
		arr[0] = false;
		arr[1] = false;
		
		// 2부터 끝자리까지 일단 모두 소수라고 가정한다
		for(int i=2; i<arr.length; i++) {
			arr[i] = true;
		}
		
		// 2부터 마지막 숫자까지 순회하면서 자기 자신을 제외한 배수를 제거한다 (에라토스테네스의 체)
		for(int i=2; i<arr.length; i++) {
			if(!arr[i]) {
				continue;
			}
			for(int j=i+i; j<arr.length; j+=i) {
				arr[j] = false;
			}
		}
		
	}
	
	public static String solve() {
		
		String ret = "Goldbach's conjecture is wrong.\n";
		
		/* 
		 * (1) 2는 유일한 짝수 소수이므로 제외하고 3부터 순회
		 * (2) i + (N-i) = N 이므로, 이 조합만 답이되는지 체크한다. 
		 * (3) 2번의 이유로 N/2까지만 순회하면 된다.
		 */
		for(int i=3; i<=N/2; i++) {
			if(arr[i] && arr[N-i]) {
				ret = N + " = " + i + " + " + (N-i) + "\n";
				break;
			}
		}
		
		return ret;
		
	}

}
