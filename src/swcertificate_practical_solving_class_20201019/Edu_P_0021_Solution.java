package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
/*
 * 
 *  < 실전문제풀이반 (2020.10.19 ~ 2020.10.23) 3일차 >
 *  
 * 문제명    : [연습P-0021] 바둑돌 잇기
 * 난이도    : 중상
 * 유형       : DP
 * 정답여부 : O
 * 기여도    : 0% (누군가의 JAVA 소스)
 * 기타
 *   
 * */
 
public class Edu_P_0021_Solution {
     
    public static int N;
    public static final int MAX = N+1;
    public static String inputStr;
    public static int map[];
    public static int dp[][];
    public static int dph[][];
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        int T = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++) {
             
            N = Integer.parseInt(br.readLine().trim());
            inputStr = br.readLine().trim();
             
            map = new int[N+1];
            dp = new int[N+1][N+1];
            dph = new int[N+1][N+1];
             
            for(int i=1; i<=N; i++) {
                 
                map[i] = inputStr.charAt(i-1) - '0';
                 
                for(int j=1; j<=N; j++) {
                    dp[i][j] = N*N;
                }
                     
            }
             
            for(int i=2; i<=N; i++) {
                if(map[i-1] != map[i]) {
                    dp[i-1][i] = 3;
                    dph[i-1][i] = 1;
                }
            }
             
            for(int i=3; i<=N; i+=2) {
                for(int j=1; j+i<=N; j++) {
                     
                    int s = j;
                    int e = j+i;
                     
                    if(map[s] != map[e]) {
                        dp[s][e] = dp[s+1][e-1] + 2*(dph[s+1][e-1]+1) + i;
                        dph[s][e] = dph[s+1][e-1] + 1;
                    }
                     
                    for(int k=s+1; k<e; k++) {
                        if(dp[s][e] > dp[s][k] + dp[k+1][e]) {
                            dp[s][e] = dp[s][k] + dp[k+1][e];
                            dph[s][e] = Math.max(dph[s][k], dph[k+1][e]);
                        }
                    }
                     
                }
            }
             
            bw.flush();
            bw.write("#" + tc + " " + dp[1][N] + "\n");
             
        }
        bw.close();
                 
    }
     
}
