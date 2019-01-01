package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Picnic {
	
	public static int N,M;
	public static boolean friend[][];
	public static boolean align[];
	public static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D:///sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			align = new boolean[N];
			friend = new boolean[N][N];
			count = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++){
				int one = Integer.parseInt(st.nextToken());
				int another = Integer.parseInt(st.nextToken());
				friend[one][another] = true;
				friend[another][one] = true;
			}
			
			count = findFriend();
			
			bw.flush();
			bw.write(count + "\n");
			
			
		}
		bw.close();

	}
	
	public static int findFriend(){
		int ret = 0;
		
		int firstFree = -1;
		for(int i=0; i<N; i++){
			if(!align[i]){
				firstFree = i;
				break;
			}
		}
		
		// 남은애가 없을때		
		if(firstFree == -1){
			return 1;
		}
			
		for(int j=firstFree+1; j<N; j++){
			
			// 둘다 할당이 안되어있고, 친구일 경우
			if(!align[j] && friend[firstFree][j]){
				align[firstFree] = align[j] = true;
				ret += findFriend();
//				count +=  findFriend();
				align[firstFree] = align[j] = false;
			}
		}
		
		return ret;
		
	}

}
