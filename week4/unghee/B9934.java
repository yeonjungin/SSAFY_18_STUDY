package study._220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B9934 {
	static ArrayList<int[]> tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = (int) (Math.pow(2, K) - 1);
		
		int[] buildings = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		
		tree = new ArrayList<>();
		
		cur(K-1, buildings, 1);
		
		tree.sort(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		for (int i = 0; i < tree.size(); i++) {
			if (i != 0 && tree.get(i)[1] != tree.get(i-1)[1]) {
				sb.append("\n");
			}
			
			sb.append(tree.get(i)[0]).append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void cur(int K, int[] buildings, int depth) {
		int p = (int) (Math.pow(2, K) - 1);
		tree.add(new int[] {buildings[p], depth});
		
		if (buildings.length == 1) return;
		
		cur(K-1, Arrays.copyOfRange(buildings, 0, p), depth+1);
		cur(K-1, Arrays.copyOfRange(buildings, p+1, buildings.length), depth+1);
	}
}




