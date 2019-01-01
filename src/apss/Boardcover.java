package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boardcover {
	
	public static int W,H;
	public static int blockArr[][][][] = {
			{{{0,0},{0,1},{1,1}},{{0,0},{0,-1},{1,0}},{{0,0},{-1,0},{-1,-1}}},
			{{{0,0},{1,0},{1,1}},{{0,0},{-1,0},{0,1}},{{0,0},{0,-1},{-1,-1}}},
			{{{0,0},{0,1},{-1,1}},{{0,0},{0,-1},{-1,0}},{{0,0},{1,0},{1,-1}}},
			{{{0,0},{1,0},{0,1}},{{0,0},{-1,0},{-1,1}},{{0,0},{0,-1},{1,-1}}}
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
			
			board = new int[W][H];
			
			for(int i=0; i<H; i++){
				String row = br.readLine().trim();
				for(int j=0; j<W; j++){
					int ch = row.charAt(j);
					if('#' == ch){
						board[j][i] = 1;
					}else{
						board[j][i] = 0;
					}
				}
				
			}
			
			
			count = search();
			
			bw.flush();
			bw.write(count + "\n");
			
		}
		
		bw.close();

	}
	
	public static int search(){
		
		int ret = 0;
		
		boolean isFull = true;
		int whiteCnt = 0;
		for(int i=0; i<H; i++){
			for(int j=0; j<W; j++){
				if(board[j][i] == 0){
					isFull = false;
					whiteCnt++;
				}
			}
		}
		
		if(isFull){
			return 1;
		}
		
		// 남은 공간의 수가 3의 배수가 아니면 리턴
		if(whiteCnt%3 != 0){
			return 0;
		}
		
		for(int i=0; i<H; i++){
			for(int j=0; j<W; j++){
				
				if(board[j][i] == 0) {
					for(int k=0; k<4; k++){	// 4가지 종류의 블럭
						for(int h=0; h<3; h++){	// 3가지 다른 중심점
							
							boolean isFail = false;
							int[] xArr = new int[3];
							int[] yArr = new int[3];
							for(int r=0; r<3; r++){	// 중심점을 기준으로한 3가지 점
								int mx = blockArr[k][h][r][0];
								int my = blockArr[k][h][r][1];
								
								int new_x = j+mx;
								int new_y = i+my;
								
								// x축이 게임판을 벗어나는 경우
								if(new_x < 0 || new_x >= W){
									isFail = true;
									break;
								}
								
								// y축이 게임판을 벗어나는 경우
								if(new_y < 0 || new_y >= H){
									isFail = true;
									break;
								}
								
								// 해당 지점이 빈칸이 아닐경우
								if(board[new_x][new_y] != 0) {
									isFail = true;
									break;
								}
								
								board[new_x][new_y]++;
								xArr[r] = new_x;
								yArr[r] = new_y;
								
							}
							
							if(isFail) {
								continue;
							}
							
							ret += search();
							
							// 원복							
							board[xArr[0]][yArr[0]]--;
							board[xArr[1]][yArr[1]]--;
							board[xArr[2]][yArr[2]]--;
							
							
						}
					}
				}
				
				
				
				
				
			}
		}
		
		return ret;
		
		
	}

}
