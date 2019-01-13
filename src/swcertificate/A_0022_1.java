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

public class A_0022_1 {
	
	public static final int KEY_CNT = 36;
	public static int firstKeyNum;
	public static int lastKeyNum;
	public static List<Integer> adjList[];
	public static long result;
	public static int visited[];
	public static int numMinCnt;
	public static int uppCnt;
	public static int passwordLength;
	public static int uppFirstChar;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
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
			numMinCnt = minute / 10;
			uppCnt = second / 10;
			
			passwordLength = 10;
			
			adjList = new ArrayList[KEY_CNT];
			makeAdjList();
			
			boolean isFirstKeyNum = true;
			if(firstKeyNum >= 0 && firstKeyNum < 10) {
				numMinCnt--;
			}else {
				isFirstKeyNum = false;
			}
			
			result = 0;
			visited = new int[KEY_CNT];
			result += getPossiblePasswordCount(firstKeyNum, 10, isFirstKeyNum ? 1 : 0, !isFirstKeyNum ? 1 : 0, 0);
			
			bw.flush();
			bw.write("#" + testCase + " " + result + "\n");
			
		}
		bw.close();

	}
	
	public static int combination(int n, int r) {
		if(n == r || r == 0) return 1; 
		else return combination(n - 1, r - 1) + combination(n - 1, r); 
	}
	
	public static long getPossiblePasswordCount(int keyIdx, int remainingCharCnt, int numCnt, int charCnt, int dupCnt){
		
		visited[keyIdx]++;
		
		if(uppCnt > (passwordLength - numCnt)) {
			return 0;
		}
		
		if(numMinCnt > (passwordLength - charCnt)) {
			return 0;
		}
		
		if(remainingCharCnt <= 1){
			
			if(keyIdx == lastKeyNum) {
				
				if(numCnt >= numMinCnt && charCnt >= uppCnt) {
					int resultCombi = combination( charCnt-(dupCnt*2), uppCnt-dupCnt);
					int dupCharCombi = (int) Math.pow(2, dupCnt);
					return dupCharCombi*resultCombi;
				}else {
					return 0;
				}
				
			}else {
				return 0;
			}
			
		}
		
		long ret = 0;
		
		List<Integer> adjListOfCurrKey = adjList[keyIdx];
		
		for(int idx=0; idx<adjListOfCurrKey.size(); idx++){
			
			int adjKeyNum = adjListOfCurrKey.get(idx);
			boolean isNum = adjKeyNum < 10 ? true : false;

			if((isNum &&visited[adjKeyNum] == 0) || (!isNum && visited[adjKeyNum] <= 1)) {
					
				ret += getPossiblePasswordCount(
						adjKeyNum,
						remainingCharCnt-1,
						(isNum ? (numCnt+1) : numCnt),
						(isNum ? charCnt : (charCnt+1)),
						visited[adjKeyNum]==1 ? dupCnt+1 : dupCnt);
				
				visited[adjKeyNum] --;
				
			}
			
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
				
				if(isInKeyboardExcLine(i+9, lineType, false)){
					adjList[i].add(i+9);
				}
				if(isInKeyboardExcLine(i+10, lineType, false)){
					adjList[i].add(i+10);
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
				}
				if(isInKeyboardExcLine(i+10, lineType, false)){
					adjList[i].add(i+10);
				}
				
			}else if(i >= 20 && i < 29){
				
				if(isInKeyboardExcLine(i-10, lineType, false)){
					adjList[i].add(i-10);
				}
				if(isInKeyboardExcLine(i-9, lineType, false)){
					adjList[i].add(i-9);
				}
				
				if(isInKeyboardExcLine(i+8, lineType, false)){
					adjList[i].add(i+8);
				}
				if(isInKeyboardExcLine(i+9, lineType, false)){
					adjList[i].add(i+9);
				}
				
			}else{ // keyIdx >= 29
				
				if(isInKeyboardExcLine(i-9, lineType, false)){
					adjList[i].add(i-9);
				}
				if(isInKeyboardExcLine(i-8, lineType, false)){
					adjList[i].add(i-8);
				}
				
			}
			
			if(isInKeyboardExcLine(i-1, lineType, true)){
				adjList[i].add(i-1);
			}
			if(isInKeyboardExcLine(i+1, lineType, true)){
				adjList[i].add(i+1);
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
