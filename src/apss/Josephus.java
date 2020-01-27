package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Josephus {
	
	public static int N, K;
	public static List<Integer> survivors;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			survivors = new LinkedList<Integer>();
			for(int i=1; i<=N; i++) {
				survivors.add(i);
			}
			
			josephus(N, K);

			bw.flush();
			bw.write(survivors.get(0) + " " + survivors.get(1) + "\n");
			
		}
		
		bw.close();

	}
	
	public static void josephus(int n, int k) {
		
		Iterator<Integer> it = survivors.iterator();
		it.next();
		
		while(n>2) {
			it.remove();
			if(!it.hasNext()) {
				it = survivors.iterator();
			}
			it.next();
			n--;
			
			for(int i=0; i<k-1; i++) {
				if(!it.hasNext()) {
					it = survivors.iterator();
				}
				it.next();
			}
		}
			
		/**풀이1 : 풀리긴 풀리는데 알고스팟에서 RTN 에러**/
//			int indexCnt = 0;
//	        while(survivors.size() > 2){ 
//	            Iterator<Integer> it = survivors.iterator();
//	            while(it.hasNext()){
//	            	int x = it.next();
//	                if( indexCnt % k == 0){
//	                    it.remove();
//	                }
//	                indexCnt++;
//	            }
//	        }
		
		/**오답**/
//		int position = (k - 1) % n;
//        while(n > 2) {
//        	survivors.remove(position);
//            n--;
//            position = (position + (k - 1)) % n;
//        }
		
	}

}
