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
		
		// �����佺�׳׽��� ü�� �ִ� �鸸���� �ѹ��� ����ϸ� �ȴ�
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
		
		// �Ҽ��� true, �׿ܴ� false
		arr = new boolean[n+1];
		
		// 0�� 1�� �Ҽ��� �ƴϴ�
		arr[0] = false;
		arr[1] = false;
		
		// 2���� ���ڸ����� �ϴ� ��� �Ҽ���� �����Ѵ�
		for(int i=2; i<arr.length; i++) {
			arr[i] = true;
		}
		
		// 2���� ������ ���ڱ��� ��ȸ�ϸ鼭 �ڱ� �ڽ��� ������ ����� �����Ѵ� (�����佺�׳׽��� ü)
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
		 * (1) 2�� ������ ¦�� �Ҽ��̹Ƿ� �����ϰ� 3���� ��ȸ
		 * (2) i + (N-i) = N �̹Ƿ�, �� ���ո� ���̵Ǵ��� üũ�Ѵ�. 
		 * (3) 2���� ������ N/2������ ��ȸ�ϸ� �ȴ�.
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
