package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 *  < ��������Ǯ�̹� (2020.10.19 ~ 2020.10.23) 5���� >
 *  
 * ������    : [����A-0033] ����Ʈ ���丮
 * ���̵�    : ��
 * ����       : DP
 * ���俩��  : X (Ǫ����)
 * �⿩��    :  0%
 * ��Ÿ			: ��������� �� ���Ҹ���
 *   
 * */

public class Sajeon_A_0033 {
	
	public static int N,M,K;
	public static int engineer[];
	public static boolean used[];
	public static int plan[][];
	public static String checkEngineer;
	public static int dp[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			engineer = new int[K];
			used = new boolean[K];
			String parts = br.readLine().trim();
			
			checkEngineer = "";
			for(int i=0; i<K; i++) {
				int part = parts.charAt(i) - 'A';
				engineer[i] = part;
				checkEngineer += 1;
			}
			
			int checkEngineerInt = Integer.parseInt(checkEngineer);
			dp = new int[N][checkEngineerInt+1][K];
			
			plan = new int[M][N];
			
			String lines = "";
			for(int i=0; i<M; i++) {
				lines = br.readLine().trim();
				
				for(int j=0; j<N; j++) {
					int part = lines.charAt(j) - 'A';
					plan[i][j] = part;
				}
			}
			
			int ans = search(0, checkEngineer, -1);
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

	}
	
	public static int search(int idx, String usedEngineer, int preEngIdx) {
		
		StringBuilder sb = new StringBuilder(usedEngineer);
		int currEngIdx = preEngIdx;
		
		// ���� ���ϴ� �����Ͼ ������ (���������̸�), �ƹ��� �����Ѵ�.
		if(checkEngineer.equals(usedEngineer)) {
			currEngIdx = 0;
			sb.replace(currEngIdx, currEngIdx+1, "0");
		}
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			
			int max = 0;
			
			// �����Ͼ ��ü���� �ʰ� ���Ѵ�.
			int cnt = 0;
			if(dp[idx][Integer.parseInt(usedEngineer)][currEngIdx] == 0) {
				for(int j=0; j<M; j++) {
					if(plan[j][i] == engineer[currEngIdx]) {
						cnt++;
					}
				}
			}else {
				cnt = dp[idx][Integer.parseInt(usedEngineer)][currEngIdx];
			}
			
			max = Math.max(max, cnt);
			
			// ���������� �ƴѰ��
			if(preEngIdx != -1) {
				
				// �����Ͼ ��ü�Ѵ�.
				for(int h=0; h<K; h++) {
					if(sb.toString().charAt(h) == '1') {
						currEngIdx = h;
						sb.replace(currEngIdx, currEngIdx+1, "0");
						
						// �����Ͼ ��ü ��, ���Ѵ�.
						cnt = 0;
						if(dp[idx][Integer.parseInt(usedEngineer)][currEngIdx] != -1) {
							for(int j=0; j<M; j++) {
								if(plan[j][i] == engineer[currEngIdx]) {
									cnt++;
								}
							}
						}else {
							cnt = dp[idx][Integer.parseInt(usedEngineer)][currEngIdx];
						}
						
						max = Math.max(max, cnt);
						
					}
				}
				
			}
			
			sum += max;
			
		}
		
		if(preEngIdx != -1)
			dp[idx][Integer.parseInt(usedEngineer)][preEngIdx] = sum;
		
		return sum;
		
	}

}
