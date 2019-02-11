package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

// 바코드
public class A_0024 {
	
	public static String S;
	public static String code_a_b[] = {"LLLLLL", "LLGLGG", "LLGGLG", "LLGGGL", "LGLLGG", "LGGLLG", "LGGGLL", "LGLGLG", "LGLGGL", "LGGLGL"};
	public static String code_b[][] = {
			{"0001101", "0100111"},
			{"0011001", "0110011"}, 
			{"0010011", "0011011"}, 
			{"0111101", "0100001"}, 
			{"0100011", "0011101"}, 
			{"0110001", "0111001"}, 
			{"0101111", "0000101"}, 
			{"0111011", "0010001"}, 
			{"0110111", "0001001"},
			{"0001011", "0010111"}
			};
	public static String code_c[] = {"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};
	public static final String PREFIX = "101";
	public static final String POSTFIX = "101";
	public static final String DIVISION = "01010";
	public static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			S = br.readLine().trim();
			
			count = 0;
			
			List<String> resultList = new ArrayList<String>();
			
			for(int i=0; i<S.length(); i++) {
				
				// 101 문자열을 찾았으면 탐색 시작
				if(S.startsWith(PREFIX, i)) {
					
					String barcode = search(S.substring(i));
					if(!"FAIL".equals(barcode)) {
						resultList.add(barcode);
						count++;
						i += 13;
					}
					
				}
				
			}
			
			bw.flush();
			bw.write("#" + testCase + "-1\n");
			
		}
		
		bw.close();

	}
	
	public static String search(String s) {
		
		// 구분자 인덱스
		int m = s.indexOf(DIVISION);
		
		// 처음부터 구분자 시작지점까지 -> 그룹 B
		String b = s.substring(0, m);
		
		if(b.length() != 6*7) {
			return "FAIL";
		}
		
		// 구분자 이후 부터 끝까지 문자열
		String afterDivision  = s.substring(m+5);
		
		// 끝지점 인덱스
		int e = afterDivision.indexOf(POSTFIX);
		
		// 구분자 끝에서 끝지점까지 -> 그룹 C
		String c = s.substring(m+5, e);
		
		if(c.length() != 6*7) {
			return "FAIL";
		}
		
		String AB = getGroupAB(b);
		if("FAIL".equals(AB)) {
			return "FAIL";
		}
		
		String C = getGroupC();
		if("FAIL".equals(C)) {
			return "FAIL";
		}
		
		if(!checkValidation(AB+C)) {
			return "FAIL";
		}		
		
		return AB + C;		
		
	}
	
	public static String getGroupC() {
		return "";
	}
	
	public static String getGroupAB(String p) {
		
		if(p.length() != 6*7) {
			return "FAIL";
		}
		
		String AB = "";
		String B = "";
		
		for(int i=0; i<6*7; i+=7) {
			String num = p.substring(i, i+7);
			
			String code = "";
			for(int j=0; j<code_b.length; j++) {
				if(num.equals(code_b[j][0])) {
					code = "L";
					break;
				}
				if(num.equals(code_b[j][1])) {
					code = "G";
					break;
				}
			}			
			B += code;
		}
		
		boolean pass = false;
		for(int i=0; i<code_a_b.length; i++) {
			if(B.equals(code_a_b[i])) {
				pass = true;
				AB += i + "";
				break;
			}
		}
		
		if(!pass) {
			AB = "FAIL";
		}
			
		AB += B;
		
		return AB;
		
	}
	
	public static boolean checkValidation(String pattern) {
		
		if(pattern.length() != 13) {
			return false;
		}
		
		boolean ret = false;
		
		int sum = 0;
		// 짝수자리
		sum += Integer.parseInt(pattern.valueOf(1));
		sum += Integer.parseInt(pattern.valueOf(3));
		sum += Integer.parseInt(pattern.valueOf(5));
		sum += Integer.parseInt(pattern.valueOf(7));
		sum += Integer.parseInt(pattern.valueOf(9));
		sum += Integer.parseInt(pattern.valueOf(11));
		
		sum *= 3;
		
		// 홀수자리
		sum += Integer.parseInt(pattern.valueOf(0));
		sum += Integer.parseInt(pattern.valueOf(2));
		sum += Integer.parseInt(pattern.valueOf(4));
		sum += Integer.parseInt(pattern.valueOf(6));
		sum += Integer.parseInt(pattern.valueOf(8));
		sum += Integer.parseInt(pattern.valueOf(10));
		
		int remainder = sum%10;
		
		
		if((10-remainder) == Integer.parseInt(pattern.valueOf(12))) {
			ret = true;
		}
		
		return ret;
		
	}

}
