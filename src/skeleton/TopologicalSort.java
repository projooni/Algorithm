package skeleton;


import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.ArrayList;

 

public class TopologicalSort {

    static int MAX = 101; 

     

    static int[] visited = new int[MAX];

    static int[] inputEdgeCount = new int[MAX];

    static ArrayList<Integer> ordered = new ArrayList<Integer>();

    static ArrayList<Integer>[] adj = new ArrayList[MAX];

 

    public static void main (String[] args) throws IOException {

        for(int i = 0 ; i < MAX ; i++){

            adj[i] = new ArrayList<Integer>();

        }

         

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line[] = br.readLine().split(" ");

        int N = Integer.valueOf(line[0]);

        int M = Integer.valueOf(line[1]);

         

        for (int i = 0 ; i < M ; i++){

            line = br.readLine().split(" ");

            int s = Integer.valueOf(line[0]);

            int e = Integer.valueOf(line[1]);

            adj[s].add(e);

            inputEdgeCount[e]++;

        }

         

        ArrayList<Integer> result = topologicalSort(N);

         

        for (int i = 0 ; i < result.size() ; i++){

            System.out.print(result.get(i) + " ");

        }

        System.out.println();

    }

 

    private static ArrayList<Integer> topologicalSort(int N) {

        ArrayList<Integer> startNode = new ArrayList<Integer>();

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 1; i <= N; i++) {

            visited[i] = 0;

            if (inputEdgeCount[i] == 0) startNode.add(i);

        }

        ordered.clear();

 

        for (int i = 0; i < startNode.size(); i++) {

            if (visited[startNode.get(i)] == 0) dfs(startNode.get(i));

        }

         

        for (int i = ordered.size()-1; i >=0 ; i--){

            result.add(ordered.get(i));

        }

 

        return result;

    }

 

    static void dfs(int u) {

        visited[u] = 1;

        for (int v = 0; v < adj[u].size(); v++) {

            if (visited[adj[u].get(v)]== 0 ) dfs(adj[u].get(v));

        }

        ordered.add(u);

    }

}
