package skeleton;

/*
 

● 정의

정렬된 리스트에서 특정한 값의 위치를 찾는 알고리즘

● 설명

처음 중간의 값을 임의의 값으로 선택하여, 그 값과 찾고자 하는 값의 크고 작음을 비교하는 방식. 처음 선택한 중앙값이 만약 찾는 값보다 크면 그 값은 새로운 최고값이 되며, 작으면 그 값은 새로운 최하값이 됨. 검색 원리상 정렬된 리스트에만 사용할 수 있다는 단점이 있지만, 검색이 반복될 때마다 목표값을 찾을 확률은 두 배가 되므로 속도가 빠름.

● 주의점

이진 탐색(Binary Search)은 기본 아이디어가 간단하지만 구현하기 까다로움.

Bentley는 벨 연구소나 IBM 에서 일하는 박사과정 학생들에게 두 시간을 주고 이진검색을 구현하라고 했을 때 90%가 버그 있는 코드를 제출했다고 함.

 (https://en.wikipedia.org/wiki/Binary_search_algorithm 의 Implementation issues 참조)

따라서, 정확하게 이해하여 구현해야 함.

예시 코드에서 중간 값을 계산할 때 m = (lb+ub)/2 로 할 경우 lb+ub의 값이 32bit int의 최대값인 (2^31 - 1) 보다 크다면 overflow가 나므로, m = lb+(ub-lb)/2 로 중간 값을 계산하여 이런 문제를 방지


  
 */

import java.util.Scanner;

class BinarySearch {

    static int N;
    static int M;
    static int arr[];

    static int binarySearch(int size, int key) {

        int lb = -1, ub = size-1, m;
        while(lb+1 < ub) {
            m = lb+(ub-lb)/2;
            if(arr[m] >= key) ub = m;
            else lb = m;
        }

        return ub>=size? -1 : arr[ub]==key? ub : -1 ;

    }

 

    public static void main(String arg[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case);
            N = sc.nextInt();
            M = sc.nextInt();
            arr = new int[N];
            for (int i = 0; i < N; i++) arr[i] = sc.nextInt();
            for (int i = 0; i < M; i++) {
                int key = sc.nextInt();
                System.out.print(" " + binarySearch(N, key));
            }
            System.out.println();

        }

         

        sc.close();

    }

}