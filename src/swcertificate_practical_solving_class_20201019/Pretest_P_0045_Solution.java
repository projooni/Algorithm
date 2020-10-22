package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 
 *  < ��������Ǯ�̹� (2020.10.19 ~ 2020.10.23) 4���� >
 *  
 * ������    : [����P-0045] �˹���
 * ���̵�    : �߻�
 * ����       : DFS(������)
 * ���俩�� : O
 * �⿩��    : 0% (���߾� �ҽ�)
 * ��Ÿ
 *   
 * */

public class Pretest_P_0045_Solution {
    static int T, N, M, order;
    static long answ;
    static int visited[];
    static ArrayList<Integer> list[];
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            
            visited = new int[N+1];            //order ����
            list = new ArrayList[N+1];
            order = 0; answ = -1;
            
            for(int i=0; i<=N; i++) {
                list[i] = new ArrayList<>();
            }
            
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                list[a].add(b);
                list[b].add(a);
            }
            
            for(int i=1; i<=N; i++) {
                if(visited[i] == 0)
                    dfs(i,0);
            }

            System.out.println("#"+t+" "+ answ);
        }
    }
    

    private static int dfs(int cur, int parent) {
        visited[cur] = ++order; 
        int ret = visited[cur];
        
        for(int i=0; i<list[cur].size(); i++) {
            int next = list[cur].get(i);
            
            if(next == parent)
                continue;

            if(visited[next] == 0) {
                int tmp = dfs(next, cur);
                if(tmp > visited[cur]) {
                    //������ Ȯ��
                    long tmpcnt = order-tmp+1;
                    answ = Math.max(answ, tmpcnt*(N-tmpcnt)*2);
                }
                ret = Math.min(ret, tmp);
            } else {
                ret = Math.min(ret, visited[next]);
            }
        }
        return ret;
    }

}
