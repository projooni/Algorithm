package swcertificate_practical_solving_class_20201019;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 
 *  < ��������Ǯ�̹� (2020.10.19 ~ 2020.10.23) 5���� >
 *  
 * ������    : [����P-0067] ���� ū �ִ� ���
 * ���̵�    : 
 * ����       : DP
 * ���俩��  : X (Ǫ����)
 * �⿩��    :  0%
 * ��Ÿ			: 
 *   
 * */

public class Pretest_P_0067 {
	
	public static int N;
	public static int arr[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			
			
			bw.flush();
			bw.write("#" + tc);
		}
		bw.close();

	}

}
