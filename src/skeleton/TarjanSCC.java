package skeleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class TarjanSCC {
	
	public static ArrayList<Integer> adjList[];
	public static int sccId[];
	public static int discovered[];
	public static int sccCounter, vertexCounter;
	public static Stack<Integer> st;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int label[] = tarjanSCC();

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

}
