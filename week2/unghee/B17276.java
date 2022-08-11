package study._220811;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class B17276 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		String[][] board;
		int n;
		int degree;
		LinkedList<String[]> elements;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			String[] nd = br.readLine().split(" ");
			
			n = Integer.parseInt(nd[0]);
			degree = Integer.parseInt(nd[1]) / 45 % 8;
			if (degree < 0) degree = 8 + degree;
			board = new String[n][];
			elements = new LinkedList<>();
			
			for (int i = 0; i < board.length; i++) {
				board[i] = br.readLine().split(" ");
			}
			
			String[] temp = new String[n];
			for (int i = 0; i < n; i++) {
				temp[i] = board[n/2][i];
			}
			elements.add(temp);
			
			temp = new String[n];
			for (int i = 0; i < n; i++) {
				temp[i] = board[i][i]; 
			}
			elements.add(temp);
			
			temp = new String[n];
			for (int i = 0; i < n; i++) {
				temp[i] = board[i][n/2];
			}
			elements.add(temp);
			
			temp = new String[n];
			for (int i = 0, j = n-1; i < n; i++, j--) {
				temp[i] = board[i][j];
			}
			elements.add(temp);
			
			for (int i = 0; i < 4; i++) {
				if ((i + degree) % 8 > 3) {
					String[] e = elements.get(i);
					String[] e_reverse = new String[e.length];
					for (int j = 0; j < e.length; j++) {
						e_reverse[j] = e[e.length - j - 1]; 
					}
					elements.remove(i);
					elements.add(i, e_reverse);
				}
			}
			
			for (int i = 0; i < degree; i++) {
				elements.addFirst(elements.pollLast());
			}
			
			for (int i = 0; i < n; i++) {
				board[n/2][i] = elements.get(0)[i];
			}
			
			for (int i = 0; i < n; i++) {
				board[i][i] = elements.get(1)[i]; 
			}
			
			for (int i = 0; i < n; i++) {
				board[i][n/2] = elements.get(2)[i];
			}
			
			for (int i = 0, j = n-1; i < n; i++, j--) {
				board[i][j] = elements.get(3)[i];
			}
			
			
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					bw.write(board[i][j] + " ");
				}
				bw.write("\n");
			}
		}
		bw.close();
	}

}

