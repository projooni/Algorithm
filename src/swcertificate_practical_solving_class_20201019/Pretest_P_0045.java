package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 
 *  < ��������Ǯ�̹� (2020.10.19 ~ 2020.10.23) 4���� >
 *  
 * ������    : [����P-0045] �˹���
 * ���̵�    : �߻�
 * ����       : DFS(������)
 * ���俩�� : O
 * �⿩��    : 50%
 * ��Ÿ
 *   
 * */

public class Pretest_P_0045 {
	
	public static int N,M;
	public static List<Integer> adjList[];
	public static boolean visited[];
	public static int d[];
	public static int inOrder;
	public static long max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 2 <= N <= 100000
			M = Integer.parseInt(st.nextToken()); // 1 <= M <= 500000
			
			visited = new boolean[N+1];
			adjList = new ArrayList[N+1];
			d = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			max = -1;
			inOrder = 0;
			dfs(1, -1);	
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
		}
		bw.close();

	}
	
	public static long dfs(int curr, int parent) {
		
		// in-order �����ϴ� ��� (���� �ϱ�)
		d[curr] = ++inOrder;
		
		// min : �ڽ� ������ ������ �� �ִ� �ּ� in-order ��
		long min = d[curr];
		for(int i=0; i<adjList[curr].size(); i++) {
			int next = adjList[curr].get(i);
			
			if(next == parent) {
				continue;
			}
			
			// in-order ���� ���ٸ� �湮�� ���Ѱ��̹Ƿ� �湮�Ѵ�.
			if(d[next] == 0) {
				long num = dfs(next, curr);
				
				// ������ (�ڽ��� ������ �� �ִ� �ּ� in-order�� ���� in-order���� ũ�� �������̴�)
				if(num > d[curr]) {
					// �������ΰ��, ���� ū in-order���� �ڽ��� in-order�� ���� +1 �ϸ� �ڽ� ���� ���´�.
					long childCnt = inOrder - num + 1;
					max = Math.max(max, (long)(childCnt * (N-childCnt) * 2));
				}
				
				min = Math.min(min, num);
			}else {
				min = Math.min(min, d[next]);
			}
			
		}		
		
		return min;
		
	}

}
