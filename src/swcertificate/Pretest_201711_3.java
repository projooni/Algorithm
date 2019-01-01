package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Pretest_201711_3 {
	public static int N;
	public static int M;
	public static ArrayList<Integer> adjList[];
	public static int count;
	public static List<Integer> rstList;
	public static int[] newNodeNum;
	public static int[][] edgeNum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		// DFS 스패닝 트리
		
		System.setIn(new FileInputStream("E:\\sample_input_2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			rstList = new ArrayList<Integer>();
			newNodeNum = new int[N+1];
			edgeNum = new int[N+1][N+1];
			
			int startNode=0, endNode=0;
			
			for(int i=1; i<=N; i++){
				adjList[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=M; i++){
				
				st = new StringTokenizer(br.readLine());
				startNode = Integer.parseInt(st.nextToken());
				endNode = Integer.parseInt(st.nextToken());
				adjList[startNode].add(endNode);
				adjList[endNode].add(startNode);
				edgeNum[startNode][endNode] = i;
				edgeNum[endNode][startNode] = i;
				
			}
			
			count = 1;
			dfs(1, 0);
			
			Collections.sort(rstList);
			bw.write("#" + tc + " " + rstList.size());
			for(int i=0; i<rstList.size(); i++){
				bw.write(" " + rstList.get(i));
			}
			bw.write("\n");
			bw.flush();
			
		}
		
		bw.close();

	}
	
	public static int dfs(int here, int parent){
		
		// DFS로 새로운 노드번호를 할당한다. 1부터 시작
		newNodeNum[here] = count++;
		// 기본 리턴값을 현재 노드의 새로운 번호로 초기화한다.
		int rt = newNodeNum[here];
		
		// 인접 노드 검사
		for(int i=0; i<adjList[here].size(); i++){
			int there = adjList[here].get(i);
			
			if(parent != there){
				int min = Integer.MAX_VALUE;
				if(newNodeNum[there] == 0){
					min = dfs(there, here);
					if(min > newNodeNum[here]){
						rstList.add(edgeNum[here][there]);
					}
					rt = rt > min ? min : rt;
				}else{
					rt = rt > newNodeNum[there] ? newNodeNum[there] : rt;
				}
			}
			
		}
		
		return rt;
		
	}

}
