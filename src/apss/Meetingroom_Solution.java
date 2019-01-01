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
			if(!"IMPOSSIBLE".equals(outStr)) { // POSSIBLE�̸�
				for(int i=0; i<N; i++) {
					bw.write(out[i].s + " " + out[i].e + "\n");
				}
			}
			
			
				
				
		}
		
		bw.close();
		

	}
	public static int[] solve2SAT(){
		int n = adjList.length/2;
		
		// SCC�� ���´�.
		int[]  label = tarjanSCC();
		
		// �� SAT ������ Ǫ�°��� �������� Ȯ���Ѵ�.
		// �� ������ ��Ÿ���� �� ������ ���� ������ ��ҿ� ���� ���� ��� ���� ����.
		for(int i=0; i<2*n; i+=2) {
			if(label[i] == label[i+1]) {
				return new int[2*n];
			}
		}
		
		// SAT ������ Ǫ�°� �����ϴ�. ���� ��������.
		int[] value = new int[n*2];
		Arrays.fill(value, -1);
		
		// Tarjan �˰��򿡼� SCC ��ȣ�� ���� ������ �������� �����ȴ�.
		// SCC ��ȣ�� �������� �� ������ �����ϸ� ���� ���� ������ �ȴ�.
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
		
		// �� ������ ���� �����Ѵ�.
		for(int i=0; i<2*n; i++) {
			int vertex = order[i].e;
			int variable = vertex / 2;
			boolean isTrue = (vertex % 2 == 0);
			
			if(value[variable] != -1) {
				continue;
			}
			
			// not A�� A���� ���� �������� A�� ��
			// A�� not A���� ���� �������� A�� ����
			value[variable] = isTrue ? 0 : 1;
			
		}
		
		return value;
	}
	public static int  scc(int here) {
		int ret = discovered[here] = vertexCounter++;
		// ���ÿ� here�� �ִ´�. here�� �ļյ��� ��� ���ÿ��� here �Ŀ� ����.
		st.push(here);
		for(int i=0; i<adjList[here].size(); i++) {
			int there = adjList[here].get(i);
			// here -> there �� Ʈ������
			if(discovered[there] == -1) {
				ret = Math.min(ret, scc(there));
			}else if(sccId[there] == -1) { // there�� �����ؾ� �ϴ� ���� ������ �ƴ϶��
				ret = Math.min(ret, discovered[there]);
			}
		}
		
		// here���� �θ�� �ö󰡴� ������ ����� ���� Ȯ���Ѵ�.
		if(ret == discovered[here]) {
			// here�� ��Ʈ�� �ϴ� ����Ʈ���� ���� �մ� �������� ���� �ϳ��� ������Ʈ�� ���´�.
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
		// �迭 �ʱ�ȭ
		sccId   = discovered = new int[adjList.length];
		Arrays.fill(sccId, -1);
		Arrays.fill(discovered, -1);
		// ī���� �ʱ�ȭ
		sccCounter = vertexCounter = 0;
		// ��� ������ ���� scc() ȣ��
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
				// i�� ȸ�ǿ� j�� ȸ�ǰ� ���� ��ĥ���
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
