package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * ���� A-0022 �Ҽ����
 * ���� ü���� ����, �ٽ� Ǯ� �ʿ䰡 ����
 * */
public class Prac_A_0022 {
	
	public static int A, B;
	public static int P[] = new int[10000];;
	public static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		getPrimes();
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// �ش� �Ҽ����� �����ϱ������ ī��Ʈ
			// ������� 1033 -> 1733 -> 3733 �̸� V[3733] == 2
			int V[] = new int[10000];
			
			// BFS ����
			// BFS�̱� ������ ���ʷ� �߰ߵǴ� ���� �ּ� ������� �� �� �ִ�.
			int ans = 0;
			Deque<Integer> qu = new ArrayDeque<>();
			qu.add(A);
			loop: while(!qu.isEmpty()) {
				int nn  = qu.poll();
				
				for(int j=0; j<4; j++) {
					
					char[] c = Integer.toString(nn).toCharArray();
					for(int k=0; k<=9; k++) {
						
						// õ�� �ڸ����� 0�� �� �� ����
						if(j==0 && k == 0) {
							continue;
						}
						
						// integer -> chracter ��ȯ
						c[j] =  (char)(k + '0');
						
						// x : �ٲ� ����
						int x = Integer.parseInt(new String(c));
						
						// x�� �Ҽ��̸�, ���ʷ� ������ �����̸�, ���� ���ڰ� �ƴѰ�� ī��Ʈ ����
						if(P[x] == 1 && V[x] == 0 && nn != x) {
							V[x] = V[nn] + 1;
							qu.add(x);
						}
						
						// x�� ���� �Ҽ��� ������ ���
						if(x == B) {
							ans = V[x];
							break loop;
						}
						
					}
					
				}
				
			}
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

	}
	
	// 100�������� �������� ���� �� �ִ�
	public static void getPrimes(){

		// ������ ¦���Ҽ� 2�� Ȧ���� ������ ���� �Ҽ����ɼ��� ������ ����
		P[2] = 1;
		for(int i=3; i<10000; i+=2) {
			P[i] = 1;
		}
		
		// 3���� �����ؼ� Ȧ���� Ž��, ����� �Ҽ����� ����
		for(int i=3; i<10000; i+=2) {
			if(P[i] == 0) continue;
			int v = i + i;
			while(v < 10000) {
				P[v] = 0;
				v += i;
			}
		}
		
	}

}
