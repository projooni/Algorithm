package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import static java.lang.Integer.parseInt;
      
public class Prac_A_0013_other_solution{
          
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = parseInt(br.readLine().trim());
        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringTokenizer st2;
            int K = Integer.parseInt(st.nextToken()); 
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
              
            int[] arr = new int[K]; // 소들이 처음에 있는 곳
              
            for(int i = 0; i < K; ++i){
                arr[i] = Integer.parseInt(br.readLine());
            }
             
            ArrayList<Integer>[] graph = new ArrayList[N+1];
            for(int i = 1; i <= N; i++){
                graph[i] = new ArrayList<>();
            }
             
            for(int i = 0; i < M; ++i){
                st2 = new StringTokenizer(br.readLine());
                int fr = parseInt(st2.nextToken());
                int to = parseInt(st2.nextToken());
                graph[fr].add(to);
            }
             
            // bfs
            boolean[] visit = new boolean[N+1];
            int[] cnt = new int[N+1];
            LinkedList<Integer> qu = new LinkedList<>();
            for(int i = 0; i < K; ++i){
                qu.clear();
                for(int j = 1; j <= N; ++j) visit[j] = false;
                qu.push(arr[i]);
                visit[arr[i]] = true;
                while (!qu.isEmpty()) {
                    int l = qu.pop();
                    cnt[l]++;
                    for(int next: graph[l]){
                        if(!visit[next]){
                            qu.add(next);
                            visit[next] = true;
                        }
                    }
                }
            }
             
            int ans = 0;
            for(int i = 1; i <= N; ++i){
                if(cnt[i] == K) {
                    ans++;
                }
            }
  
                      
            System.out.printf("#%d %d\n",t,ans);
                
        }
    }
}

