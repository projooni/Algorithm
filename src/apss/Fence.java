package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Fence {
	
	public static int N;	// 1<= N <= 20000
	public static int height[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			height = new int[N];
			for(int i=0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			int result = getMaxArea(0,N-1);
			
			bw.flush();
			bw.write(result+"\n");
		}
		bw.close();

	}
	
	public static int getMaxArea(int left, int right) {
						
		if(left == right) {
			return height[left];
		}
		
		int mid = (right + left)/2;
		int ret = Math.max(getMaxArea(left,mid), getMaxArea(mid+1,right));
		
		int lo = mid, hi = mid+1;
		int midHeight = Math.min(height[lo], height[hi]);
		
		ret = Math.max(ret, midHeight*2);
		
		while(left < lo || hi < right) {
			if(hi < right && (lo == left || height[lo-1] < height[hi+1])) {
				hi++;
				midHeight  = Math.min(midHeight, height[hi]);
			}else {
				lo--;
				midHeight = Math.min(midHeight, height[lo]);
			}
			ret = Math.max(ret, midHeight * (hi-lo+1));
		}
		
		return ret;	
		
	}

}
