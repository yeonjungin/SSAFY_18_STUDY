package week03;
import java.io.*;
import java.util.*;

//17281. (야구공)
public class Main_17281_baseball {
	static int N;
	static int p = 9;
	static int cnt = 0;
	static int cntOut = 0;
	static int max = 0;
	static int [][] result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = new int [N][p];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < p; j++)
				result[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int [] arr = {1, 2, 3, 4, 5, 6, 7, 8};
		
		perm(arr, 0);
		System.out.println(max);
	}
	
	static void game(int [] output) {		
		int [] arr = new int[9];
		int j=0;
		for(int i = 0; i < p; i++)
			if(i!=3)
				arr[i] = output[j++];
		
		int turn = 0;
		int score = 0;
		for (int i = 0; i < N; i++) {
			int out = 0;
			ArrayList<Integer> list = new ArrayList<>();
			while (out < 3) {
				int player = arr[turn++ % 9];
				int state = result[i][player];
				if (state == 0) out++;
				else list.add(state);
			}
			int sum = 0;
			for (int k = list.size() -1; k >= 0; k--) {
				sum += list.get(k);
				if(sum >= 4) {
					score += k+1;
					break;
				}
			}
		}
		max = Math.max(max, score);
	}
	
	public static void perm(int[] arr, int pivot) {
		if (pivot == arr.length) {
			game(arr);
			return;
		}
		for (int i = pivot; i < arr.length; i++) {
			swap(arr, i, pivot);
			perm(arr, pivot + 1);
			swap(arr, i, pivot);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}