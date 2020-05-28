package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Pretest_A_0028 {
	
	public static int N;
	public static String str1, str2;
	public static int preCnt1[], preCnt2[];
	public static long cnt1, cnt2;
	public static long dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			dp = new long[19];
			dp[1] = 1;
			factorial(18);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			str1 = st.nextToken();
			str2 = st.nextToken();
			
			preCnt1 = new int[N];
			preCnt2 = new int[N];
			
			char c3[] = new char[N];
			c3[0] = 'a';
			for(int i=1; i<N; i++) {
				c3[i] = (char) (c3[i-1]+1);
				preCnt1[i] = i;
				preCnt2[i] = i;
			}
			
			char c1[] = str1.toCharArray();
			char c2[] = str2.toCharArray();
			
			cnt1 = 0;
			cnt2 = 0;
			for(int i=0; i<N; i++) {
				cnt1 += dp[N-i-1] * preCnt1[c1[i]-'a'];
				for(int j=(c1[i]-'a')+1; j<N; j++) {
					preCnt1[j]--;
				}
				cnt2 += dp[N-i-1] * preCnt2[c2[i]-'a'];
				for(int j=(c2[i]-'a')+1; j<N; j++) {
					preCnt2[j]--;
				}
			}
			cnt1+=1;
			cnt2+=1;
			bw.flush();
			bw.write("#" + tc + " " + (Math.abs(cnt2-cnt1)-1) +  "\n");
		}
		bw.close();
	}
	
	public static long factorial(int n) {
		
		if(n == 0) {
			return 0;
		}
		
		if(n == 1) {
			return 1;
		}
		
		if(dp[n] == 0) {
			dp[n] = n*factorial(n-1);
		}
		
		return dp[n];
	}

}
