package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ant {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1 <= T <= 50
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			long startTime = System.currentTimeMillis();
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			StringTokenizer st = new StringTokenizer(br.readLine()); 
			
			// 2 <= K <= N <= 100,000
			// 2<= L <= 1000000000
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[] posArr = new int[N];
			char[] dirArr = new char[N];
			boolean[] isFallArr = new boolean[N];
			
			for(int i=0; i<N; i++){
				st = new StringTokenizer(br.readLine()); 
				posArr[i] =  Integer.parseInt(st.nextToken());
				dirArr[i] = st.nextToken().charAt(0);
			}
			
			int count = 0;
			int numOfAnt = 0;
			int timeOfAnt = 0;
			
			while(count < K){
				
				for(int j=0; j<N; j++){
					// ���̵��� ��ȯ�ϸ� ��ġ ����
					if(dirArr[j] == 'L'){
						posArr[j]--;
					}else{
						posArr[j]++;
					}
					
					// ���� �����ϸ� ī����
					if(isFallArr[j] != true && (posArr[j] <= 0 || posArr[j] >= L) ){
						count++;
						isFallArr[j] = true;
						if(count >= K){
							if(numOfAnt != 0){
								if(dirArr[j] == 'L'){
									numOfAnt = j+1;
								}
							}else{
								numOfAnt = j+1;
							}
							break;
						}
					}
					
				}
				
				
				
				// �浹�� ���� ������
				for(int m=0; m<N; m++){
					for(int n=1; n<N; n++){
						if(m != n){
							if(posArr[m] == posArr[n]){
								char tmp = dirArr[m];
								dirArr[m] = dirArr[n];
								dirArr[n] = tmp;
							}
						}
					}
				}
				
				timeOfAnt++;
			}
			
				
			
			
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
//		    System.out.println("total elapsed time = " + elapsedTime/1000);
			
			

			

			System.out.println("#" + (test_case+1) + " " + numOfAnt + " " + timeOfAnt);
			

		}

	}

}
