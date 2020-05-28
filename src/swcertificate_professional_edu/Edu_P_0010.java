package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

/*
 * [교육 P-0010] 금속활자 인쇄기
 * Trie 알고리즘
 * TreeMap을 사용하는 이유는 정렬 때문에?
 * String은 느리므로 StringBuffer 사용. StringBuilder도 비슷한데 스레드 처리가 다르다? -> 알아보기
 * 트라이 알고리즘 -> 사전식 배열, 출력 등
 * */
public class Edu_P_0010 {
	
	public static int N; // 단어의 수 : 1<= N <=25000
	public static String word[];
	public static int trie[];
	
	public static class TrieNode{
		char c;
		TreeMap<Character, TrieNode> children;
		boolean isEnd;
		public TrieNode(char c) {
			this.c = c;
			this.children = new TreeMap<>();
			isEnd = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine());
			TrieNode root = new TrieNode(' ');
			
			for(int i=0; i<N; i++) {
				TrieNode cur = root;
				char[] word = br.readLine().trim().toCharArray();
				for(char c : word) {
					// 해당 자식을 갖고 있지 않다면 신규 생성
					if(!cur.children.containsKey(c)) {
						TrieNode child = new TrieNode(c);
						cur.children.put(c, child);
					}
					cur = cur.children.get(c);
				}
				cur.isEnd = true;
			}
			
			StringBuffer sb = new StringBuffer();
			dfs(sb, root);
			
			bw.flush();
			bw.write("#" + tc + " " + sb.toString() + "\n");
		}
		bw.close();

	}
	
	public static void dfs(StringBuffer sb, TrieNode node) {
		if(node.isEnd) {
			sb.append('P');
		}
		for(char c : node.children.keySet()) {
			sb.append(c);
			dfs(sb, node.children.get(c));
			sb.append('-');
		}
	}

}
