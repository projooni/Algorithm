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
 * 연습 A-0022 소수경로
 * 완전 체득을 위해, 다시 풀어볼 필요가 있음
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
			
			// 해당 소수까지 도달하기까지의 카운트
			// 예를들어 1033 -> 1733 -> 3733 이면 V[3733] == 2
			int V[] = new int[10000];
			
			// BFS 시작
			// BFS이기 때문에 최초로 발견되는 답이 최소 경로임을 알 수 있다.
			int ans = 0;
			Deque<Integer> qu = new ArrayDeque<>();
			qu.add(A);
			loop: while(!qu.isEmpty()) {
				int nn  = qu.poll();
				
				for(int j=0; j<4; j++) {
					
					char[] c = Integer.toString(nn).toCharArray();
					for(int k=0; k<=9; k++) {
						
						// 천의 자리에는 0이 올 수 없다
						if(j==0 && k == 0) {
							continue;
						}
						
						// integer -> chracter 변환
						c[j] =  (char)(k + '0');
						
						// x : 바뀐 숫자
						int x = Integer.parseInt(new String(c));
						
						// x가 소수이며, 최초로 도달한 숫자이며, 원래 숫자가 아닌경우 카운트 증가
						if(P[x] == 1 && V[x] == 0 && nn != x) {
							V[x] = V[nn] + 1;
							qu.add(x);
						}
						
						// x가 최종 소수에 도달한 경우
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
	
	// 100만까지는 무리없이 구할 수 있다
	public static void getPrimes(){

		// 유일한 짝수소수 2와 홀수를 제외한 수는 소수가능성이 없으니 제외
		P[2] = 1;
		for(int i=3; i<10000; i+=2) {
			P[i] = 1;
		}
		
		// 3부터 시작해서 홀수만 탐색, 배수만 소수에서 제외
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
