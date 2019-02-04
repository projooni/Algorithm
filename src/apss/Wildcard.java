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

public class Wildcard {
	
	public static String W;
	public static int N;
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
				
				bw.flush();
				
				if(search(0, 0, fileName[i]) == 1) {
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
	
	// 
	public static int search(int pIdx, int fIdx, String fileName) {
		
		if(fileName.length() < 1) {
			return 1;
		}
		
		int ret = 1;
		
		// 패턴과 비교
		for(int pi=pIdx; pi<W.length(); pi++) {
			if(fIdx >= fileName.length()) {
				break;
			}
			// 현재 패턴 문자
			char p = W.charAt(pi);
			// 패턴과 다른경우
			if(p != fileName.charAt(fIdx)) {
				if(p == '?') {
					fIdx++;
					continue;
				}else if(p == '*') {
					for(int i=pi+1; i<fileName.length(); i++) {
						ret = search(pi+1, i, fileName);
						if(ret == 1) {
							return 1;
						}
					}
				}else{
					ret = 0;
					break;
				}
			}else {
				// 패턴과 일치하는데, 패턴 인덱스만 끝에 도달한경우
				if(pi == (W.length()-1) && fIdx < (fileName.length()-1)) {
					ret = 0;
					break;
				}
				fIdx++;
			}
		}
		
		return ret;
		
		
	}

}
