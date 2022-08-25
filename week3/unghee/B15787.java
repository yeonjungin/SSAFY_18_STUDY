package study._220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B15787 {
	private static ArrayList<ArrayList<Boolean>> trains;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int result = 0;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		trains = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			ArrayList<Boolean> temp = new ArrayList<>();
			for (int j = 0; j < 20; j++) {
				temp.add(false);
			}
			trains.add(temp);
		}
		
		for (int i = 0; i < M; i++) {
			String[] temp = br.readLine().split(" ");
			int op = Integer.parseInt(temp[0]);
			int n = Integer.parseInt(temp[1]);
			int seat = 0;
			if (temp.length == 3) {
				seat = Integer.parseInt(temp[2]) - 1; 
			}
			
			switch (op) {
			case 1:
				one(n, seat);
				break;
			case 2:
				two(n, seat);
				break;
			case 3:
				three(n);
				break;
			case 4:
				four(n);
				break;
			}
		}
		
		HashSet<ArrayList<Boolean>> alreadyGo = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			if (!alreadyGo.contains(trains.get(i))) {
				result++;
				alreadyGo.add(trains.get(i));
			}
		}
		
		System.out.println(result);
	}
	
	private static void one(int n, int seat) {
		trains.get(n).set(seat, true);
	}
	private static void two(int n, int seat) {
		trains.get(n).set(seat, false);
	}
	private static void three(int n) {
		boolean temp_b = false;
		boolean temp_a = false;
		for (int i = 0; i < 20; i++) {
			temp_a = trains.get(n).get(i);
			trains.get(n).set(i, temp_b);
			temp_b = temp_a;
		}
	}
	private static void four(int n) {
		boolean temp_b = false;
		boolean temp_a = false;
		for (int i = 19; i >= 0; i--) {
			temp_a = trains.get(n).get(i);
			trains.get(n).set(i, temp_b);
			temp_b = temp_a;
		}
	}

}
