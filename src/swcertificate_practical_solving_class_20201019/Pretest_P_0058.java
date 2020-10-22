package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 
 *  < ��������Ǯ�̹� (2020.10.19 ~ 2020.10.23) 4���� >
 *  
 * ������    : [����P-0058] ��ũ
 * ���̵�    : �߻�
 * ����       : �ε���Ʈ�� or ���׸�Ʈ Ʈ�� or ���� Ʈ��
 * ���俩��  : O
 * �⿩��    : 80% (���� ������ �� ���ذ�)
 * ��Ÿ
 *   
 * */

public class Pretest_P_0058 {
	
	public static int N;
	public static int arr[][];
	public static long indexTree[];
	public static int base;
	public static int yMax;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N+1][3];
			
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				arr[i][0] = x;
				arr[i][1] = y;
				arr[i][2] = c;
				yMax = Math.max(y, yMax);
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o2[0] - o1[0];
				}
				
			});
			
			base = 1 << (int)(Math.log(yMax-1)/Math.log(2)) + 1;
			indexTree = new long[2*base];
			
			long sum = 0;
			for(int i=0; i<arr.length-1; i++) {
				int y = arr[i][1];
				int c = arr[i][2];
				update(y,c);
				
				long upFromMe = query(0,yMax);
				long downFromMe = query(0,y);
				
				sum += (upFromMe - downFromMe);
			}
			
			bw.flush();
			bw.write("#" + tc + " " + sum +  "\n");
		}
		bw.close();

	}
	
	public static void update(int idx, int value) {
		
		int i = base + idx;
		indexTree[i] = value;
		while(i > 1) {
			i /= 2;
			indexTree[i] = indexTree[i*2] + indexTree[i*2+1];
		}
		
	}
	
	public static long query(int a, int b) {
		
		long sum = 0;
		
		int l = base + a;
		int r = base + b;
		
		while(l < r) {
			
			if(r%2 == 0) {
				sum += indexTree[r];
				r -= 1;
				r /= 2;
			}else {
				r /=2;
			}
			
			if(l%2 == 1) {
				sum += indexTree[l];
				l += 1;
				l /= 2;
			}else {
				l /=2;
			}
		}
		
		sum += indexTree[l];
		
		return sum;
		
		
	}

}
