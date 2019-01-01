package apss;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Wordchain {
	
	public static int N;	// 1 ~ 100
	public static String[] wordList;
	public static boolean isImposible;
	public static int[] terminerNodes;
	// 그래프의 인접행렬표현. adj[i][j] = i와 j사이의 간선의 수
	public static int[][] adj;
	public static List<Integer> circuit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=0; testCase < T; testCase++){
			N = Integer.parseInt(br.readLine().trim());
			wordList = new String[N];
			circuit = new ArrayList<Integer>();
			adj = new int[N][N];
			terminerNodes = new int[2];
			isImposible = false;
			
			for(int i=0; i<N; i++){
				wordList[i] = br.readLine();
			}
			
			char here_last;
			char there_first;
			boolean isConnected = false;
			for(int i=0; i<N; i++){
				
				here_last = wordList[i].charAt(wordList[i].length()-1);
				
				for(int j=0; j<N; j++){
					
					if(i == j){
						continue;
					}
					
					there_first = wordList[j].charAt(0);
					
					if(here_last == there_first){
						adj[i][j] = 1;
						adj[j][i] = 1;
						isConnected = true;
					}
					
				}
				
			}
			
			int sum = 0;
			int k = 0;
			for(int i=0; i<N; i++){
				
				if(k >= 2){
					isImposible = true;
					break;
				}
				
				sum = 0;
				for(int j=0; j<N; j++){
					
					if(adj[i][j] > 0){
						sum++;
					}
					
				}
				
				if(sum%2 != 0){
					terminerNodes[k] = i;
					k++;
				}
				
			}
			
			if(!isImposible){
				adj[terminerNodes[0]][terminerNodes[1]]++;
				adj[terminerNodes[1]][terminerNodes[0]]++;
			}
			
			
			if(isImposible || !isConnected){
//				bw.write("IMPOSIBLE");
				System.out.println("IMPOSIBLE");
			}else{
				getEulerCircuit(0);
				for(int i=circuit.size()-1; i>=0; i--){
					System.out.print(wordList[circuit.get(i)]);
					if(i != 0){
						System.out.print(" ");
					}
				}
				System.out.println("");
				
			}
//			bw.write("\n");
//			bw.flush();
			
		}
		
//		bw.close();
		
		

	}
	
	public static void getEulerCircuit(int here){
		for(int there=0; there < N; there++){
			while(adj[here][there] > 0){
				adj[here][there]--;
				adj[there][here]--;
				getEulerCircuit(there);
			}
		}
		if(!circuit.contains(here)){
			circuit.add(here);
			
		}
		
	}

}
