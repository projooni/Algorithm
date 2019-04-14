package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class A_0026 {
	
	public static class NextPos {
			int f_hPos;
			int f_wPos;
			int b_hPos;
			int b_wPos;
	}
	
	// 자동차 
	public static int N;
	public static int P[][];
	public static int Car[][];
	public static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			N = Integer.parseInt(br.readLine().trim());
			P = new int[N+1][N+1];
			Car = new int[N+1][3];
			visited = new boolean[N+1];
			
			for(int i=1; i<=N; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int hPos = Integer.parseInt(st.nextToken());
				int wPos = Integer.parseInt(st.nextToken());
				String dir = st.nextToken().toUpperCase();
				
				Car[i][0] = hPos;
				Car[i][1] = wPos;
				
				if("E".equals(dir)) {
					Car[i][2] = 1; // 동: 1, 서: 2, 남: 3, 북: 4
				}else if("W".equals(dir)) {
					Car[i][2] = 2; // 동: 1, 서: 2, 남: 3, 북: 4
				}else if("S".equals(dir)) {
					Car[i][2] = 3; // 동: 1, 서: 2, 남: 3, 북: 4
				}else {
					Car[i][2] = 4; // 동: 1, 서: 2, 남: 3, 북: 4
				}
				
				P[hPos][wPos] = i;
				
			}
			
			int result = getMinMoveCount(1, true);
			
			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
			
			
		}
		
		bw.close();
		

	}
	
	// 1번 차량이 목적지에 도착할때까지 자동차들의 최소 이동횟수를 리턴한다.
	public static int getMinMoveCount(int carNum, boolean isFront) {
		
		// 1번 차량이 목적지에 도착했는가?
		if(P[3][5] == 1) {
			return 1;
		}
		
		int hPos = Car[carNum][0];
		int wPos = Car[carNum][1];
		int direction = Car[carNum][2];
		
		NextPos nextPos = getMovePos(hPos, wPos, direction);
		
		int f_hPos = nextPos .f_hPos;
		int f_wPos = nextPos.f_wPos;
		int b_hPos = nextPos.b_hPos;
		int b_wPos = nextPos.b_wPos;
		
		if(isFront && (f_hPos > 5 || f_hPos < 1)  || (f_wPos > 5 || f_wPos < 1) ) {
			return 0;
		}
		
		if(!isFront && (b_hPos > 5 || b_hPos < 1)  || (b_wPos > 5 || b_wPos < 1) ) {
			return 0;
		}
		
		int ret = Integer.MAX_VALUE;
	
		// 앞으로 이동한다.
		while(wPos > 0 && wPos < 6) {
			
			// 앞이 차로 막혀있는가?
			if(P[f_hPos][f_wPos] != 0) {
				ret = Math.min(ret, 1 + getMinMoveCount(P[f_hPos][f_wPos], true));
				continue;
			}
			
			// 안막혔으면 이동하고 현재 위치를 갱신한다.
			P[hPos][wPos] = 0;
			
			hPos = f_hPos;
			wPos = f_wPos;
			P[hPos][wPos] = carNum;
		
			nextPos = getMovePos(hPos, wPos, direction);
			f_hPos = nextPos .f_hPos;
			f_wPos = nextPos.f_wPos;
		
		}
		
		// 뒤로 이동한다
		while(wPos > 0 && wPos < 6) {
			
			// 뒤가 차로 막혀 있는가?
			if(P[b_hPos][b_wPos] != 0) {
				ret = Math.min(ret, 1 + getMinMoveCount(P[b_hPos][b_wPos], false));
				continue;
			}
			
			// 안막혔으면 이동하고 현재 위치를 갱신한다.
			P[hPos][wPos] = 0;
			hPos = b_hPos;
			wPos = b_wPos;
			P[hPos][wPos] = carNum;
		
			nextPos = getMovePos(hPos, wPos, direction);
			b_hPos = nextPos.b_hPos;
			b_wPos = nextPos.b_wPos;
			
		
		}
		
		return ret;
		
	}
	
	public static NextPos getMovePos(int hPos, int wPos, int direction) {
		
		int f_hPos, f_wPos, b_hPos, b_wPos;
		
		if(direction == 1) {
			f_wPos = wPos + 1;
			f_hPos = hPos;
			b_wPos = wPos - 1;
			b_hPos = hPos;
		}else if(direction == 2) {
			f_wPos = wPos - 1;
			f_hPos = hPos;
			b_wPos = wPos + 1;
			b_hPos = hPos;
		}else if(direction == 3) {
			f_wPos = wPos;
			f_hPos = hPos + 1;
			b_wPos = wPos;
			b_hPos = hPos - 1;
		}else {
			f_wPos = wPos;
			f_hPos = hPos - 1;
			b_wPos = wPos;
			b_hPos = hPos +1;
		}
		
		NextPos nextPos = new NextPos();
		nextPos.f_hPos = f_hPos;
		nextPos.f_wPos = f_wPos;
		nextPos.b_hPos = b_hPos;
		nextPos.b_wPos = b_wPos;
		
		return nextPos;
		
	}

}
