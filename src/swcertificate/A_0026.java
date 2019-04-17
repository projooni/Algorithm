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
	public static int front_h_pos;
	public static int front_w_pos;
	public static int back_h_pos;
	public static int back_w_pos;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			N = Integer.parseInt(br.readLine().trim());
			P = new int[6][6];
			Car = new int[N+1][3];
			visited = new boolean[N+1];
			
			for(int i=1; i<=N; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int hPos = Integer.parseInt(st.nextToken());
				int wPos = Integer.parseInt(st.nextToken());
				String direction = st.nextToken().toUpperCase();
				
				Car[i][0] = hPos;
				Car[i][1] = wPos;
				
				if("E".equals(direction)) {
					Car[i][2] = 1; // 동: 1, 서: 2, 남: 3, 북: 4
				}else if("W".equals(direction)) {
					Car[i][2] = 2; // 동: 1, 서: 2, 남: 3, 북: 4
				}else if("S".equals(direction)) {
					Car[i][2] = 3; // 동: 1, 서: 2, 남: 3, 북: 4
				}else {
					Car[i][2] = 4; // 동: 1, 서: 2, 남: 3, 북: 4
				}
				
				P[hPos][wPos] = i;
				
			}
			
			int result = getMinMoveCount();
			
			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
			
			
		}
		
		bw.close();
		

	}
	
	// 1번 차량이 목적지에 도착할때까지 자동차들의 최소 이동횟수를 리턴한다.
	public static int getMinMoveCount() {
		
		// 목적지에 도착
		if(Car[1][0] == 3 && Car[1][1] == 5) {
			return 0;			
		}
		
		int min = Integer.MAX_VALUE;
		
		// 모든 자동차를 순회
		for(int i=1; i<N; i++) {
			
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			
			// 현재 자동차 좌표
			int curr_h_pos = Car[i][0];
			int curr_w_pos = Car[i][1];
			int curr_dir = Car[i][2];
			
			// 현재 자동차 좌표 백업
			int org_h_pos = Car[i][0];
			int org_w_pos = Car[i][1];
			
			min = Integer.MAX_VALUE;
			
			// 앞으로 이동한다.
			while((curr_w_pos >= 1 && curr_w_pos <= 5) && (curr_h_pos >= 1 && curr_h_pos <=  5)) {
				
				// 이동할 좌표 갱신
				getMovePos(curr_h_pos, curr_w_pos, curr_dir);			
				
				// 앞이 벽이라 못가면 break
				if(!getPossibleMove(front_h_pos, front_w_pos, curr_dir)) {
					break;
				}				
							
				// 앞이 차로 막혀있고 방문한 자동차가 아니면 recursive
				if(P[front_h_pos][front_w_pos] != 0) {
					min = Math.min(min, getMinMoveCount());
					break;
				}
				
				// 안막혔으면 이동하고 현재 위치를 갱신한다.
				P[front_h_pos][front_w_pos] = 0;
				
				curr_h_pos = front_h_pos;
				curr_w_pos = front_w_pos;
				P[curr_h_pos][curr_w_pos] = i;
			
			}
			
			curr_h_pos = org_h_pos;
			curr_w_pos = org_w_pos;
			getMovePos(curr_h_pos, curr_w_pos, curr_dir);
			
			// 뒤로 이동한다
			while((curr_w_pos >= 1 && curr_w_pos <= 5) && (curr_h_pos >= 1 && curr_h_pos <=  5)) {
				
				// 이동할 좌표 갱신
				getMovePos(curr_h_pos, curr_w_pos, curr_dir);			
				
				// 뒤가 벽이라 못가면 break
				if(!getPossibleMove(back_h_pos, back_w_pos, curr_dir)) {
					break;
				}	
				
				// 뒤가 차로 막혀 있는가?
				if(P[back_h_pos][back_w_pos] != 0) {
					if(!visited[P[back_h_pos][back_w_pos]]) {
						min = Math.min(min, getMinMoveCount());
					}
					break;
				}
				
				// 안막혔으면 이동하고 현재 위치를 갱신한다.
				P[curr_h_pos][curr_w_pos] = 0;
				
				curr_h_pos = back_h_pos;
				curr_w_pos = back_w_pos;
				P[curr_h_pos][curr_w_pos] = i;
				
			}
			
		}
		
		int ret = 0;
		if(min != Integer.MAX_VALUE) {
			ret = min;
		}
		
		return ret;
		
	}
	
	public static void getMovePos(int hPos, int wPos, int direction) {
		
		if(direction == 1) {
			front_h_pos = hPos;
			front_w_pos = wPos + 1;
			back_h_pos = hPos;
			back_w_pos = wPos - 1;
		}else if(direction == 2) {
			front_h_pos = hPos;
			front_w_pos = wPos - 1;
			back_h_pos = hPos;
			back_w_pos = wPos + 1;
		}else if(direction == 3) {
			front_h_pos = hPos + 1;
			front_w_pos = wPos;
			back_h_pos = hPos - 1;
			back_w_pos = wPos;
		}else {
			front_h_pos = hPos - 1;
			front_w_pos = wPos;
			back_h_pos = hPos +1;
			back_w_pos = wPos;
		}
		
	}
	
	public static boolean getPossibleMove(int h_pos, int w_pos, int dir) {
		
		boolean ret = true;
		
		if(dir == 1 || dir == 2) {
			if(w_pos > 5 || w_pos < 1) {
				ret = false;
			}
		}else {
			if(h_pos > 5 || h_pos < 1) {
				ret = false;
			}
		}
		
		return ret;
		
		
	}

}
