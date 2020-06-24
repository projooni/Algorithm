package swcertificate_professional_edu_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Pretest_P_0041 {
	
	public static int Q;
	public static int indexTree[];
	public static int base;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			Q = Integer.parseInt(br.readLine().trim());
			
			// 왜 -1을 하는지?
			base = 1 << (int)(Math.log(100000-1) / Math.log(2)) + 1;
			indexTree = new int[base*2];
			
			bw.flush();
			bw.write("#" + tc);
			for(int i=0; i<Q; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int type = Integer.parseInt(st.nextToken());
				
				if(type == 1) {
					int card = Integer.parseInt(st.nextToken());
					indexTree[base + card-1]++;
					update(base + card-1, indexTree[base+card-1]);
				}else {
					int k = Integer.parseInt(st.nextToken());
					int idx = 1;
					while(idx < base) {
						if(indexTree[idx*2] < k) {
							idx = idx*2 + 1;
							k -= indexTree[idx-1];
						}else {
							idx = idx*2;
						}
					}
					bw.write(" " + (idx - base + 1));
					update(idx, indexTree[idx] - 1);
				}
			}
			bw.write("\n");
			
		}
		bw.close();

	}
	
	public static void update(int idx, int y) {
		indexTree[idx] = y;
		while(idx > 1) {
			idx /= 2;
			indexTree[idx] = indexTree[idx*2] + indexTree[idx*2+1];
		}
	}

}
