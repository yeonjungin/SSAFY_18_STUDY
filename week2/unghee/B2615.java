package study._220811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2615 {
	private static int[][] board = new int[20][20];
	private static int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int[] col = {-1, 0, 1, 1, -1, -1, 0, 1};
	private static int win = 0;
	private static int result[] = {0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i < board.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < board.length; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean b = false;
		for (int i = 1; i < board.length; i++) {
			for (int j = 1; j < board.length; j++) {
				if (board[j][i] == 1) {
					b = isBingo(j, i, 1);
				}
				else if (board[j][i] == 2) {
					b = isBingo(j, i, 2);
				}
				if (b) break;
			}
			if (b) break;
		}
		if (win == 0) {
			sb.append(win);
		}
		else {
			sb.append(win).append("\n").append(result[0]).append(" ").append(result[1]);
		}
		
		System.out.println(sb.toString());
		
	}

	private static boolean isBingo(int i, int j, int x) {
		int n_row;
		int n_col;
		int count;
		for (int j2 = 0; j2 < 8; j2++) {
			n_row = i + row[j2];
			n_col = j + col[j2];
			count = 1;
			if (1 <= n_row && n_row < 20 && 1 <= n_col && n_col < 20) {
				while (board[n_row][n_col] == x) {
					n_row += row[j2];
					n_col += col[j2];
					count++;
					
					if (1 > n_row || n_row >= 20 || 1 > n_col || n_col >= 20) {
						break;
					}
				}
				if (count == 5 && board[i-row[j2]][j-col[j2]] != x) {
					result[0] = i;
					result[1] = j;
					win = x;
					return true;
				}
			}
		}
		return false;
	}
}

