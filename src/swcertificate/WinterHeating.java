package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class WinterHeating {
	
	public static int minHeatTime = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] nodeValArr = new int[N+1];
			nodeValArr[0] = -1;
			for( int i=1; i<=N; i++ ){
				nodeValArr[i] = Integer.parseInt(st.nextToken());
			}
			
			String[] edgeArr = new String[M];
			int[] edgeCntArr = new int[N+1];
			Arrays.fill(edgeCntArr, 0);
			for( int i=0; i<M; i++ ){
				st = new StringTokenizer(br.readLine());
				String startNode = st.nextToken();
				String endNode = st.nextToken();
				edgeArr[i] = startNode + "," + endNode;
				
				// node �� Edge ���� ī�����Ѵ�. => 1�� ���ϸ� ���� �ٱ� ���
				edgeCntArr[Integer.parseInt(startNode)]++;
				edgeCntArr[Integer.parseInt(endNode)]++;
				
			}
			
			minHeatTime = 0;
			doProcess(nodeValArr, edgeArr, edgeCntArr);
			
			
			System.out.println("#" + testCase + " " + minHeatTime);
			
			
		}

	}
	
	public static void doProcess(int[] nodeValArr, String[] edgeArr, int[] edgeCntArr){
		// �Է� : ���� ��� ����Ʈ, ���� ����(edge) ����
		// ��� : �ּҷ� heating �ϴ� �ð�
		
		// edgeCntArr�� �ֿܰ� ������ �˾Ƴ���.
		List<Integer> outestNodeList = new ArrayList<Integer>();
		for(int i=1; i<edgeCntArr.length; i++){
			if(edgeCntArr[i] <= 1 && nodeValArr[i] > 0){ // ������ �ϳ��ۿ� ������� ���� �ֿܰ� ���
				if(edgeCntArr[i] == 0){
					minHeatTime += nodeValArr[i];
					nodeValArr[i] = 0;
				}else{
					outestNodeList.add(i);
				}
			}
		}
		
		if(outestNodeList == null || outestNodeList.isEmpty()){
			return;
		}
		// �ֿܰ� ������ ���鼭 1 ���� ������ �� �����Ѵ�
		for(int i=0; i<outestNodeList.size(); i++){
			
			if(edgeCntArr[outestNodeList.get(i)] <= 0){
				continue;
			}
			
			// �ֿܰ� ���� ����� ��带 ã�´�.
			int outestNode = outestNodeList.get(i);	// �ֿܰ� ���
			int nextOutestNode = 0;
			for(int j=0; j<edgeArr.length; j++){
				String[] nodeNumArr = edgeArr[j].split(",");
				int startNode = Integer.parseInt(nodeNumArr[0]);
				int endNode = Integer.parseInt(nodeNumArr[1]);
				
				if(outestNode == startNode){
					nextOutestNode = endNode;
				}else if(outestNode == endNode){
					nextOutestNode = startNode;
				}else{
					continue;
				}
				
			}
			
			// nextNode�� ����� �ֿܰ� ����� ���� ū ���� ã�´�.
			int max = 0;
			for(int j=0; j<edgeArr.length; j++){
				String[] nodeNumArr = edgeArr[j].split(",");
				int startNode = Integer.parseInt(nodeNumArr[0]);
				int endNode = Integer.parseInt(nodeNumArr[1]);
				
				if(nextOutestNode == startNode){
//					nodeValArr[endNode] -= nodeValArr[outestNode];
					if(edgeCntArr[endNode] <= 1){
						max = max < nodeValArr[endNode] ? nodeValArr[endNode] : max;
					}
				}else if(nextOutestNode == endNode){
//					nodeValArr[startNode] -= nodeValArr[outestNode];
					if(edgeCntArr[startNode] <= 1){
						max = max < nodeValArr[startNode] ? nodeValArr[startNode] : max;
					}
					
				}else{
					continue;
				}
			}
			
			// max���� ����..
			for(int j=0; j<edgeArr.length; j++){
				String[] nodeNumArr = edgeArr[j].split(",");
				int startNode = Integer.parseInt(nodeNumArr[0]);
				int endNode = Integer.parseInt(nodeNumArr[1]);
				
				if(nextOutestNode == startNode){
					nodeValArr[endNode] -= max;
					if(nodeValArr[endNode] <= 0){
						nodeValArr[endNode] = -1;
					}
					edgeCntArr[endNode] = 0;
					edgeCntArr[nextOutestNode]--;
				}else if(nextOutestNode == endNode){
					nodeValArr[startNode] -= max;
					if(nodeValArr[startNode] <= 0){
						nodeValArr[startNode] = -1;
					}
					edgeCntArr[startNode] = 0;
					edgeCntArr[nextOutestNode]--;
				}else{
					continue;
				}
			}
			nodeValArr[nextOutestNode] -= max;
			if(nodeValArr[nextOutestNode] < 0){
				nodeValArr[nextOutestNode] = -1;
			}
			
			minHeatTime += max;
			
		}
		
		
		doProcess(nodeValArr, edgeArr, edgeCntArr);
		
		
		
		
		
	}

}
