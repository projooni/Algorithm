// 시간복잡도 : O(|V||E|), E는 최대 V^2
package skeleton;


//BellmanFordMoore.java  

class BellmanFordMoore {  

 static final int _M_size_V_ = 100; // max number of vertices  

 static final int _M_size_E_ = 500; // max number of edges  

 static final int _INTMAX_ = 2087654321; // infinite  

    

 static int V, E; // V:��������  E:��������  

    

 // ����ǥ : [][0] ������, [][1] ������, [][2] cost  

 static int Edge[][] = new int[_M_size_E_][3];  

    

 // store the least cost of the vertices  

 static int visited[] = new int[_M_size_V_];  

 // previous vertex in path  

 static int predecessor[] = new int[_M_size_V_];  



 // return value  

 // true  : ���� weight ����Ŭ�� �������� �ʾ� ���� �����  

 // false : ���� weight ����Ŭ ����  

 static boolean bellmanfordmoore(int start) {  

     for (int i = 0; i < _M_size_V_; i++) {  

         visited[i] = _INTMAX_; // visited init  

         predecessor[i] = -1;  

     }  

     visited[start] = 0;  



     for (int count = 1; count < V; count++) {  

         for (int j = 0; j < E; j++) {  

           if (visited[Edge[j][0]] != _INTMAX_ &&

              visited[Edge[j][1]] > visited[Edge[j][0]] + Edge[j][2]) {

                 visited[Edge[j][1]] = visited[Edge[j][0]] + Edge[j][2];

                 predecessor[Edge[j][1]] = Edge[j][0];  

           }  

         }  

     }  



     // verification  

     for (int j = 0; j < E; j++) {  

         if (visited[Edge[j][1]] > visited[Edge[j][0]] + Edge[j][2]) {  

             return false;  

         }  

     }  

     return true;  

 }  

}