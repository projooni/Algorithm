package skeleton;

import java.util.Scanner;

 

class ParametricSearch {

    static int N;
    static int M;
    static int MAXSIZE = 100000000;
    static int Video[];

    static boolean fmin(int size) {

        int usbCount = 1;
        int usbSize = 0;
        
        for(int i = 0; i < N; i++) {
            if(usbSize + Video[i] > size) {
                usbCount++;
                usbSize = Video[i];
                if(usbSize > size || usbCount > M) return false;
            }
            else usbSize += Video[i];
        }

        return true;

    }

     

    public static void main(String arg[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) 
        {
            N = sc.nextInt();
            M = sc.nextInt();

            Video = new int[N];
            for (int i = 0; i < N; i++) Video[i] = sc.nextInt();
            int lb=-1, ub = MAXSIZE, m;
            while(lb+1 < ub) {
                m = lb+(ub-lb)/2;
                if(fmin(m)) ub = m;
                else lb = m;
            }

            System.out.println("#" + test_case + " "+ ub);

        }

        sc.close();

    }

}
