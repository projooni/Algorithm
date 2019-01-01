package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class PreTest_201701 {
	
	public static int N;		// 동기 수
	public static int l;		// binary search left 좌표
	public static int r;		// binary search right 좌표
	public static int m;		// binary search middle 좌표
	public static Pair[] pair;	// 동기들의 좌표와 속도 인스턴스
	public static int minX;		// 결과 값 - 최소 시간
	
	public static class Pair{
		public int X;
		public int V;
		public Pair() {
		}
		public Pair(int x, int v) {
			X = x;
			V = v;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("E:\\sample_input_2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++){
			
			
			N = Integer.parseInt(br.readLine().trim());
			pair = new Pair[N];
			
			// 동기들 좌표 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				pair[i] = new Pair();
				pair[i].X = Integer.parseInt(st.nextToken());
			}
			
			// 동기들 속도 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++){
				pair[i].V = Integer.parseInt(st.nextToken());
			}
			
			// binary search를 위한 sorting
			Arrays.sort(pair,new Comparator<Pair>(){
				@Override
				public int compare(Pair p1, Pair p2) {
					// TODO Auto-generated method stub
					return p1.X - p2.X;
				}
			});
			
			// left에는 0번 인덱스를, right에는 마지막 인덱스의 좌표를 넣는다.
			l = pair[0].X;
			r = pair[pair.length-1].X;
			
			// middle 좌표에 도달하는 최대 시간과, 양 옆의 좌표에 도달하는 최대 시간을 저장할 장소
			double ml_t = Double.MAX_VALUE,
				   mr_t = Double.MAX_VALUE,
				   m_t = Double.MAX_VALUE;
			
			// 결과값 변수 초기화
			minX = -1;
			// middle 값이 양 옆의 좌표를 확인할 필요없이 명확한 답일 경우
			boolean isSearch = false;
			
			// binary search 시작
			// l<=r 이 아님에 주의
			while(l < r) {
				
	            m = l+(r - l)/2;
	            
	            // 좌표범위를 벗어나지 않는 경우에만 계산
	            if((m-1) >= -1000000000 ){
	            	ml_t = calTime(m-1);
	            }
	            
	            // 좌표범위를 벗어나지 않는 경우에만 계산
	            if((m+1) <= 1000000000 ){
	            	mr_t = calTime(m+1);
	            }
	            
	            m_t = calTime(m);
	            
	            double min_t = ml_t;
	            min_t = mr_t < min_t ? mr_t : min_t;
	            min_t = m_t < min_t ? m_t : min_t;
	            
	            if(min_t == m_t){ // 세 좌표중 중앙값이 최소일 경우
	            	if(m_t != ml_t){ // 왼쪽 값과 동일하지 않을 경우 -> 명확하게 중간값이 답
	            		minX = m;
		            	isSearch = true;
		            	break;
	            	}else{ // 왼쪽 값과 동일한 경우 -> 최소 좌표가 답이 되므로 한번 더 탐색한다.
	            		r = m-1;
	            	}
	            	
	            }else if(min_t == mr_t){ // 세 좌표중 오른쪽 값이 최소일 경우
	            	l = m+1;
	            }else{ // 세 좌표중 왼쪽 값이 최소일 경우
	            	r = m-1;
	            	
	            }
	            
	        }
			
			if(!isSearch){
					minX = l;
			}
			
			
			bw.write("#" + tc + " " + minX + "\n");
			bw.flush();
			
		}
		
		bw.close();

	}
	
	// x 좌표에 모두가 도착하는 최소 시간을 리턴한다.
	public static double calTime(int x){
		
		double max = 0;
		for(int i=0; i<pair.length; i++){
			double iTime = (double) Math.abs(pair[i].X - x) / pair[i].V;
			max = iTime > max ? iTime : max;
		}
		
		return max;
		
		
	}

}
