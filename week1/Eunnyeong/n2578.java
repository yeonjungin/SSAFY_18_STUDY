import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int[][] a = new int[5][5];
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				a[i][j] = s.nextInt();
		
		int[] x = new int[25];
		for (int i = 0; i < 25; i++) 
			x[i] = s.nextInt();
		
		int[][] check = new int[5][5];
		for (int i = 0; i < 5; i++)
			Arrays.fill(check[i], 0);
		
		for (int k = 0; k < 25; k++) {
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++)
					if (x[k] == a[i][j])
						check[i][j] = 1;
			
			int f = 0;
			for (int i = 0; i < 5; i++) { //가로
				int cnt = 0;
				for (int j = 0; j < 5; j++) {
					if (check[i][j] == 1)
						cnt++;
				}
				if (cnt == 5) 
					f++;
			}
			
			for (int i = 0; i < 5; i++) { //세로
				int cnt = 0;
				for (int j = 0; j < 5; j++) {
					if (check[j][i] == 1)
						cnt++;
				}
				if (cnt == 5) 
					f++;
			}
			
			int cnt = 0;
			for (int i = 0; i < 5; i++) { //대각선1
				for (int j = 0; j < 5; j++) {
					if (i == j && check[i][j] == 1)
						cnt++;
				}
				if (cnt == 5)
					f++;
			}
			
			cnt = 0;
			for (int i = 0; i < 5; i++) { //대각선2
				for (int j = 0; j < 5; j++) {
					if (i + j == 4 && check[i][j] == 1)
						cnt++;
				}
				if (cnt == 5) 
					f++;
			}
				
			if (f >= 3) {
				System.out.println(k + 1);
				break;
			}
		}
	}
}