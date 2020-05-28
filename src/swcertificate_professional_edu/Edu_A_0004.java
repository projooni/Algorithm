package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * ���� A-0004 �ݱ�
 * ���� �˰��� : ��Ŭ���� ȣ����
 * ���� �Ȱ�..
 * */
public class Edu_A_0004 {

	public static int N,M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// �ϴ� ���м��� ������ �Ѵ�.
			// ���м��� �ƴ� ���, �̹� �߷��ִ� �κп� ���� ó���� �ȵȴ�.
			// ������� 2/4�� ��� | | | | |
			int g = gcd(N,M);
			
			// ���������� ���������� ���� ��
			int cut = M/g;
			
			// ���м��� �������Ƿ� �ٽ� ������� ���ؼ� ���󺹱�
			// cut-1 : ���������� ���� �� - 1 �� ���� cut �� �̴�.
			int ans = g*(cut-1);
			
			bw.flush();
			bw.write("#" + tc + " " + ans + "\n");
		}
		bw.close();

	}
	
	public static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a%b);
	}

}
