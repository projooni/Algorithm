package skeleton;

class UnionFind {

// max number of elements  
	static final int N = 1000;

	// starts with 1, index 0 is not used.
	static int p[] = new int[N + 1];
	
	public static void init() {
		for(int i=1; i<=N; i++) {
			p[i] = i;
		}
	}

	public static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if (A != B) {
			p[A] = B;
		}
	}

	public static int find(int n) {
		if (p[n] == n) {
			return n;
		}
		return p[n] = find(p[n]);
	}

}