package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class QUADTREE {
	
	public static String str;
	public static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			str = br.readLine().trim();
			
			idx = 0;
			String result = recursive();
			
			bw.flush();
			bw.write( result);
			bw.write("\n");
			
		}
		
		bw.close();

	}
	
	public static String recursive() {
		
		
		char c = str.charAt(idx);
		
		if(c != 'x') {
			return c + "";
		}
		
		
		
		// 2사분면 search
		idx++;
		String str2 = recursive();
		// 1사분면 search
		idx++;
		String str1 = recursive();
		// 3사분면 search
		idx++;
		String str3 = recursive();
		// 4사분면 search
		idx++;
		String str4 = recursive();
		
		// 2 + 1 + 3  + 4 하면 뒤집은 쿼드트리가 나온다.
		
		return "x" + str3 + str4 + str2 + str1;				
		
	}

}
