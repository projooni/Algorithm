package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Sortgame_solution {

	public static int N;
	public static Map<Integer, Integer> toSort[];
	public static int[] firstSeq;
	public static int[] sorted;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("E:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());
		
		toSort = new HashMap[8];
		for(int i=0; i<8; i++){
			toSort[i] = new HashMap<Integer, Integer>();
		}
		
		for(int i=0; i<8; i++){
			sorted = new int[i+1];
			for(int j=0; j<i+1; j++){
				sorted[j] = j;
			}
			toSort[i].put(Arrays.hashCode(sorted), 0);
			makeGraph(i+1);
		}
		
		for(int tc=0; tc<T; tc++){

			N = Integer.parseInt(br.readLine());
			firstSeq = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i=0; i<N; i++){
				firstSeq[i] = Integer.parseInt(st.nextToken());
			}
			
			bw.write("" + solve(firstSeq) + "\n");
			bw.flush();

		}
		
		bw.close();

	}

	public static void makeGraph(int n){

		Queue<int[]> Q = new LinkedList<int[]>();
		Q.add(sorted);

		while(!Q.isEmpty()){

			int[] nextSeq = Q.poll();
			int[] buffSeq;
			int depth = toSort[n-1].get(Arrays.hashCode(nextSeq));

			for(int i=0; i<n; i++){

				for(int j=i+1; j<n; j++){

					int h = j;
					buffSeq = new int[n];
					
					for(int k=0; k<n; k++){

						if(k >= i && k <= j){
							buffSeq[k] = nextSeq[h];
							h--;
						}else{
							buffSeq[k] = nextSeq[k];
						}

					}
					
					if(!(toSort[n-1].get(Arrays.hashCode(buffSeq)) != null )){
						toSort[n-1].put(Arrays.hashCode(buffSeq), depth+1);
						Q.add(buffSeq);
						
					}

				}

			}

		}

	}
	
	
	public static int solve(int[] srcArr){
		int[] fixed = srcArr.clone();
		
		for(int i=0; i<N; i++){
			int smaller = 0;
			for(int j=0; j<N; j++){
				if(srcArr[j] < srcArr[i]){
					smaller++;
				}
			}
			fixed[i] = smaller;
		}
		
		Map rtMap = toSort[N-1];
		int rtDepth = (int) rtMap.get(Arrays.hashCode(fixed));
		return rtDepth;
	}
	


}

