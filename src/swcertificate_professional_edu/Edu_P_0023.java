package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 교육 P-0023 의미있는 문장 찾기
 * SW 검정 Professional 양성교육 2일차 연습2번문제
 * */
public class Edu_P_0023 {
	
	public static int N, K;
	public static int arr[];
	public static String check;
	public static String allMatch;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<K; i++) {
				check += "0";
				allMatch += "1";
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int start = 0;
			int end = 0;
			while(start != N || end != N) {
				StringBuilder sb = new StringBuilder(check);
				sb.replace(end, end+1, "1");
				
				int checkInt = Integer.parseInt(check, 2);
				String checkBinary = Integer.toBinaryString(checkInt);
				if(allMatch.equals(checkBinary)) {
					sb.replace(start, start+1, "0");
					start++;
				}else {
					end++;
				}
				
			}
			
			bw.flush();
			bw.write("#");
		}
		bw.close();
		
	}

}
