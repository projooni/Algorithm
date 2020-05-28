package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Edu_P_0034 {
	
	public static String K;
	public static int L;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = st.nextToken();
			L = Integer.parseInt(st.nextToken());
			
			 String ans="";
	            boolean[] primes = new boolean[L+1];
	            primes[1]=true;
	            for(int i=2; i<L; i++){
	                if(primes[i])   continue;
	                if(isAccept(i)){
	                    ans = "BAD "+i;
	                    break;
	                }
	                for(int j=i+i; j<L; j+= i){
	                    primes[j] = true;
	                }
	            }
	            if(ans.equals(""))
	                ans = "GOOD";
			
			bw.flush();
			bw.write("#" + tc + ans + "\n");
		}
		bw.close();

	}
	
	static boolean isAccept(int x){
        int ret=0;
        char[] arr = K.toCharArray();
        for(int i=0; i < arr.length; i++ ){
            ret = (ret * 10 + (arr[i]-'0')) % x;
        }
        if(ret==0){
            return true;
        }
        else{
            return false;
        }
    }

}
