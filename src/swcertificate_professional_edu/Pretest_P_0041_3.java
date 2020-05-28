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
 * ���� P-0041 K-Heap �����̺�Ʈ
 * ����ҽ�
 * �����͸� �־��� �����ϴ°��, ���°�� �������� ���ϴµ� �����ʹ� ��� ���ϸ� �ε��� Ʈ���� ���
 * O(QlogN)
 * */
public class Pretest_P_0041_3 {
	
	public static int Q;
	public static int indexTree[];
	public static int base;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			Q = Integer.parseInt(br.readLine().trim());
			
			List<Integer> resultList = new ArrayList<Integer>();
			
			base = 1 << ((int)(Math.log(100000-1) / Math.log(2)) + 1);
			indexTree = new int[base*2];
			
			for(int i=0; i<Q; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				
				if(type == 1) {
					// 	ī�带 �̴´�.
					int card = Integer.parseInt(st.nextToken());
					indexTree[base + card -1]++;
					update(base + card -1, indexTree[base + card -1]);
				}else {
					// �����ڰ� ��÷�ڸ� �����Ѵ�.
					int K = Integer.parseInt(st.nextToken());
					
					int h = 1;
					while(h < base) {
						if(indexTree[h*2] < K) {
							h = h*2 + 1;
							K -= indexTree[h-1];
						}else {
							h *= 2;
						}
					}
					
					resultList.add(h-base+1);
					update(h,indexTree[h]-1);
					
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
	
	public static void update(int idx, int y) {
		
		indexTree[idx] = y;
		while(idx > 1) {
			idx /= 2; // �θ��̵�
			indexTree[idx] = indexTree[idx*2] + indexTree[idx*2+1]; 
		}
		
	}
	
	

}
