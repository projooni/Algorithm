package apss;



import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileInputStream;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.util.ArrayList;

import java.util.LinkedList;

import java.util.Queue;

import java.util.StringTokenizer;



public class Sortgame {

	public static int N;
	public static ArrayList<Integer> visited;
	public static int finalDepth;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine().trim());

		for(int tc=0; tc<T; tc++){

			N = Integer.parseInt(br.readLine());
			visited = new ArrayList<Integer>();
			int[] firstSeq = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int i=0; i<N; i++){
				firstSeq[i] = Integer.parseInt(st.nextToken());
			}

			finalDepth = 0;
			makeGraph(firstSeq);

			bw.write("" + (finalDepth) + "\n");
			bw.flush();

		}
		
		bw.close();

	}

	public static void makeGraph(int[] preSeq){

		Queue<int[]> Q = new LinkedList<int[]>();
		Queue<Integer> Q_Depth = new LinkedList<Integer>();
		Q.add(preSeq);
		Q_Depth.add(0);

		boolean flag = false;
		for(int i=0; i<N; i++){

			if(preSeq[i] != (i+1)){
				flag = true;
				break;
			}

		}

		if(!flag){
			return;
		}

		while(!Q.isEmpty()){

			int[] nextSeq = Q.poll();
			int[] buffSeq;
			int depth = Q_Depth.poll();
			boolean isNotSorted = false;

			for(int i=0; i<N; i++){

				for(int j=i+1; j<N; j++){

					int h = j;
					isNotSorted = false;
					buffSeq = new int[N];
					
					for(int k=0; k<N; k++){

						if(k >= i && k<=j){
							buffSeq[k] = nextSeq[h];
							h--;
						}else{
							buffSeq[k] = nextSeq[k];
						}

						if((k+1) != buffSeq[k]){
							isNotSorted = true;
						}

					}

					if(isNotSorted == false){ // 정렬이 되었다면
						finalDepth = depth+1;
						return;
					}

					Q.add(buffSeq);
					Q_Depth.add(depth+1);

				}

			}

			

		}

		

	}


}

