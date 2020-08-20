package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Pretest_A_0029 {
	
	public static int R,C;
	public static int count;
	public static int matrix[][];
	public static int piece[][][][] = {
			{},
			{{{0,0,1},{1,1,1}},{{1,0},{1,0},{1,1}},{{1,1,1},{1,0,0}},{{1,1},{0,1},{0,1}}},
			{{{0,1,0},{1,1,1}},{{1,0},{1,1},{1,0}},{{1,1,1},{0,1,0}},{{0,1},{1,1},{0,1}}},
			{{{1,1,0},{0,1,1}},{{0,1},{1,1},{1,0}},{{1,1,0},{0,1,1}},{{0,1},{1,1},{1,0}}},
			{{{1,1},{1,1}},{{1,1},{1,1}},{{1,1},{1,1}},{{1,1},{1,1}}},
			{{{0,1,1},{0,1,0},{1,1,0}},{{1,0,0},{1,1,1},{0,0,1}},{{0,1,1},{0,1,0},{1,1,0}},{{1,0,0},{1,1,1},{0,0,1}}},
			{{{0,1,0},{0,1,0},{1,1,1}},{{1,0,0},{1,1,1},{1,0,0}},{{1,1,1},{0,1,0},{0,1,0}},{{0,0,1},{1,1,1},{0,0,1}}},
			{{{1,1,0},{1,1,1}},{{1,1},{1,1},{1,0}},{{1,1,1},{0,1,1}},{{0,1},{1,1},{1,1}}},
			{{{0,0,1},{0,0,1},{1,1,1}},{{1,0,0},{1,0,0},{1,1,1}},{{1,1,1},{1,0,0},{1,0,0}},{{1,1,1},{0,0,1},{0,0,1}}}
	};

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		R = 6;
		C = 4;
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			matrix = new int[R][C];
			
			for(int i=0; i<R; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for(int j=0; j<C; j++) {
					int num = Integer.parseInt(st.nextToken());
					matrix[i][j] = num;
				}
			}
			
			count = 0;
			recursive(0,0);
			
			bw.flush();
			bw.write("#" + tc + "\n");
		}
		bw.close();

	}
	
	public static void recursive(int x, int y) {
		
		for(int i=y; i<R; i++) {
			for(int j=x; j<C; j++) {
				
				// 1번부터 8번까지 조각 순회
				for(int p=1; p<=8; p++) {
					// 4방향 회전 모양 순회
					for(int r=1; r<=4; r++) {
						
						// 조각의 모든 좌표 순회
						for(int h=0; h<piece[p][r].length; h++) {
							for(int k=0; k<piece[p][r][h].length; k++) {
								if( matrix[i+h][j+k] == p ) {
									count++;
								}
							}
						}
						
						//recursive()
						
					}
				}
				
			}
		}
		
	}

}
