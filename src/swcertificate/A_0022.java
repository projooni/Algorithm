package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
	public static List<Integer> adjList[];
	public static int keyCnt[];
	public static int dp[][];
	public static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
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
			int numMinCnt = minute / 10;
			int uppMinCnt = second / 10;
			
			adjList = new ArrayList[KEY_CNT];
			makeAdjList();
			
			keyCnt = new int[KEY_CNT];
			Arrays.fill(keyCnt, 2);
			
			dp = new int[KEY_CNT][9];
			for(int i=0; i<KEY_CNT; i++) {
				for(int j=0; j<9; j++) {
					dp[i][j] = -1;
				}
			}
			
			if(firstKeyNum >= 0 && firstKeyNum < 10) {
				numMinCnt--;
			}
			if(lastKeyNum >= 10 && firstKeyNum < KEY_CNT) {
				uppMinCnt--;
			}
			result = getPossiblePasswordCount(firstKeyNum, 8, numMinCnt, uppMinCnt);
			
			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
			
		}
		bw.close();

	}
	
	public static int getPossiblePasswordCount(int keyIdx, int remainingCharCnt, int numMinCnt, int uppMinCnt){
		
		boolean isUppercase = keyIdx < 0 ? true : false;
		if(isUppercase) {
			keyIdx *= -1;
		}
		
		// 남은 자리수가 없고, 최소 포함되어야할 개수 이상 숫자가 포함되며, 대문자가 포함되어야 할 개수만큼 있을때 1을 리턴. 나머지는 0리턴
		if(remainingCharCnt <= 0){
			if(keyIdx == lastKeyNum) {
				if(numMinCnt <= 0 && uppMinCnt == 0) {
					return 1;
				}else {
					return 0;
				}
			}else {
				return 0;
			}
		}
		
		// 현재 키의 카운트가 2번(대문자/소문자) 모두 소진된 경우 더이상 입력할 수 없으므로 0리턴.
		if(keyCnt[keyIdx] <= 0) {
			return 0;
		}
		
		// 키 카운트를 소진시킨다
		keyCnt[keyIdx]--;
		
		int ret = 0;
		
		// 현재 키의 인접 키 리스트
		List<Integer> adjListOfCurrKey = adjList[keyIdx];
		
		for(int idx=0; idx<adjListOfCurrKey.size(); idx++){
			
			int adjKeyNum = adjListOfCurrKey.get(idx);
			
			// 남은 비밀번호가 1개일때, 마지막 비밀번호의 인접 키 여부를 검사한다. 
//			if(remainingCharCnt == 1){
//				List<Integer> adjListOfLastKey = adjList[lastKeyNum];
//				boolean isAdjLastKey = false;
//				for(int j=0; j<adjListOfLastKey.size(); j++){
//					if(adjKeyNum == adjListOfLastKey.get(j)){
//						isAdjLastKey = true;
//						break;
//					}
//				}
//				if(!isAdjLastKey){
//					continue;
//				}
//			}
			
			// dp에 없으면 재귀를 탄다.
//			if(dp[adjKeyNum][remainingCharCnt] == -1){
//				dp[adjKeyNum][remainingCharCnt] = getPossiblePasswordCount(
//						adjKeyNum,
//						remainingCharCnt-1,
//						(adjKeyNum < 10 ? (numMinCnt -1) : numMinCnt),
//						(adjKeyNum >= 10 ? (uppMinCnt -1) : uppMinCnt));
//			}
			
//			ret += dp[adjKeyNum][remainingCharCnt];
			ret += getPossiblePasswordCount(
					adjKeyNum,
					remainingCharCnt-1,
					(adjKeyNum < 10 ? (numMinCnt -1) : numMinCnt),
					(isUppercase ? (uppMinCnt -1) : uppMinCnt));
			
		}
		
		keyCnt[keyIdx]++;
		
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
				
				if(isInKeyboardExcLine(i+9, lineType, false)){
					adjList[i].add(i+9);
					adjList[i].add((i+9)*-1);
				}
				if(isInKeyboardExcLine(i+10, lineType, false)){
					adjList[i].add(i+10);
					adjList[i].add((i+10)*-1);
				}
				
			}else if(i >= 10 && i < 20){
				
				if(isInKeyboardExcLine(i-10, lineType, false)){
					adjList[i].add(i-10);
				}
				if(isInKeyboardExcLine(i-9, lineType, false)){
					adjList[i].add(i-9);
				}
				
				if(isInKeyboardExcLine(i+9, lineType, false)){
					adjList[i].add(i+9);
					adjList[i].add((i+9)*-1);
				}
				if(isInKeyboardExcLine(i+10, lineType, false)){
					adjList[i].add(i+10);
					adjList[i].add((i+10)*-1);
				}
				
			}else if(i >= 20 && i < 29){
				
				if(isInKeyboardExcLine(i-10, lineType, false)){
					adjList[i].add(i-10);
					adjList[i].add((i-10)*-1);
				}
				if(isInKeyboardExcLine(i-9, lineType, false)){
					adjList[i].add(i-9);
					adjList[i].add((i-9)*-1);
				}
				
				if(isInKeyboardExcLine(i+8, lineType, false)){
					adjList[i].add(i+8);
					adjList[i].add((i+8)*-1);
				}
				if(isInKeyboardExcLine(i+9, lineType, false)){
					adjList[i].add(i+9);
					adjList[i].add((i+9)*-1);
				}
				
			}else{ // keyIdx >= 29
				
				if(isInKeyboardExcLine(i-9, lineType, false)){
					adjList[i].add(i-9);
					adjList[i].add((i-9)*-1);
				}
				if(isInKeyboardExcLine(i-8, lineType, false)){
					adjList[i].add(i-8);
					adjList[i].add((i-8)*-1);
				}
				
			}
			
			if(isInKeyboardExcLine(i-1, lineType, true)){
				adjList[i].add(i-1);
				if(i >= 10) {
					adjList[i].add((i-1)*-1);
				}
			}
			if(isInKeyboardExcLine(i+1, lineType, true)){
				adjList[i].add(i+1);
				if(i >= 10) {
					adjList[i].add((i+1)*-1);
				}
			}
			
		}
		
		
	}
	
	public static boolean isInKeyboardExcLine(int num, int lineType, boolean isOkSameLine){
		boolean ret = false;
		if(num >= 0 && num <= 35){
			if(lineType == 1) {
				if(isOkSameLine){
					if(num >= 0 && num < 10) {
						ret = true;
					}
				}else {
					if(num >= 10 && num < 20) {
						ret = true;
					}
				}
				
			}else if(lineType == 2) {
				if(isOkSameLine){
					if(num >= 10 && num < 20) {
						ret = true;
					}
				}else {
					if(num < 10 || (num >= 20 && num < 29)) {
						ret = true;
					}
				}
				
			}else if(lineType == 3) {
				if(isOkSameLine){
					if(num >= 20 && num < 29) {
						ret = true;
					}
				}else {
					if(num < 20 || num >= 29) {
						ret = true;
					}
				}
				
			}else if(lineType == 4){
				if(isOkSameLine) {
					if(num >= 29 && num < 36) {
						ret = true;
					}
				}else {
					if(num < 29) {
						ret = true;
					}
				}
				
			}else {
				ret = true;
			}
		}
		return ret;
	}

}
