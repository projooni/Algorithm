package bj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class bj1991 {
	
	public static int N;
	public static List<Integer> adjList[];
	public static String pre, in, post;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int tc=1; tc<=1; tc++) {
			
			N = Integer.parseInt(br.readLine());
			adjList = new ArrayList[65+26];
			for(int i=65; i<adjList.length; i++) {
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				// TODO String -> char�� ��ȯ�ϴ� �� ���� �����?
				int m = st.nextToken().charAt(0);
				int l = st.nextToken().charAt(0);
				int r = st.nextToken().charAt(0);
				
				adjList[m].add(l);
				adjList[m].add(r);
				
			}
			
			pre = "";
			in = "";
			post = "";
			search('A');
			
			bw.flush();
			bw.write(pre + "\n");
			bw.write(in + "\n");
			bw.write(post + "\n");
			
		}
		
		bw.close();

	}
	
	public static void search(int curr) {
		
		char currChar = (char) curr;
		pre += currChar;
		
		boolean isRoot = false;
		for(int i=0; i<adjList[curr].size(); i++) {
			int next = adjList[curr].get(i);
			if(next == '.') {
				continue;
			}
			search(next);
			if(i==0) {
				in += currChar;
				isRoot = true;
			}
		}
		
		if(!isRoot) {
			in += currChar;
		}
		post += currChar;
		
	}

}
