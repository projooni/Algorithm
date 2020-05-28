package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 교육 P-0005 확장 유클리드 호제법
 * 
 * */
public class Edu_P_0005 {
	
	public static int X, Y, Z;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Z = Integer.parseInt(st.nextToken());
			
			if(Z % gcd(X,Y) == 0) {
				
			}else {
				
			}
			
			
			bw.flush();
			bw.write("#" + tc + "\n");
		}
		bw.close();

	}
	
	public static int gcd(int a, int b) {
		return b==0 ? a : gcd(b,a%b);
	}
	
	

}
