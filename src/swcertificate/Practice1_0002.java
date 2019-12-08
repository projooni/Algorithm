package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 같은 문자
public class Practice1_0002 {

    public static String A,B;
    public static int cnt_A[];
    public static int cnt_B[];

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        System.setIn(new FileInputStream("D://sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++) {

            cnt_A = new int[123];
            cnt_B = new int[123];

            A = br.readLine().trim();
            B = br.readLine().trim();

            for(int i=0; i<A.length(); i++) {
                char ch = A.charAt(i);
                cnt_A[ch]++;
            }

            for(int i=0; i<B.length(); i++) {
                char ch = B.charAt(i);
                cnt_B[ch]++;
            }

            boolean isSame = true;
            for(int i=97; i<123; i++) {
                if(cnt_A[i] != cnt_B[i]) {
                    isSame = false;
                    break;
                }
            }

            bw.flush();
            bw.write("#" + tc + " " + (isSame ? 1 : 0) + "\n");        
        }
        bw.close();

    }

}