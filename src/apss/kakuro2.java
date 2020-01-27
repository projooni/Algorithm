package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class kakuro2 {
	
	public static int N;
	public static int Q;
	public static int board[][];
	public static int result[][];
	public static int hint[][];
	public static int targetSum;

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			board = new int[N+1][N+1];
			result = new int[N+1][N+1];
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 0) {
						result[i][j] = 0;
					}
				}
			}

			Q = Integer.parseInt(br.readLine());
			hint = new int[Q][4];
			for(int i=0; i<Q; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<4; j++) {
					hint[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			for(int i=0; i<Q; i++) {
				int y = hint[i][0];
				int x = hint[i][1];
				int direction = hint[i][2];
				targetSum = hint[i][3];
				int rslt = 0;
				// 가로: 0, 세로: 1
				if(direction == 0) {
					rslt = search(y, x+1, direction, 0);
				}else {
//					rslt = search(y+1, x, direction, 0);
				}
				
			}
			
			// 출력.
			bw.flush();
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
						bw.write(result[i][j] + " ");
				}
				bw.write("\n");
			}
			
		}
		bw.close();
		

	}
	
	public static int search(int y, int x, int direction, int sum) {
		
		if(y>8 || x>8 || board[y][x] == 0) {
			if(sum == targetSum) {
				return targetSum;
			}else {
				return -1;
			}
		}
		
		int curr = result[y][x] != 0 ? result[y][x] : 1;
	
		int ret = 0;
		for(int i=curr; i<=9; i++) {
			result[y][x] = i;
			if(direction == 0) {
				ret = search(y, x+1, direction, sum + i);
			}else {
				ret = search(y+1, x, direction, sum + i);
			}
			
			if(ret != -1) {
				break;
			}
			
		}
		
		
		return ret;
		
	}

}
