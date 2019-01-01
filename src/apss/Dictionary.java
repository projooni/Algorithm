package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	
	public static int[][] adj;
	public static boolean[] seen = new boolean[26];
	public static List<Integer> order;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("C://dev/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for( int testCase = 1; testCase <= T; testCase++ ){
			
			int N = Integer.parseInt(br.readLine().trim());
			String[] words = new String[N];
			for( int i = 0; i < N; i++ ){
				words[i] = br.readLine().trim();
			}
			
			makeGraph(words);
			List<Integer> rt = topologicalSort();
			
			if(rt.isEmpty()){
				bw.write("INVALID HYPOTHESIS");
			}else{
				for(int i=0; i<rt.size(); i++){
					bw.write(Character.toString((char)((int)rt.get(i)+'a')));
				}
			}
			bw.write("\n");
			bw.flush();
			
			
		}
		bw.close();

	}
	
	// 주어진 단어들로부터 알파벳 간의 선후관계 그래프를 생성한다.
	public static void makeGraph(String[] words){
		adj = new int[26][26];
		for(int j = 1; j < words.length; j++){
			int i = j - 1;
			int len = Math.min(words[i].length(), words[j].length());
			
			for(int k = 0; k < len; k++){
				if(words[i].charAt(k) != words[j].charAt(k)){
					int a = words[i].charAt(k) - 'a';
					int b = words[j].charAt(k) - 'a';
					adj[a][b] = 1;
					break;
				}
			}
			
		}
	}
	
	public static void dfs(int here){
		seen[here] = true;
		for( int there = 0; there < adj.length; there++ ){
			if(adj[here][there] == 1 && !seen[there]){
				dfs(there);				
			}
		}
		order.add(here);
	}
	
	public static List<Integer> topologicalSort(){
		int n = adj.length;
		seen = new boolean[n];
		order = new ArrayList<Integer>();
		
		// dfsAll의 역할?
		for(int i=0; i<n; i++){
			if(!seen[i]){
				dfs(i);
			}
		}
		
		Collections.reverse(order.subList(0, order.size()));
		
		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				if(adj[order.get(j)][order.get(i)] == 1){
					return new ArrayList<Integer>();
				}
			}
		}
		
		return order;
		
		
	}

}
