package skeleton;

//UnionFind.java  

class UnionFind {  

// 시간복잡도 : 상수라 해도 무방
/*
실제 시간 복잡도는 O(α(N))이라고 한다. α는 애커만 함수라고 하는데 N이  2^65536일때 애커만 함수 값이 5가 된다.
따라서 그냥 상수라고 봐도 무방하다.
출처: http://www.crocus.co.kr/683 [Crocus]
*/
// max number of elements  
static final int _M_size_ = 1000;  

 // starts with 1, index 0 is not used.  
static int Set[] = new int[_M_size_ + 1];  

 static int group(int n) {  
     if(Set[n] == 0) return n;  
     return Set[n] = group(Set[n]); // path compression  
 }  

 static void join(int a, int b) {  
     int A = group(a), B = group(b);  
     if (A != B) Set[A] = B;  
 }

}