package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;


public class Meetingroom_Solution {
	
	public static int N;
	public static ArrayList<Integer> adjList[];
	public static int sccId[];
	public static int discovered[];
	public static int sccCounter, vertexCounter;
	public static Stack<Integer> st;
	public static Pair meetingTimePair[];
	
	public static class Pair{
		int s;
		int e;
		public Pair(int si, int ei){
			s = si;
			e = ei;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int C = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st;
		for(int tc=1; tc<=C; tc++){
			
			N = Integer.parseInt(br.readLine().trim());
			meetingTimePair = new Pair[2*N];
			
			// O(N)
			for(int i=0; i<2*N; i=i+2){
				st = new StringTokenizer(br.readLine());
				int s1 = Integer.parseInt(st.nextToken());
				int e1 = Integer.parseInt(st.nextToken());
				int s2 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				meetingTimePair[i] = new Pair(s1,e1);
				meetingTimePair[i+1] = new Pair(s2,e2);
			}
			
			makeGraph(meetingTimePair);
			int[] result = solve2SAT();
			
			bw.flush();
			
			Pair [] out = new Pair[N];
			String outStr = "POSSIBLE";
			for(int i=0; i<result.length/2; i+=2) {
				if(result[i] == 0 && result[i+1] == 0) {
					outStr = "IMPOSSIBLE";
					break;
				}
				
				out[i/2] = result[i] ==1 ? meetingTimePair[i] : meetingTimePair[i+1];
			}
			
			bw.write(outStr+"\n");
			if(!"IMPOSSIBLE".equals(outStr)) { // POSSIBLE이면
				for(int i=0; i<N; i++) {
					bw.write(out[i].s + " " + out[i].e + "\n");
				}
			}
			
			
				
				
		}
		
		bw.close();
		

	}
	public static int[] solve2SAT(){
		int n = adjList.length/2;
		
		// SCC로 묶는다.
		int[]  label = tarjanSCC();
		
		// 이 SAT 문제를 푸는것이 가능한지 확인한다.
		// 한 변수를 나타내는 두 정점이 같은 강결합 요소에 속해 있을 경우 답이 없다.
		for(int i=0; i<2*n; i+=2) {
			if(label[i] == label[i+1]) {
				return new int[2*n];
			}
		}
		
		// SAT 문제를 푸는게 가능하다. 답을 생성하자.
		int[] value = new int[n*2];
		Arrays.fill(value, -1);
		
		// Tarjan 알고리즘에서 SCC 번호는 위상 정렬의 역순으로 배정된다.
		// SCC 번호의 역순으로 각 정점을 정렬하면 위상 정렬 순서가 된다.
		Pair[] order = new Pair[2*n];
		for(int i=0; i<2*n; i++) {
			order[i] = new Pair(-label[i],i);
		}
		Arrays.sort(order, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				Pair a = (Pair)o1;
				Pair b = (Pair)o2;
				return a.s - b.s;
			}
		});
		
		// 각 정점에 값을 배정한다.
		for(int i=0; i<2*n; i++) {
			int vertex = order[i].e;
			int variable = vertex / 2;
			boolean isTrue = (vertex % 2 == 0);
			
			if(value[variable] != -1) {
				continue;
			}
			
			// not A가 A보다 먼저 나왔으면 A는 참
			// A가 not A보다 먼저 나왔으면 A는 거짓
			value[variable] = isTrue ? 0 : 1;
			
		}
		
		return value;
	}
	public static int  scc(int here) {
		int ret = discovered[here] = vertexCounter++;
		// 스택에 here를 넣는다. here의 후손들은 모두 스택에서 here 후에 들어간다.
		st.push(here);
		for(int i=0; i<adjList[here].size(); i++) {
			int there = adjList[here].get(i);
			// here -> there 가 트리간선
			if(discovered[there] == -1) {
				ret = Math.min(ret, scc(there));
			}else if(sccId[there] == -1) { // there가 무시해야 하는 교차 간선이 아니라면
				ret = Math.min(ret, discovered[there]);
			}
		}
		
		// here에서 부모로 올라가는 간선을 끊어야 할지 확인한다.
		if(ret == discovered[here]) {
			// here를 루트로 하는 서브트리에 남아 잇는 정점들을 전부 하나의 컴포넌트로 묶는다.
			while(true) {
				int t = st .pop();
				sccId[t] = sccCounter;
				if(t == here) break;
			}
			++sccCounter;
		}
		
		return ret;
		
	}
	public static int[] tarjanSCC(){
		// 배열 초기화
		sccId   = discovered = new int[adjList.length];
		Arrays.fill(sccId, -1);
		Arrays.fill(discovered, -1);
		// 카운터 초기화
		sccCounter = vertexCounter = 0;
		// 모든 정점에 대해 scc() 호출
		for(int i=0; i<adjList.length; i++) {
			if(discovered[i] == -1) {
				st = new Stack<Integer>();
				scc(i);
			}
		}
		
		return sccId;
	}
	public static void makeGraph(Pair[] meetings) {
		int vars = meetings.length;
		
		adjList = new ArrayList[vars*2];
		for(int i=0; i< adjList.length; i++) {
			adjList[i] = new ArrayList();
		}
		
		for(int i=0; i<vars; i+=2) {
			int j = i+1;
			adjList[i*2 + 1].add(j*2);
			adjList[j*2 + 1].add(i*2);
		}
		
		for(int i=0; i<vars; i++) {
			for(int j=0; j<i; j++) {
				// i번 회의와 j번 회의가 서로 겹칠경우
				if(!disjoint(meetings[i], meetings[j])) {
					adjList[i*2].add(j*2 + 1);
					adjList[j*2].add(i*2 + 1);
				}
				
			}
		}
		
		
	}
	public static boolean disjoint(Pair a, Pair b) {
		return a.e <= b.s || b.e <= a.s;
	}
	

}
