package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Interstellar {
	
	public static int time;
	public static int X; // 가로
	public static int Y; // 세로
	public static int M; // 운석 개수
	public static int W; // 워프 개수
	public static Meteor[] meteorArr;
	public static Warp[] warpArr;
	public static int[][] warpLocArr;
	public static int[][] meteorLocArr;
	public static boolean isMininf;
	
	public static int[][] dp;
	
	public static class Meteor{
		public int x;
		public int y;
		public Meteor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Warp{
		public int xs;
		public int ys;
		public int xe;
		public int ye;
		public int tw;
		public boolean isVisited;
		public Warp(int xs, int ys, int xe, int ye, int tw, boolean isVisited) {
			this.xs = xs;
			this.ys = ys;
			this.xe = xe;
			this.ye = ye;
			this.tw = tw;
			this.isVisited = isVisited;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
				System.setIn(new FileInputStream("D:\\sample_input.txt"));
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
				int T = Integer.parseInt(br.readLine());
				
				for(int testCase=1; testCase<=T; testCase++){
					
					StringTokenizer st = new StringTokenizer(br.readLine());
					
					X = Integer.parseInt(st.nextToken());
					Y = Integer.parseInt(st.nextToken());
					
					M = Integer.parseInt(br.readLine());
					
					// 운석 세팅!
					meteorArr = new Meteor[M];
					meteorLocArr = new int[X][Y];
					for(int i=0; i<M; i++){
						st = new StringTokenizer(br.readLine());
						// 운석 배열의 항목 각각을 초기화
						meteorArr[i] = new Meteor(
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken())
								);
						meteorLocArr[meteorArr[i].x][meteorArr[i].y] = -1;
					}
					
					// 워프 세팅!
					W = Integer.parseInt(br.readLine());
					warpArr = new Warp[W];
					warpLocArr = new int[X][Y];
					for(int i=0; i<X; i++){
						for(int j=0; j<Y; j++){
							warpLocArr[i][j] = -1;
						}
					}
					for(int i=0; i<W; i++){
						st = new StringTokenizer(br.readLine());
						// 워프 배열의 항목 각각을 초기화
						warpArr[i] = new Warp(
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								Integer.parseInt(st.nextToken()),
								false
								);
						warpLocArr[warpArr[i].xs][warpArr[i].ys] = i;
						warpLocArr[warpArr[i].xe][warpArr[i].ye] = i;
					}
					
					dp = new int[X][Y];
					for(int i=0; i<X; i++){
						for(int j=0; j<Y; j++){
							dp[i][j] = -1;
						}
					}
					
					dp[0][0] = 0;
//					dp[0][1] = 1;
//					dp[1][0] = 1;
					isMininf = false;
					int rtVal = doProcess(0,0,-1);
					if(rtVal == -2){
						bw.write("#" + testCase + " mininf\n");
					}else{
						bw.write("#" + testCase + " " + (dp[X-1][Y-1] == -1 ? "noway" : dp[X-1][Y-1]) + "\n");
					}
					
					bw.flush();
					
				}
				
				bw.close();

	}
	
	public static int doProcess(int currX, int currY, int isFromTop){
		
		int rtVal = -1;
		
		if(currX >= X){ //격자의 가로 범위를 벗어났을때 끝
			return rtVal;
		}
		if(currY >= Y){ //격자의 세로 범위를 벗어났을때 끝
			return rtVal;
		}
		
		if(currX == (X-1) && currY == (Y-1)){ // 목적지에 도달한 경우
			
			if(isFromTop == 1){	// 위에서 접근한 경우
				
				if(dp[currX][currY] == -1){	// 목적지의 dp값이 없는 경우 새로 갱신한다.
					
					dp[currX][currY] = dp[currX][currY-1]+1;
					
				}else{	// 목적지의 dp값이 있는 경우 크기 비교 후 최소 값으로 갱신한다.
					
					dp[currX][currY] = dp[currX][currY] != 0 && dp[currX][currY] > (dp[currX][currY-1]+1) ? dp[currX][currY-1]+1 : dp[currX][currY];
					
				}
				
			}else{	// 왼쪽에서 접근한 경우
				
				if(dp[currX][currY] == -1){ // 목적지의 dp값이 없는 경우 새로 갱신한다.
					
					dp[currX][currY] = dp[currX-1][currY]+1;
					
				}else{ // 목적지의 dp값이 있는 경우 크기 비교 후 최소 값으로 갱신한다.
					
					dp[currX][currY] = dp[currX][currY] != 0 && dp[currX][currY] > (dp[currX-1][currY]+1) ? dp[currX-1][currY]+1 : dp[currX][currY];
					
				}
				
			}
			return rtVal;
		}
		
		if(meteorLocArr[currX][currY] == -1){	// 현재 위치가 운석이라면 그냥 리턴
			return rtVal;
		}
		
		if(dp[currX][currY] == -1){	// dp에 없으면 dp값 갱신
			int fromTop = Integer.MAX_VALUE;
			int fromLeft = Integer.MAX_VALUE;
			if(currX-1 >= 0){
				fromLeft = dp[currX-1][currY] + 1;
			}
			if(currY-1 >= 0){
				fromTop = dp[currX][currY-1] + 1;
			}
			dp[currX][currY] = fromTop <= fromLeft ? fromTop : fromLeft;
		}
		
			
		if(warpLocArr[currX][currY] != -1){	// 현재 위치가 워프라면..
			
			int idxWarp = warpLocArr[currX][currY];
			Warp warp = warpArr[idxWarp];
			
			if(warp.isVisited){ // 이미 방문한 워프라면
				if(warp.tw < 0 && (Math.abs(warp.tw) > (Math.abs(warp.xs-warp.xe)+Math.abs(warp.ys-warp.ye)))){ // 시간은 무한히 거꾸로 돌아간다
					return -2;
				}else{	// 시간은 영원히 흘러간다
					return -1;
				}
			}
			
			warp.isVisited = true;
			
			if(warp.xs == warp.xe && warp.ys == warp.ye){ // 워프의 무한반복이라면 (시작지점과 도착지점이 같다면)
				
				if(warp.tw < 0){ // 시간은 무한히 거꾸로 돌아간다
					return -2;
				}else{	// 시간은 영원히 흘러간다
					return -1;
				}
				
			}else{
				// 워프 도착지점으로 이동해서 탐색을 계속한다.
				if(dp[warp.xe][warp.ye] == -1){
					dp[warp.xe][warp.ye] = dp[warp.xs][warp.ys] + warp.tw;
				}else{
					if(dp[warp.xe][warp.ye] > (dp[warp.xs][warp.ys] + warp.tw)){
						dp[warp.xe][warp.ye] = dp[warp.xs][warp.ys] + warp.tw;	// 워프 시간을 더해줌
					}
				}
				
				
				if((warp.xe+1 < X) && dp[warp.xe+1][warp.ye] == -1){
					if(doProcess(warp.xe+1, warp.ye, 0) == -2){
						isMininf = true;
					}
				}
				if((warp.ye+1) < Y && dp[warp.xe][warp.ye+1] == -1){
					if(doProcess(warp.xe, warp.ye+1, 1) == -2){
						isMininf = true;
					}
				}
				
				return isMininf == true ? -2 : rtVal;
			}
			
			
			
		}
		
		// 탐색 계속..
		if((currX+1) < X && dp[currX+1][currY] == -1){
			if(doProcess(currX+1, currY, 0) == -2){
				isMininf = true;
			}
		}
		if((currY+1) < Y && dp[currX][currY+1] == -1){
			if(doProcess(currX, currY+1, 1) == -2){
				isMininf = true;
			}
		}
		
		return isMininf == true ? -2 : rtVal;
		
	}

}
