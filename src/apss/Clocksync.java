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

public class Clocksync {
	
	public static List<Integer> switchListArr[];
	public static int switchPressCntArr[];
	public static int clockMatrix[];
	public static final int clockSize = 16;
	public static final int switchSize = 10;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		switchListArr = new ArrayList[10];
		for(int i=0; i<switchSize; i++) {
			switchListArr[i] = new ArrayList<Integer>();
		}
		
		switchListArr[0].add(0);
		switchListArr[0].add(1);
		switchListArr[0].add(2);
		
		switchListArr[1].add(3);
		switchListArr[1].add(7);
		switchListArr[1].add(9);
		switchListArr[1].add(11);
		
		switchListArr[2].add(4);
		switchListArr[2].add(10);
		switchListArr[2].add(14);
		switchListArr[2].add(15);
		
		switchListArr[3].add(0);
		switchListArr[3].add(4);
		switchListArr[3].add(5);
		switchListArr[3].add(6);
		switchListArr[3].add(7);
		
		switchListArr[4].add(6);
		switchListArr[4].add(7);
		switchListArr[4].add(8);
		switchListArr[4].add(10);
		switchListArr[4].add(12);
		
		switchListArr[5].add(0);
		switchListArr[5].add(2);
		switchListArr[5].add(14);
		switchListArr[5].add(15);
		
		switchListArr[6].add(3);
		switchListArr[6].add(14);
		switchListArr[6].add(15);
		
		switchListArr[7].add(4);
		switchListArr[7].add(5);
		switchListArr[7].add(7);
		switchListArr[7].add(14);
		switchListArr[7].add(15);
		
		switchListArr[8].add(1);
		switchListArr[8].add(2);
		switchListArr[8].add(3);
		switchListArr[8].add(4);
		switchListArr[8].add(5);
		
		switchListArr[9].add(3);
		switchListArr[9].add(4);
		switchListArr[9].add(5);
		switchListArr[9].add(9);
		switchListArr[9].add(13);
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			
			clockMatrix = new int[clockSize];
			switchPressCntArr = new int[switchSize];
			
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int i=0; i<clockSize; i++) {
				clockMatrix[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = recursive(0);			
			
			bw.flush();
			bw.write((result > 100000000 ? -1 : result) + "\n");
		}		
		bw.close();

	}
	
	public static int recursive(int switchNum) {
		
		// 10번째 스위치까지 왔을때
		if(switchNum == switchSize) {
			
			boolean isAllClockPoint12 = true;
			for(int i=0; i<clockSize; i++) {
				if(clockMatrix[i] != 12) {
					isAllClockPoint12 = false;
				}
			}
			
			// 모두 12시를 가리키고 있다면
			if(isAllClockPoint12) {
				return 0;
			}else {
				return 123456789;
			}
			
		}
		
		
		int ret = Integer.MAX_VALUE;
			for(int cnt=0; cnt<4; cnt++) {
				
				// 스위치를 누른다
				for(int j=0; j<switchListArr[cnt].size(); j++) {
					int clockNum = switchListArr[cnt].get(j);
					clockMatrix[clockNum] = (clockMatrix[clockNum] + 3) == 15 ? 3 : (clockMatrix[clockNum] + 3) ; 
				}
				// 누른 스위치 카운터
				ret = Math.min(ret, recursive(cnt + switchNum+1));
			}		
		
		return ret;
		
		
	}

}
