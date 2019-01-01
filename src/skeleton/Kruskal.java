package skeleton;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;  

public class Kruskal {  
	
	/*
	1. 비용을 기준으로 간선을 적은 것부터 큰 것 순으로 정렬한다.
	2. 적은 비용의 간선부터 하나씩 그린다. (사이클을 만들게 되는 간선은 그리지 않는다.)
	3. 모든 정점이 선으로 연결될 때까지 2의 과정을 계속한다.
	[네이버 지식백과] 최소 비용 신장 트리 알고리즘 - 가장 적은 비용으로 연결하려면? (소프트웨어 알고리즘)
	 */
	
	/*
	 * 최소 신장 트리가 완성되었는지 확인하는 방법
	 * 간선의 수 + 1 = 정점의 수
	 * */

    static final int _V_size_ = 10000; // max number of vertices  

    static final int _E_size_ = 100000; // max number of edges  

    static int Set[] = new int[_V_size_ + 1]; // start with 1. index 0 is not used.  

    static int edge[][] = new int[_E_size_][3]; // [0] : from vertex, [1] : to vertex, [2] : edge cost     

   

    static int group(int n) {  

        if (Set[n] == 0) return n;  

        return Set[n] = group(Set[n]); // path compression  

    }  

   

    static void join(int a, int b) {  

        int A = group(a), B = group(b);  

        if (A != B) Set[A] = B;  

    }  

   

    static class Comp implements Comparator<int[]>  

    {  

        @Override 

        public int compare(int[] arg0, int[] arg1) {  

            // TODO Auto-generated method stub  

            return arg0[2] - arg1[2];  

        }  

    }  

       

    static int kruskal(int V, int E)  

    {  

        int mst_cost = 0;  

        int selected = 0;  

        // Initialize disjoint set  

        for(int i = 1 ; i <= V ; i++) Set[i] = 0;  

           

        Arrays.sort(edge, 0, E, new Comp());  

           

        for(int i = 0 ; i < E ; i++)  

        {  

        	// 사이클 확인. from 정점과 to 정점이 같은 group이면 사이클이다.
            if(group(edge[i][0]) != group(edge[i][1]))  

            {  

                //mst_cost += edge[i].cost;  

                mst_cost += (long)edge[i][2];  

                selected++;  

                join(edge[i][0], edge[i][1]);  

            }  

        }  


        // 선택된 간선들의 수가 정점의 수보다 1적으면 최소신장트리 완성.
        if(selected == V-1) return mst_cost; // return mst cost  

        else return -1; // can't make mst  

    }  

   

    public static void main(String[] args) throws Exception  

    {  

        Scanner sc = new Scanner(System.in);  

        int V = sc.nextInt();  

        int E = sc.nextInt();  

           

        for(int i = 0 ; i < E ; i++)  

        {  

            edge[i][0] = sc.nextInt();  

            edge[i][1] = sc.nextInt();  

            edge[i][2] = sc.nextInt();  

        }  

        System.out.println(kruskal(V,E));  

    }  

}