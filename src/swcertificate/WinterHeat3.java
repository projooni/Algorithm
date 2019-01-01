package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class WinterHeat3 {
	
	public static class Node{
		
		public int num;
		public List<Integer> edgeList;
		public int cnt;
		public int nDegree;
		public boolean isDel;
		
	}

	public static BigInteger minHeatTime = BigInteger.ZERO;


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int testCase=1; testCase<=T; testCase++){
			
			minHeatTime = BigInteger.ZERO;

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			Node[] nodeArr = new Node[N+1];
			nodeArr[0] = new Node();
			nodeArr[0].isDel = true;
			
			for( int i=1; i<=N; i++ ){
				nodeArr[i] = new Node();
				nodeArr[i].num = i;
				nodeArr[i].nDegree = Integer.parseInt(st.nextToken());
				nodeArr[i].isDel = false;
				nodeArr[i].edgeList = new ArrayList<Integer>();
			}
			
			
			for( int i=0; i<M; i++){
				st = new StringTokenizer(br.readLine());
				int startNode = Integer.parseInt(st.nextToken());
				int endNode = Integer.parseInt(st.nextToken());
				nodeArr[startNode].edgeList.add(endNode);
				nodeArr[endNode].edgeList.add(startNode);
			}
			
			Queue leafNodes = new LinkedList();
			
			for( int i=1; i<=N; i++){
				nodeArr[i].cnt = nodeArr[i].edgeList.size();
				if(nodeArr[i].cnt <= 1){
					leafNodes.offer(nodeArr[i]);
				}
			}
			
			
			while(!leafNodes.isEmpty()){
				
				Node leafNode = (Node)leafNodes.poll();
				
				if(leafNode.isDel){
					continue;
				}
				
				if(leafNode.cnt <= 0){
					minHeatTime = minHeatTime.add(BigInteger.valueOf(leafNode.nDegree));
					leafNode.isDel = true;
				}else if(leafNode.cnt == 1){
					int leafNodeDegree = leafNode.nDegree;
					
					Node nextNode = null;
					
					if(leafNode.cnt != leafNode.edgeList.size()){	
						for(int j=0; j<leafNode.edgeList.size(); j++){
							if(!nodeArr[leafNode.edgeList.get(j)].isDel){
								nextNode = nodeArr[leafNode.edgeList.get(j)];
							}else{
								continue;
							}
						}
						if(nextNode == null){
							minHeatTime = minHeatTime.add(BigInteger.valueOf(leafNode.nDegree));
							leafNode.isDel = true;
							continue;
						}
					}else{
						nextNode = nodeArr[leafNode.edgeList.get(0)];
					}
					
					nextNode.nDegree -= leafNode.nDegree;
					if(nextNode.nDegree <=0){
						nextNode.nDegree = 0;
						nextNode.isDel = true;
					}
					
					for(int j=0; j<nextNode.edgeList.size(); j++){
						
						Node anotherLeafNode = nodeArr[nextNode.edgeList.get(j)];
//						if(anotherLeafNode.isDel){
//							continue;
//						}
						
						anotherLeafNode.nDegree -= leafNodeDegree;
						if(anotherLeafNode.nDegree <= 0){
							anotherLeafNode.nDegree = 0;
							anotherLeafNode.isDel = true;
//							if(anotherLeafNode.cnt == 1){
								nextNode.cnt--;
//							}
							anotherLeafNode.cnt--;
						}
						
						
					}
					
					if(nextNode.cnt <= 1 && !nextNode.isDel){
						leafNodes.offer(nextNode);
					}
					
					minHeatTime = minHeatTime.add(BigInteger.valueOf(leafNodeDegree));
					
					
				}
				
				
				
				
			}
			

			System.out.println("#" + testCase + " " + minHeatTime);

			

		}


	}



}