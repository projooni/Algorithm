package skeleton;


public class FloydWarshall {  
	// 시간복잡도 : V^3

    static final int _M_size_ = 100; // max number of vertices  

    static final int _INTMAX_ = 2087654321; // infinite  

   

    static int AdjMatrix[][] = new int[_M_size_][_M_size_];  // Adjacent matrix  

    static int dist[][] = new int [_M_size_][_M_size_]; // Shortest distance matrix  

   

    static void floyd_warshall(int V)  

    {  

        // Copy graph information to dist array  

        for(int i = 1 ; i <= V ; i++)  

            for(int j = 1 ; j <= V ; j++)  

                if(dist[i][j]>0) dist[i][j] = AdjMatrix[i][j];  

                else dist[i][j] = _INTMAX_;  

           

     // Updates the shortest distance between i and j when passing through k node

        for(int k = 1 ; k <= V ; k++)  

        {  

            for(int i = 1 ; i <= V ; i++)  

            {  

                for(int j = 1; j <= V ; j++)  

                {  

                    if(dist[i][j] > dist[i][k]+dist[k][j])  

                    {  

                        dist[i][j] = dist[i][k]+dist[k][j];  

                    }  

                }  

            }  

        }  

    }  

}