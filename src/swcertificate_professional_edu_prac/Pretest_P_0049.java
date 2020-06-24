package swcertificate_professional_edu_prac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Pretest_P_0049 {
	
	public static int N,K;
	public static int base;
	public static int arr[][];
	public static int ref[];
	public static int indexTree[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[N+1][2];
			ref = new int[N+1];
			
			st = new StringTokenizer(br.readLine().trim());
			for(int i=1; i<=N; i++) {
				arr[i][0] = i;
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			base = 1 << (int)(Math.log(100000-1)/Math.log(2)) + 1;
			indexTree = new int[base*2];
			
			Arrays.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] arg0, int[] arg1) {
					// TODO Auto-generated method stub
					return arg0[1] - arg1[1];
				}
				
			});
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i == arr[j][0]) {
						ref[i] = j;
						break;
					}
				}
			}
			
			int k = (K+1)/2;
			int s = 0;
			int e = 1;
			int max = 0;
			while(e <= N) {
				
				update(base + ref[e] - 1,1);
				
				if(e-s == K) {
					if(s != 0) {
						update(base + ref[s] - 1,0);
					}
					int mid = find(k);
					max = Math.max(mid, max);
					s++;
				}
				
				e++;
				
			}
			
			bw.flush();
			bw.write("#" + tc + " " + max + "\n");
			
		}
		bw.close();
		
	}
	
	public static void update(int idx, int val) {
		indexTree[idx] = val;
		while(idx > 1) {
			idx /= 2;
			indexTree[idx] = indexTree[idx*2] + indexTree[idx*2+1];
		}
	}
	
	public static int find(int k) {
		
		int idx = 1;
		while(idx < base) {
			if(indexTree[idx*2] < k) {
				idx = idx*2 + 1;
			}else {
				idx = idx*2;
			}
		}
		
		return arr[idx-base+1][1];
		
	}

}
