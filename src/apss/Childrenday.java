package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Childrenday {
	public static int N,M;
	public static int[] els;
	public static List<Integer> adjList[];
	public static Long C;
	public static boolean isPossible;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			String els_str = st.nextToken();
			els = new int[els_str.length()];
			
			for(int i=0; i<els_str.length(); i++){
				els[i] = Character.getNumericValue(els_str.charAt(i));
			}
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = (long) -1;
			isPossible = false;
			for(int i=0; i<els.length; i++) {
				if(!isPossible) {
					BFS(els[i]);
				}
			}
			
			bw.flush();
			if(isPossible) {
				bw.write(C+"\r");
			}else {
				bw.write("IMPOSSIBLE");
			}
			
			
		}
		bw.close();

	}
	
	public static void BFS(int start_vertex){
		
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(start_vertex);
		
		while(!Q.isEmpty()){
			
			int here = Q.poll();
			long tmpC;
			
			if( C == -1) {
				tmpC = (long) here;
			}else {
				if(here == 0) {
					break;
				}
				tmpC = Long.parseLong((C+ "") + (here+""));
			}
			
			if( (tmpC-M) != 0 && (tmpC-M)%N == 0 ) { // 답을 찾았으면 break
				isPossible = true;
				break;
			}else {
				if((C+"").length() == 1) {
					C = (long)0;
				}else {
					C = Long.parseLong((C+"").substring(0,(C+"").length()-1));
				}
				
			}
			
			for(int i=0; i<els.length; i++){
				Q.add(els[i]);
			}
			
			
		}
		
		
		
	}

}
