package skeleton;


import java.io.IOException;

import java.util.Scanner;

 

public class Lca {

 

    static int MAX = 64;

    static int MD = 6;

    static int[][] p = new int[MAX][MD];

    static int[] d = new int[MAX];

    static int[] visited = new int[MAX];

     

    static void dfs(int here, int depth) {

        if (here >= MAX) return;

        visited[here] = 1;

        d[here] = depth;

        for (int i = 2 * here; i < 2 * (here + 1); i++) {

            if (i >= MAX) break;

            if (visited[i] == 1) continue;

            p[i][0] = here;

            dfs(i, depth + 1);

        }

    }

 

    static void aces_find() {

        for (int j = 1; j < MD; j++) {

            for (int i = 1; i < MAX; i++) {

                p[i][j] = p[p[i][j - 1]][j - 1];

            }

        }

    }

 

    static int lca(int x, int y) {

        if (d[x] > d[y]) {

            int temp = d[x];

            d[x] = d[y];

            d[y] = temp;

        }

 

        for (int i = MD-1; i >= 0; i--) {

            if (d[y] - d[x] >= (1 << i))

                y = p[y][i];

        }

        if (x == y) return x;

 

        for (int i = MD-1; i >= 0; i--) {

            if (p[x][i] != p[y][i]) {

                x = p[x][i];

                y = p[y][i];

            }

        }

        return p[x][0];

 

    }

     

    public static void main(String[] args) throws NumberFormatException, IOException {

        dfs(1, 0);

        aces_find();

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 0 ; i < N; i++){

            int s = sc.nextInt();

            int e = sc.nextInt();

            System.out.println(lca(s,e));

        }

    }

}
