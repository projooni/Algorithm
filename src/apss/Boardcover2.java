package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boardcover2 {
	
	public static int W,H,R,C;
	public static int block[][];
	public static int board[][];
	public static List<int[]> rotations[];
	public static int best;
	public static int blockSize;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("/User/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			board = new int[H][W];
			block = new int[R][C];
			rotations[0] = new ArrayList<int[]>();
			rotations[1] = new ArrayList<int[]>();
			rotations[2] = new ArrayList<int[]>();
			rotations[3] = new ArrayList<int[]>();
			
			// board 정보 입력 
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
			
			// block 정보 입력 
			for(int i=0; i<R; i++) {
				String row = br.readLine().trim();
				for(int j=0; j<C; j++) {
					int ch = row.charAt(j);
					if('#' == ch){
						block[i][j] = 1;
					}else{
						block[i][j] = 0;
					}
				}
			}
			
			generateRotations(block);
//			search(0);
			
			bw.flush();
			bw.write(best + "\n");
			
		}
		
		bw.close();

	}
	
	// 이것만으로도 머리터짐
	// arr(block)을 시계방향으로 90도 회전한다. 
	public static int[][] rotate(int[][] arr){
		// 90도 회전 
		int[][] ret = new int[arr[0].length][arr.length];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				ret[j][arr.length-i-1] = arr[i][j];
			}
		}
		return ret;
	}
	
	// block의 네 가지 회전 형태를 만들고 이들을 상대 좌표의 목록으로 변환한다.
	public static void generateRotations(int[][] block) {
		for(int rot=0; rot<4; rot++) {
			int originY = -1;
			int originX = -1;
			for(int i=0; i<block.length; i++) {
				for(int j=0; j<block[i].length; j++) {
					if(block[i][j] == 1) {
						if(originY == -1) {
							//가장 윗줄 맨 왼쪽에 있는 칸이 '원점' 이 된다.
							originY = i;
							originX = j;
						}
						// 각 칸의 위치를 원점으로부터의 상대좌표로 표현한다.
						rotations[rot].add(new int[] {i-originY, j-originX});
					}
					// block을 시계 방향으로 90도 회전한다.
					block = rotate(block);
				}
			}
			// 네 가지 회전 형태 중 중복이 있을 경우 이를 제거한다. (일단 생략)
			
			// 블록이 몇 칸 짜리인지 저장해둔다.
			blockSize = rotations[0].size();
		}
		
	}
	
	
	// board의 (y,x)를 type번 방법으로 덮거나, 덮었언 블록을 없앤다.
	// delta = 1 이면 덮고, -1이면 덮었던 블록을 없앤다.
	// 만약 블록이 제대로 덮이지 않은 경우 (게임판 밖으로 나가거나, 겹치거나, 검은 칸을 덮을 때) false 반환한다.
//	public static boolean set(int y, int x, int type, int delta) {
//		boolean ok = true;
//		for(int i=0; i<3; i++) {
//			int ny = y + coverType[type][i][0];
//			int nx = x + coverType[type][i][1];
//			if(ny < 0 || ny >= H || nx < 0 || nx >= W) {
//				ok = false;
//			}else if((board[ny][nx] += delta) > 1) {
//				ok = false;
//			}
//		}
//		
//		return ok;
//	}
	
	// placed: 지금까지 놓은 블록의 수 
//	public static void search(int placed) {
//		// 아직 채우지 못한 칸 중 가장 윗줄 왼쪽에 있는 칸을 찾는다.
//		int y = -1, x = -1;
//		for(int i=0; i<H; i++) {
//			for(int j=0; j<W; j++) {
//				if(board[i][j] == 0) {
//					y = i;
//					x = j;
//					break;
//				}
//			}
//			if(y != -1) {
//				break;
//			}
//		}
//		
//		// 기저 사례 : 모든 칸을 채웠으면 1을 반환한다.
//		if(y == -1) {
//			best = Math.max(best, placed);
//			return;
//		}
//		
//		int ret = 0;
//		for(int type = 0; type < 4; type++) {
//			// 만약 board[y][x]를 type 형태로 덮을 수 있으면 재귀 호출한다.
//			if(set(y,x,type,1)) {
//				ret += cover();
//			}
//			// 덮었던 블록을 치운다.
//			set(y,x,type,-1);
//		}
//		
//		return ret;
//		
//	}
	

}
