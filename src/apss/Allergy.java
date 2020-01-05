package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Allergy {
	
	public static int N,M;
	public static Map<String, Integer> friends;
	public static int eatableCnt[];
	public static List<Integer> eatable[];
	public static List<Integer> canEat[];
	public static int eaten[];
	public static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("/Users/projooni/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			eaten = new int[N];
			friends = new HashMap<String, Integer>();
			result = Integer.MAX_VALUE;
			eatable = new ArrayList[M];
			canEat = new ArrayList[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				friends.put(st.nextToken(), i);
				canEat[i] = new ArrayList();
			}
			
			eatableCnt = new int[M];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				eatableCnt[i] = Integer.parseInt(st.nextToken());
				eatable[i] = new ArrayList();
				for(int j=0; j<eatableCnt[i]; j++) {
					int friendNumber = friends.get(st.nextToken());
					eatable[i].add(friendNumber);
					canEat[friendNumber].add(i);
				}
			}
			
			search(0,0);
 			
			bw.flush();
			bw.write(result + "\n");
			
		}
		bw.close();

	}
	
	public static void search(int foodCount, int idx) {
		
		if(foodCount >= result) {
			return;
		}
		
		int first  = 0;
		while(first < N && eaten[first] > 0) {
			++first;
		}
		
		// 모든 친구가 먹을 음식이 있는 경우 종료
		if(first == N) {
			result = Math.min(result,foodCount);
			return;
		}
		
		for(int i=0; i<canEat[first].size(); i++) {
			
			int food = canEat[first].get(i);
			
			List<Integer> eatableList = eatable[food];
			
			for(int j=0; j<eatableList.size(); j++) {
				eaten[eatableList.get(j)]++;
			}
			
			search(foodCount+1, idx+1);
			
			for(int j=0; j<eatableList.size(); j++) {
				eaten[eatableList.get(j)]--;
			}
			
		}
		
	}

}
