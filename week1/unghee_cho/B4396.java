package study._220804;
import java.util.Scanner;

public class B4396 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] row = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] col = {0, 1, -1, 1, -1, 1, -1, 0};
		
		char[][] results = new char[n][n];
		char[][] mine = new char[n][n];
		char[][] open = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			mine[i] = sc.next().toCharArray();
		}
		
		for (int i = 0; i < n; i++) {
			open[i] = sc.next().toCharArray();
		}
		
		boolean mineOpen = false;
		int new_r;
		int new_c;
		int mineCount = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (open[i][j] == 'x' && mine[i][j] == '*') {
					mineOpen = true;
					continue;
				}
				if (open[i][j] == 'x') {
					mineCount = 0;
					for (int x = 0; x < 8; x++) {
						new_r = i + row[x];
						new_c = j + col[x];
						
						if (0 <= new_r && new_r < n && 0 <= new_c && new_c < n) {
							if (mine[new_r][new_c] == '*') {
								mineCount++;
							}
						}
					}
					results[i][j] = (char) (mineCount + '0');
				}
				else {
					results[i][j] = '.';
				}
			}
		}
		
		if (mineOpen) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (mine[i][j] == '*') {
						results[i][j] = '*';
					}
				}
			}
		}
		
		for (char[] result : results) {
			for (char r : result) {
				System.out.print(r);
			}
			System.out.println();
		}
	}

}
