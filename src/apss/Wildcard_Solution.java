package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wildcard_Solution {
	
	public static String W, S;
	public static int N;
	public static int cache[][];
	public static String fileName[];
	public static List<String> result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://eclipse-workspace/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			W = br.readLine().trim();
			N = Integer.parseInt(br.readLine().trim());
			
			result = new ArrayList<String>();
			
			fileName = new String[N];
			for(int i=0; i<N; i++) {
				fileName[i] = br.readLine().trim();
				S = fileName[i];
				
				cache = new int[101][101];
				for(int k=0; k<101; k++) {
					for(int j=0; j<101; j++) {
						cache[k][j] = -1;
					}
				}
				
				bw.flush();
				
				if(dynamicMatch(0,0) == 1) {
					result.add(fileName[i]);
				}
				
			}
			
			Collections.sort(result);
			
			for(int i=0; i<result.size(); i++) {
				bw.write(result.get(i) + "\n");
			}
			
		}
		bw.close();
		
		

	}
	
	// 와일드카드 패턴 w가 문자열 s에 대응되는지 여부를 반환한다.
	public static boolean match(String w, String s) {
		// w[pos]와 s[pos]를 맞춰나간다.
		int pos = 0;
		while(pos < s.length() && pos<w.length() && (w.charAt(pos) == '?' || w.charAt(pos) == s.charAt(pos))) {
			pos++;
		}
		
		// 더이상 대응할 수 없으면 왜 while문이 끝났는지 확인한다.
		// 2. 패턴 끝에 도달해서 끝난 경우: 문자열도 끝났어야 대응됨
		if(pos == w.length()) {
			return pos == s.length();
		}
		
		// 4. *를 만나서 끝난 경우: *에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
		if(w.charAt(pos) == '*') {
			for(int skip=0; pos+skip<=s.length(); skip++) {
				if(match(w.substring(pos+1), s.substring(pos+skip))) {
					return true;
				}
			}
		}
		
		// 이 외의 경우에는 모두 대응되지 않는다.
		return false;
	}
	
	// O(n^3) : 패턴길이 n, 문자열길이 n, 재귀호출 n
	public static int dynamicMatch(int w, int s) {
		// -1은 아직 답이 계산되지 않았음을 의미한다.
		// 1은 해당 입력들이 서로 대응됨을 의미한다.
		// 0은 해당 입력들이 서로 대응되지 않음을 의미한다.
		int ret = cache[w][s];
		if(ret != -1) {
			return cache[w][s];
		}
		
		// W[w]와 S[s]를 맞춰나간다.
		while(w<W.length() && s<S.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
			w++;
			s++;
		}
		
		// 더이상 대응할 수 없으면 왜 while문이 끝났는지 확인한다
		// 2. 패턴 끝에 도달해서 끝난 경우: 문자열도 끝났어야 참
		if(w == W.length()) {
			return cache[w][s] = (s == S.length() ? 1 : 0);
		}
		
		// 4. *를 만나서 끝난 경우: *에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
		if(W.charAt(w) == '*') {
			for(int skip=0; skip+s <= S.length(); ++skip) {
				if(dynamicMatch(w+1, s+skip) == 1) {
					return cache[w][s] = 1;
				}
			}
		}
		
		// 3. 이 외의 경우에는 모두 대응되지 않는다.
		return cache[w][s] = 0;
		
	}
	
	public static int dynamicMatch2(int w, int s) {
		// -1은 아직 답이 계산되지 않았음을 의미한다.
		// 1은 해당 입력들이 서로 대응됨을 의미한다.
		// 0은 해당 입력들이 서로 대응되지 않음을 의미한다.
		int ret = cache[w][s];
		if(ret != -1) {
			return cache[w][s];
		}
		
		// W[w]와 S[s]를 맞춰나간다.
		while(w<W.length() && s<S.length() && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
			return cache[w][s] = dynamicMatch2(w+1,s+1);
		}
		
		// 더이상 대응할 수 없으면 왜 while문이 끝났는지 확인한다
		// 2. 패턴 끝에 도달해서 끝난 경우: 문자열도 끝났어야 참
		if(w == W.length()) {
			return cache[w][s] = (s == S.length() ? 1 : 0);
		}
		
		// 4. *를 만나서 끝난 경우: *에 몇 글자를 대응해야 할지 재귀 호출하면서 확인한다.
		if(W.charAt(w) == '*') {
			if(dynamicMatch2(w+1,s) == 1 || (s < S.length() && dynamicMatch2(w, s+1) == 1)) {
				return cache[w][s] = 1;
			}
		}
		
		// 3. 이 외의 경우에는 모두 대응되지 않는다.
		return cache[w][s] = 0;
		
	}

}
