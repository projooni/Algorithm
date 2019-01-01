package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// input case
// 2
// 10/12 02:52:58
// 10/22 02:52:48

public class A_0022 {
	
	public static final int KEY_CNT = 36;
	public static int firstKeyNum;
	public static int lastKeyNum;
	public static int numMinCnt;
	public static int uppMinCnt;
	public static List<Integer> adjList[];
	public static int dp[][];
	public static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase < T; testCase++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String date = st.nextToken();
			String[] splitedDate = date.split("/");
			int month = Integer.parseInt(splitedDate[0]);
			int day = Integer.parseInt(splitedDate[1]);

			String time = st.nextToken();
			String[] splitedTime = time.split(":");
			int hour = Integer.parseInt(splitedTime[0]);
			int minute = Integer.parseInt(splitedTime[1]);
			int second = Integer.parseInt(splitedTime[2]);
			
			firstKeyNum = (day + second) % 36;
			lastKeyNum = (month + hour + minute) % 36;
			numMinCnt = minute / 10;
			uppMinCnt = second / 10;
			
			adjList = new ArrayList[KEY_CNT];
			makeAdjList();
			
			dp = new int[KEY_CNT][9];
			for(int i=0; i<KEY_CNT; i++) {
				for(int j=0; j<9; j++) {
					dp[i][j] = -1;
				}
			}
			result = getPossiblePasswordCount(firstKeyNum, 8);
			
			bw.write("#" + testCase + result);
			
		}

	}
	
	public static int getPossiblePasswordCount(int keyIdx, int remainingCharCnt){
		
		if(remainingCharCnt <= 0){
			return 1;
		}
		
		int ret = 0;
		
		// 현재 Key에 인접한 Key list
		List<Integer> adjListOfCurrKey = adjList[keyIdx];
		
		for(int idx=0; idx<adjListOfCurrKey.size(); idx++){
			
			if(remainingCharCnt == 1){
				// 마지막 10번째 입력 키에 대한 인접 키 리스트
				List<Integer> adjListOfLastKey = adjList[lastKeyNum];
				boolean isAdjLastKey = false;
				for(int j=0; j<adjListOfLastKey.size(); j++){
					if(idx == adjListOfLastKey.get(j)){
						isAdjLastKey = true;
						break;
					}
				}
				if(!isAdjLastKey){
					continue;
				}
			}
			
			// �쁽�옱 idx�뿉 ���븳 �씤�젒 key 踰덊샇
			int adjKeyNum = adjListOfCurrKey.get(idx);
			
			if(adjKeyNum < 10){
				// �닽�옄�씤媛�?
				numMinCnt--;
			}else{
				// 臾몄옄�씤媛�?
				uppMinCnt--;
			}
			
			if(dp[adjKeyNum][remainingCharCnt] == -1){
				dp[adjKeyNum][remainingCharCnt] = getPossiblePasswordCount(idx, remainingCharCnt-1);
			}
			
			ret += dp[adjKeyNum][remainingCharCnt];
			
		}
		
		return ret;
		
	}
	
	public static void makeAdjList(){
		
		for(int i=0; i<KEY_CNT; i++){
			adjList[i] = new ArrayList<Integer>();
			
			int lineType = 0;
			if(i >= 0 && i < 10) {
				lineType = 1;	// 1 Line
			}else if(i >= 10 && i < 20) {
				lineType = 2;	// 2 Line
			}else if(i >= 20 && i < 29) {
				lineType = 3;
			}else {
				lineType = 4;
			}			
			
			if(i < 10){
				
				if(isInKeyboardExcLine(i-10, lineType)){
					adjList[i].add(i-10);
				}
				if(isInKeyboardExcLine(i-9, lineType)){
					adjList[i].add(i-9);
				}
				
				if(isInKeyboardExcLine(i+9, lineType)){
					adjList[i].add(i+9);
				}
				if(isInKeyboardExcLine(i+10, lineType)){
					adjList[i].add(i+10);
				}
				
			}else if(i >= 10 && i < 20){
				
				if(isInKeyboardExcLine(i-10, lineType)){
					adjList[i].add(i-10);
				}
				if(isInKeyboardExcLine(i-9, lineType)){
					adjList[i].add(i-9);
				}
				
				if(isInKeyboardExcLine(i+9, lineType)){
					adjList[i].add(i+9);
				}
				if(isInKeyboardExcLine(i+10, lineType)){
					adjList[i].add(i+10);
				}
				
			} if(i >= 20 && i < 29){
				
				if(isInKeyboardExcLine(i-10, lineType)){
					adjList[i].add(i-10);
				}
				if(isInKeyboardExcLine(i-9, lineType)){
					adjList[i].add(i-9);
				}
				
				if(isInKeyboardExcLine(i+8, lineType)){
					adjList[i].add(i+8);
				}
				if(isInKeyboardExcLine(i+9, lineType)){
					adjList[i].add(i+9);
				}
				
			}else{ // keyIdx >= 29
				
				if(isInKeyboardExcLine(i-9, lineType)){
					adjList[i].add(i-9);
				}
				if(isInKeyboardExcLine(i-8, lineType)){
					adjList[i].add(i-8);
				}
				
			}
			
			lineType = 0;
			
			if(isInKeyboardExcLine(i-1, lineType)){
				adjList[i].add(i-1);
			}
			if(isInKeyboardExcLine(i+1, lineType)){
				adjList[i].add(i+1);
			}
			
			adjList[i].add(i-9);
		}
		
		
	}
	
	// 현재 key가 속한 라인이 아니면서 키 배열내에 있는지 여부를 리턴한다
	public static boolean isInKeyboardExcLine(int num, int lineType){
		boolean ret = false;
		if(num >= 0 && num <= 35){
			if(lineType == 1) {
				if(num >= 10) {
					ret = true;
				}
			}else if(lineType == 2) {
				if(num < 10 || num >= 20) {
					ret = true;
				}
			}else if(lineType == 3) {
				if(num < 20 || num >= 29) {
					ret = true;
				}
			}else if(lineType == 4){
				if(num < 29) {
					ret = true;
				}
			}else {
				ret = true;
			}
		}
		return ret;
	}

}
