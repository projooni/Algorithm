package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 기출 P-0041 K-Heap 슈퍼이벤트
 * 
 * */
public class Pretest_P_0041_2 {
	
	public static int Q;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			Q = Integer.parseInt(br.readLine().trim());
			
			PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
			PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>();
			
			List<Integer> resultList = new ArrayList<Integer>();
			
			int pqPointer = 1;
			for(int i=0; i<Q; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				
				if(type == 1) {
					// 	카드를 뽑는다.
					int card = Integer.parseInt(st.nextToken());
					
					if(pqPointer == 1) {
						pq1.add(card);
					}else {
						pq2.add(card);
					}
					
				}else {
					// 진행자가 당첨자를 선언한다.
					
					int K = Integer.parseInt(st.nextToken());
					int cnt = 0;
					int qSize = 0;
					if(pqPointer == 1) {
						qSize = pq1.size();
					}else {
						qSize = pq2.size();
					}
					
					while(cnt != qSize) {
						
						int target = -1;
						
						if(cnt == K-1) {
							if(pqPointer == 1) {
								target = pq1.poll();
							}else {
								target = pq2.poll();
							}
							resultList.add(target);
							cnt++;
							continue;
						}
						
						if(pqPointer == 1) {
							target = pq1.poll();
							pq2.add(target);
						}else {
							target = pq2.poll();
							pq1.add(target);
						}
						
						cnt++;
						
					}
					
					pqPointer = pqPointer == 1 ? 0 : 1;
					
				}
				
			}
			
			bw.flush();
			bw.write("#" + tc);
			for(int i=0; i<resultList.size(); i++) {
				bw.write(" " + resultList.get(i));
			}
			bw.write("\n");
		}
		bw.close();

	}

}
