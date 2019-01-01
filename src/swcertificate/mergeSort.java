package swcertificate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class mergeSort {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		
		System.setIn(new FileInputStream("D:\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++){
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		int [] outArr = recursiveMerge(arr);
		for(int i=0; i<outArr.length; i++){
			System.out.println(outArr[i]);
		}
		
			

	}
	
	public static int[] recursiveMerge(int[] inputArr){
		int size = inputArr.length;
		if(size <= 1){
			return inputArr;
		}
		
		int[] dividedArr1 = new int[size/2];
		int[] dividedArr2 = new int[size - (size/2)];
		
		for(int i=0; i<dividedArr1.length; i++){
			dividedArr1[i] = inputArr[i];
		}
		
		for(int i=0; i<dividedArr2.length; i++){
			dividedArr2[i] = inputArr[size/2 + i];
		}
		
		
		int[] resultArr1 = recursiveMerge(dividedArr1);
		int[] resultArr2 = recursiveMerge(dividedArr2);
		
		int[] returnArr = new int[resultArr1.length + resultArr2.length];

		int k = 0;
		int i = 0;
		int j = 0;
		while(k < (resultArr1.length+resultArr2.length)){
			
			if( ( i< resultArr1.length) && ((j >= resultArr2.length) || (resultArr1[i] < resultArr2[j])) ){
				returnArr[k] = resultArr1[i];
				i++;
			}else if(( j < resultArr2.length) && ((i >= resultArr1.length) || (resultArr1[i] >= resultArr2[j])) ){
				returnArr[k] = resultArr2[j];
				j++;
			}
			
			k++;
		}
		
		
		return returnArr;	
		
	}

}
