package apss;



import java.util.ArrayList;

import java.util.List;



public class Code30_4 {

	

	public static class Pair{

		

		public int linkedNode;

		public int weight;

		public Pair(int linkedNode, int weight) {

			this.linkedNode = linkedNode;

			this.weight = weight;

		}

		

	}

	

	// 정점의 수

	public static int V;	

	// 각 정점에서의 간선 리스트

	public static List<Pair>[] adj;



	public static void main(String[] args) {

		// TODO Auto-generated method stub

		

		for(int i=0; i<V; i++){

			// 각 정점에 연결된 간선을 전부 add한다.

			adj[i] = new ArrayList<Pair>();

			int n = 2;	// 연결딘 노드

			int w = 10;	// 가중치

			Pair pair = new Pair(n, w);

			

			//TODO 각 노드에 간선들 추가

			

		}

		

		List<Integer> result = bellmanFord(0);

		



	}

	

	public static List<Integer> bellmanFord(int src){

		

		// 시작점을 제외한 모든 정점가지의 최단 거리 상한을 INF로 둔다.

		List<Integer> upper = new ArrayList<Integer>();

		for(int i=0; i<V; i++){

			upper.add(Integer.MAX_VALUE);

		}

		upper.set(src, 0);

		boolean updated = false;

		

		// V번 순회한다.

		for(int i=0; i<V; i++){

			updated = false;

			for(int here=0; here<V; here++){

				for(int j=0; j<adj[here].size(); j++){

					int there = adj[here].get(j).linkedNode;

					int cost = adj[here].get(j).weight;

					

					// (here, there) 간선을 따라 완화를 시도한다.

					if(upper.get(there) > upper.get(here) + cost){

						// 성공

						upper.set(there, upper.get(here) + cost);

						updated = true;

					}

				}

				

				// 모든 간선에 대해 완화가 실패했을 경우 V-1번도 돌 필요 없이 곧장 종료.

				if(!updated) break;

			}

			

			

		}

		

		// V번째 순회에서도 완화가 성공했다면 음수 사이클이 있다.

		if(updated) upper.clear();

		return upper;

		

	}



}

