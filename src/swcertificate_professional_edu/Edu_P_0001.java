package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 정답아님!
 * 내가 푼 방식인데 SW검정 사이트에서는 정답처리되지만, 정답이 아닌것으로 판단됨 
 * */

public class Edu_P_0001 {
	
	public static int N,K;
	public static String num;
	public static int[][] arr;
	public static int[][] arr2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N][2];
			arr2 = new int[N-K][2];
			
			num = br.readLine().trim();
			
			for(int i=0; i<N; i++) {
				arr[i][0] = (int)(num.charAt(i)-'0');
				arr[i][1] = i;
			}
			
			Arrays.sort(arr,new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[0] - o2[0];
				}
				
			});
			
			int j=0;
			for(int i=K; i<N; i++) {
				arr2[j][0] = arr[i][0];
				arr2[j][1] = arr[i][1];
				j++;
			}
			
			Arrays.sort(arr2,new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[1] - o2[1];
				}
				
			});
			
			bw.flush();
			bw.write("#" + tc + " ");
			for(int i=0; i<N-K; i++) {
				bw.write(arr2[i][0]+"");
			}
			bw.write("\n");
			
		}
		bw.close();
		

	}

}
