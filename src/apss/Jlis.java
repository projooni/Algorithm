package apss;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jlis {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C:\\dev\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=0; testCase<T; testCase++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] arrN = new int[N];
			int[] arrM = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				arrN[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++){
				arrM[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arrN);
			Arrays.sort(arrM);
			
			int i = 0;
			int j = 0;
			int count = 0;
			
			while(i < N || j < M){
				
				if(i == N){
					j++;
				}else if(j == M){
					i++;
				}else{
					if(arrN[i] == arrM[j]){
						i++;
						j++;
					}else if(arrN[i] < arrM[j]){
						i++;
					}else{
						j++;
					}
				}
				count++;
			}
			
			System.out.println(count);
		}

	}

}
