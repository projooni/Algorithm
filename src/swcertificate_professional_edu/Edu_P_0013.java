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
 * 교육 P-0013
 * O(M+N)
 * 최종 아름다움은 가장 짧은 구간의 길이이다.
 * 나무의 높이는 0부터 가장 짧은 구간의 길이만큼의 숫자를 반복하면 된다.
 * 어이없는 문제..
 * */
public class Edu_P_0013 {
	
	public static int N,M;
	public static int tree[];
	public static int section[][];
	public static int beauty = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			tree = new int[N+1];
			section = new int[M][3];
			
			for(int i=0; i<N+1; i++) {
				tree[i] = -1;
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				section[i][0] = s;	// 구간시작점
				section[i][1] = e;	// 구간끝점
				section[i][2] = Math.abs(e-s)+1;	// 구간 길이
			}
			
			// 크기순 오름차순 정렬
			Arrays.sort(section, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[2] - o2[2];
				}
				
			});
			
			beauty = section[0][2];
			
			int k = 0;
			for(int i=1; i<tree.length; i++) {
				tree[i] = k;
				k++;	
				if(k == section[0][2]) {
					k =0;
				}
			}
			
			bw.flush();
			bw.write("#" + tc + " " + "\n");
			bw.write(beauty + "\n");
			for(int i=1; i<tree.length; i++) {
				bw.write(tree[i] + " ");
			}
			bw.write("\n");
			
		}
		bw.close();

	}

}
