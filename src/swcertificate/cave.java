package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cave {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=0; testCase<T; testCase++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			int[] S = new int[H+2];
			
			for(int i=0; i<N; i++){
				int h = Integer.parseInt(br.readLine());
				if((i%2) == 1){
					//Ȧ�� �϶�
					S[1]++;
					S[h+1]--;
				}else{
					//¦�� �϶�
					S[H-h+1]++;
					S[H+1]--;					
				}
			}
			
			for(int i=2; i<H+1; i++){
				S[i] += S[i-1];
			}
			
			int min = Integer.MAX_VALUE;
			int count = 0;
			for(int i=1; i<=H; i++){
				if(min > S[i]){
					min = S[i];
					count = 1;
				}else if(min == S[i]) count++;
								
			}
			
			System.out.println("#" + testCase + " " + min + " " + count);
			
		}
	}
}
