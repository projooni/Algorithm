package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Wordchain_Solution {
	
	public static int N;
	public static int[][] adj;
	public static ArrayList<String> graph[][];
	public static int[] indegree, outdegree;
//	public static List<Integer> circuit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("E://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int testCase=0; testCase < T; testCase++){
			
			N = Integer.parseInt(br.readLine().trim());
			adj = new int[26][26];
			graph = new ArrayList[26][26];
			indegree = new int[26];
			outdegree = new int[26];
			
			String[] words = new String[N];
			for(int i=0; i<N; i++){
				words[i] = br.readLine();
			}
			
			bw.write(solve(words) + "\n");
			bw.flush();
			
		}

	}
	
	public static void makeGraph(String[] words){
		
		// �쟾�뿭 蹂��닔 珥덇린�솕
		for(int i=0; i<26; i++){
			for(int j=0; j<26; j++){
				graph[i][j] = new ArrayList<String>();
			}
		}
		
		// 媛� �떒�뼱瑜� 洹몃옒�봽�뿉 異붽��븳�떎.
		for(int i=0; i<words.length; i++){
			int a = words[i].charAt(0) - 'a';
			int b = words[i].charAt(words[i].length()-1) - 'a';
			graph[a][b].add(words[i]);
			adj[a][b]++;
			outdegree[a]++;
			indegree[b]++;
		}
		
	}
	
	public static void getEulerCircuit(int here, List<Integer> circuit){
		
		for(int there=0; there<26; there++){
			while(adj[here][there] > 0){
				adj[here][there]--;
				getEulerCircuit(there, circuit);
			}
		}
		circuit.add(here);
	}
	
	// 현재 그래프의 오일러 트레일이나 서킷을 반환하다.
	public static List<Integer> getEulerTrailOrCircuit(){
		
		List<Integer> circuit = new ArrayList<Integer>();
		
		// 우선 트레일을 찾아본다: 시작점이 존재하는 경우
		for(int i=0; i<26; i++){
			if(outdegree[i] == indegree[i]+1){
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}
		
		// 아니면 서킷이니, 간선에 인접한 아무 정점에서나 시작한다.
		for(int i=0; i<26; i++){
			if(outdegree[i] > 0){
				getEulerCircuit(i, circuit);
				return circuit;
			}
			
		}
		
		// 모두 실패한 경우 빈 배열을 반환한다.
		return circuit;
		
	}
	
	// 현재 그래프의 오일러 서킷/트레일 존재 여부를 확인한다.
	public static boolean checkEuler(){
		
		// 예비 시작점과 끝점의 수
		int plus1 = 0, minus1 = 0;
		boolean ret = false;
		
		for(int i=0; i<26; i++){
			int delta = outdegree[i] - indegree[i];
			
			// 모든 정점의 차수는 -1, 1 또는 0이어야 한다.
			if(delta < -1 || delta > 1) return false;
			if(delta == 1) plus1++;
			if(delta == -1) minus1++;
			
			// 시작점과 끝점은 각 하나씩 있거나 하나도 없어야 한다.
			ret = (plus1 == 1 && minus1 == 1) || (plus1 == 0 && minus1 == 0);
		}
		
		return ret;
		
	}
	
	public static String solve(String[] words){
		
		makeGraph(words);
		
		// 차수가 맞지 않으면 실패!
		if(!checkEuler()) return "IMPOSSIBLE";
		
		// 오일러 서킷이나 경로를 찾아낸다
		List<Integer> circuit = getEulerTrailOrCircuit();
		
		// 모든 간선을 방문하지 못했으면 실패
		if(circuit.size() != words.length + 1) return "IMPOSSIBLE";
		
		// 아닌 경우 방문 순서를 뒤집은 뒤 간선들을 모아 문자열로 만들어 반환한다.
		String ret = "";
		for(int i=circuit.size()-2; i>=0; i--){
			int a = circuit.get(i+1), b = circuit.get(i);
			if(ret.length() > 0) ret += " ";
			ret += graph[a][b].get(graph[a][b].size()-1);
		}
		
		return ret;
		
	}

}
