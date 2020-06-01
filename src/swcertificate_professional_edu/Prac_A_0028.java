package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 연습A-0028 나누기
 * 1번째 시도 : 2/5 시간초과
 * 문제 사이즈가 작기 때문에, 재귀(스택) / 큐 속도비교는 큰 의미는 없다.
 * 최종 시도 : divide 하고 return을 안해준게 문제였음
 * */
public class Prac_A_0028 {
	
	public static int N;
	public static int arr[][];
	public static int result[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			result = new int[2];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			getRectanlgeCount(0,N,0,N);
			
			bw.flush();
			bw.write("#" + tc + " " + result[0] + " " + result[1] + "\n");
		}
		bw.close();

	}
	
	public static void getRectanlgeCount(int s_x, int e_x, int s_y, int e_y) {
		
		int curr = arr[s_y][s_x];
		boolean isOne = true;
		for(int i=s_y; i<e_y; i++) {
			for(int j=s_x; j<e_x; j++) {
				
				if(arr[i][j] != curr) {
					isOne = false;
					getRectanlgeCount(s_x, s_x + (e_x-s_x)/2, s_y, s_y + (e_y-s_y)/2);
					getRectanlgeCount(s_x + (e_x-s_x)/2, e_x, s_y, s_y + (e_y-s_y)/2);
					getRectanlgeCount(s_x, s_x + (e_x-s_x)/2, s_y + (e_y-s_y)/2, e_y);
					getRectanlgeCount(s_x + (e_x-s_x)/2, e_x, s_y + (e_y-s_y)/2, e_y);
					return;
				}
				
			}
		}
		
		if(isOne) {
			result[curr]++;
		}
		
	}

}

