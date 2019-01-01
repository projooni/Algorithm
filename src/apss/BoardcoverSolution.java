package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BoardcoverSolution {
	
	public static int W,H;
	public static int coverType[][][] = {
			{ {0,0}, {1,0}, {0,1} },
			{ {0,0}, {0,1}, {1,1} },
			{ {0,0}, {1,0}, {1,1} },
			{ {0,0}, {1,0}, {1,-1} },
			};
	public static int board[][];
	public static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			board = new int[H][W];
			
			for(int i=0; i<H; i++){
				String row = br.readLine().trim();
				for(int j=0; j<W; j++){
					int ch = row.charAt(j);
					if('#' == ch){
						board[i][j] = 1;
					}else{
						board[i][j] = 0;
					}
				}
				
			}
			
			count = cover();
			
			bw.flush();
			bw.write(count + "\n");
			
		}
		
		bw.close();

	}
	
	// board의 (y,x)를 type번 방법으로 덮거나, 덮었언 블록을 없앤다.
	// delta = 1 이면 덮고, -1이면 덮었던 블록을 없앤다.
	// 만약 블록이 제대로 덮이지 않은 경우 (게임판 밖으로 나가거나, 겹치거나, 검은 칸을 덮을 때) false 반환한다.
	public static boolean set(int y, int x, int type, int delta) {
		boolean ok = true;
		for(int i=0; i<3; i++) {
			int ny = y + coverType[type][i][0];
			int nx = x + coverType[type][i][1];
			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
				ok = false;
			}else if((board[ny][nx] += delta) > 1) {
				ok = false;
			}
		}
		
		return ok;
	}
	
	// board의 모든 빈 칸을 덮을 수 있는 방법의 수를 반환한다.
	// board[i][j] = 1 이미 덮인 칸 혹은 검은 칸
	// board[i][j] = 0 아직 덮이지 않은 칸
	public static int cover() {
		// 아직 채우지 못한 칸 중 가장 윗줄 왼쪽에 있는 칸을 찾는다.
		int y = -1, x = -1;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(board[i][j] == 0) {
					y = i;
					x = j;
					break;
				}
			}
			if(y != -1) {
				break;
			}
		}
		
		// 기저 사례 : 모든 칸을 채웠으면 1을 반환한다.
		if(y == -1) return 1;
		int ret = 0;
		for(int type = 0; type < 4; type++) {
			// 만약 board[y][x]를 type 형태로 덮을 수 있으면 재귀 호출한다.
			if(set(y,x,type,1)) {
				ret += cover();
			}
			// 덮었던 블록을 치운다.
			set(y,x,type,-1);
		}
		
		return ret;
		
	}
	

}
