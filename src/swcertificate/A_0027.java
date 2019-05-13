package swcertificate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class A_0027 {
	
	public static int N, L, H;
	public static double[][] drone;
	public static List<double[]> _4, _3, _2, _1;
	public static int[] result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<=T; testCase++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			_4 = new ArrayList<double[]>();
			_3 = new ArrayList<double[]>();
			_2 = new ArrayList<double[]>();
			_1 = new ArrayList<double[]>();
			
			drone = new double[N+1][3];
			result = new int[N+1];
			
			for(int i=1; i<N+1; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int x = Integer.parseInt(st.nextToken());
				int y =  Integer.parseInt(st.nextToken());
				drone[i][0] = x;
				drone[i][1] = y;
				drone[i][2] = i;
			}
			
			getDrone();
			
			int index = 1;
			double pre  = 0;
			boolean lastSame = false;
			
			for(int i=0; i<_4.size(); i++) {
				double a = _4.get(i)[0] == 0  ? Double.MAX_VALUE : _4.get(i)[1] / _4.get(i)[0];
				if(index != 1) {
					if(pre == a) {
						lastSame = true;
					}else {
						lastSame = false;
					}
				}
				pre = a;
				result[(int)_4.get(i)[2]] = lastSame ?  result[(int)_4.get(i-1)[2]] : index;
				index++;
			}
			
			for(int i=0; i<_3.size(); i++) {
				double a = _3.get(i)[0] == 0  ? Double.MAX_VALUE : _3.get(i)[1] / _3.get(i)[0];
				if(index != 1) {
					if(pre == a) {
						lastSame = true;
					}else {
						lastSame = false;
					}
				}
				pre = a;
				result[(int)_3.get(i)[2]] = lastSame ?  result[(int)_3.get(i-1)[2]] : index;
				index++;
			}
			
			for(int i=0; i<_2.size(); i++) {
				double a = _2.get(i)[0] == 0  ? Double.MAX_VALUE : _2.get(i)[1] / _2.get(i)[0];
				if(index != 1) {
					if(pre == a) {
						lastSame = true;
					}else {
						lastSame = false;
					}
				}
				pre = a;
				result[(int)_2.get(i)[2]] = lastSame ?  result[(int)_2.get(i-1)[2]] : index;
				index++;
			}
			
			for(int i=0; i<_1.size(); i++) {
				double a = _1.get(i)[0] == 0  ? Double.MAX_VALUE : _1.get(i)[1] / _1.get(i)[0];
				if(index != 1) {
					if(pre == a) {
						lastSame = true;
					}else {
						lastSame = false;
					}
				}
				pre = a;
				result[(int)_1.get(i)[2]] = lastSame ?  result[(int)_1.get(i-1)[2]] : index;
				index++;
			}
			
			bw.flush();
			bw.write("#" + testCase);
			for(int i=1; i<result.length; i++) {
				bw.write(" " + result[i]);
			}
			bw.write( "\n");
			
		}
		
		bw.close();
		

	}
	
	public static void getDrone() {
		
		for(int i=1; i<drone.length; i++) {
			double x = drone[i][0];
			double y = drone[i][1];
			
			if(!innerCircle(x, y)) {
				continue;
			}
			
			if(x >= 0 && y <= 0) {
				// 4사분면			
				_4.add(drone[i]);				
			}else if(x < 0 && y < 0) {
				// 3사분면
				_3.add(drone[i]);
			}else if(x < 0 && y >= 0) {
				// 2사분면
				_2.add(drone[i]);
			}else {
				// 1사분면
				_1.add(drone[i]);
			}
			
		}
		
		Comparator comp = new Comparator<double[]>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				// TODO Auto-generated method stub
				double a1 = Math.abs(o1[0] == 0  ? Double.MAX_VALUE : o1[1] / o1[0]);
				double a2 = Math.abs(o2[0] == 0  ? Double.MAX_VALUE : o2[1] / o2[0]);
		
				return (a1 <= a2) ?  -1 : 1;
			}
			
		};
		
		Comparator compDesc = new Comparator<double[]>() {

			@Override
			public int compare(double[] o1, double[] o2) {
				// TODO Auto-generated method stub
				double a1 = Math.abs(o1[0] == 0  ? Double.MAX_VALUE : o1[1] / o1[0]);
				double a2 = Math.abs(o2[0] == 0  ? Double.MAX_VALUE : o2[1] / o2[0]);
		
				return (a1 <= a2) ?  1 : -1;
			}
			
		};
		
		_4.sort(comp);
		_3.sort(compDesc);
		_2.sort(comp);
		_1.sort(compDesc);
		
		return;
		
	}
	
	
	public static boolean innerCircle(double x, double y) {
		boolean ret = false;
		
		int distanceDrone = (int) (Math.pow(x,  2) + Math.pow(y, 2));
		if(distanceDrone <= L*L && distanceDrone >= H*H) {
			ret = true;			
		}
		
		return ret;
		
	}

}
