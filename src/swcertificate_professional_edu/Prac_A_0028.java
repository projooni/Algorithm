package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * ����A-0028 ������
 * 1��° �õ� : 2/5 �ð��ʰ�
 * ���� ����� �۱� ������, ���(����) / ť �ӵ��񱳴� ū �ǹ̴� ����.
 * ���� �õ� : divide �ϰ� return�� �����ذ� ��������
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

