package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * [SW검정][교육P-0001] 큰수구하기
 * 유형 : 스택(Stack)
 * 자력풀이정도 : 0%
 * 참고 : 
 * 기타 의견 : 
 * */

public class Edu_P_0001_Stack {
	
	public static int N,K;
	public static String numStr;
	public static int arr[];

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
			
			numStr = br.readLine();
			arr = new int[numStr.length()];
			
			Stack<Integer> S = new Stack<Integer>();
			
			for(int i=0; i<numStr.length(); i++) {
				char c = numStr.charAt(i);
				int n = Integer.parseInt(String.valueOf(c));
				arr[i] = n;
			}
			
			S.push(arr[0]);
			
			int k = 0;
			for(int i=1; i<arr.length; i++) {
				
				while(k != K && !S.isEmpty() && S.peek() < arr[i]) {
					S.pop();
					k++;
				}
				
				S.push(arr[i]);
				
			}
			
			int ans[] = new int[S.size()];
			int j=S.size()-1;
			while(!S.isEmpty()) {
				ans[j] = S.pop();
				j--;
			}
			
			bw.flush();
			bw.write("#" + tc + " ");
			for(int i=0; i<ans.length; i++) {
				if(i >= N-K) {
					break;
				}
				bw.write(ans[i]+"");
			}
			bw.write("\n");
			
		}
		bw.close();

	}

}
