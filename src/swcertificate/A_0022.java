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
	public static int password[];
	public static int visited[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase <= T; testCase++){
			
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
			
			
			password = new int[10];
			Arrays.fill(password, -1);
			result = 0;
			// 첫문자가 소문자이거나 숫자일 경우
			visited = new int[2][KEY_CNT];
			result += getPossiblePasswordCount(firstKeyNum, 10, numMinCnt, uppMinCnt);
			// 첫문자가 대문자일 경우
			if(firstKeyNum >= 10) {
				visited = new int[2][KEY_CNT];
				result += getPossiblePasswordCount((firstKeyNum*-1), 10, numMinCnt, uppMinCnt);
			}
			
			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
			
		}
		bw.close();

	}
	
	public static int getPossiblePasswordCount(int keyIdx, int remainingCharCnt, int numMinCnt, int uppMinCnt){
		
		System.out.println(keyIdx);
		
		boolean isUppercase = keyIdx < 0 ? true : false;
		if(isUppercase) {
			keyIdx *= -1;
		}
		
		visited[isUppercase ? 1 : 0][keyIdx]++;
		
		// 남은 자릿수가 0이고, 마지막 키에 도달했으며, 최소 숫자 카운트를 달성하고, 대문자 카운트를 달성한경우
		if(remainingCharCnt <= 1){
			if(keyIdx == lastKeyNum) {
				if(numMinCnt <= 0 && uppMinCnt == 0) {
					return 1;
				}else {
					System.out.println("fail");
					return 0;
				}
			}else {
				System.out.println("fail");
				return 0;
			}
		}
		
		password[10-remainingCharCnt] = keyIdx;
		
		// ���� Ű�� ī��Ʈ�� 2��(�빮��/�ҹ���) ��� ������ ��� ���̻� �Է��� �� �����Ƿ� 0����.
//		if(keyCnt[keyIdx] <= 0) {
//			return 0;
//		}
		
		// Ű ī��Ʈ�� ������Ų��
//		keyCnt[keyIdx]--;
		
		int ret = 0;
		
		// ���� Ű�� ���� Ű ����Ʈ
		List<Integer> adjListOfCurrKey = adjList[keyIdx];
		
		for(int idx=0; idx<adjListOfCurrKey.size(); idx++){
			
			int adjKeyNum = adjListOfCurrKey.get(idx);
			
			
			// ���� ��й�ȣ�� 1���϶�, ������ ��й�ȣ�� ���� Ű ���θ� �˻��Ѵ�. 
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
			
			// dp�� ������ ��͸� ź��.
//			if(dp[adjKeyNum][remainingCharCnt] == -1){
//				dp[adjKeyNum][remainingCharCnt] = getPossiblePasswordCount(
//						adjKeyNum,
//						remainingCharCnt-1,
//						(adjKeyNum < 10 ? (numMinCnt -1) : numMinCnt),
//						(adjKeyNum >= 10 ? (uppMinCnt -1) : uppMinCnt));
//			}
			
//			ret += dp[adjKeyNum][remainingCharCnt];
			boolean isAdjKeyUppercase = adjKeyNum < 0 ? true : false;
			if(visited[isAdjKeyUppercase ? 1 : 0][isAdjKeyUppercase ? (adjKeyNum * -1) : adjKeyNum] == 0) {
				ret += getPossiblePasswordCount(
						adjKeyNum,
						remainingCharCnt-1,
						(adjKeyNum < 10 ? (numMinCnt -1) : numMinCnt),
						(isAdjKeyUppercase ? (uppMinCnt -1) : uppMinCnt));
				visited[isAdjKeyUppercase ? 1 : 0][isAdjKeyUppercase ? (adjKeyNum * -1) : adjKeyNum] = 0;
				
			}
			
			
		}
		
//		keyCnt[keyIdx]++;
		
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
