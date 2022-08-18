import java.io.*;
import java.util.*;

public class Main {
	
	static int n, ans;
	static int[] order; //타자 순서
	static int[][] a;
	static boolean[] select; //타자 순서 체크
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		a = new int[n][9];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				a[i][j] = Integer.parseInt(st.nextToken());
		}
		
		select = new boolean[9];
		order = new int[9];
		select[3] = true; //1번타자 위치 4번으로 고정
		order[3] = 0;
		ans = 0;
		
		perm(1);
		
		bw.write(ans + "\n");
	    bw.flush(); bw.close(); br.close();
	}
	
	public static void perm(int cnt) { //순열(타자 순서 구하기)
		if (cnt == 9) {
			play();
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (select[i]) continue;
			select[i] = true;
			order[i] = cnt; // 타자치는 순서를 배열에 담아준다
			perm(cnt + 1);
			select[i] = false;
		}
		
	}
	
	public static void play() { // 점수 체크
		int score = 0, start = 0;
		int[] base;
		
		for (int k = 0; k < n; k++) { //이닝만큼 반복
			base = new int[3];
			int out = 0;
			
			outer: while(true) { //점수 계산
				for (int i = start; i < 9; i++) {
					int hitter = a[k][order[i]]; //타자(정해진 순서대로 바뀜)
					
					if (hitter == 0)
						out++;
					if (hitter == 1) {
						for (int j = 2; j >= 0; j--) {
							if (base[j] != 0) {
								while(base[j] != 0) {
									if (j == 2) score++;
									else base[j + 1]++;
									base[j]--;
								}
							}
						}
						base[0]++;
					}
					
					else if (hitter == 2) {
						for (int j = 2; j >= 1; j--) {
							if (base[j] != 0) {
								while(base[j] != 0) {
									score++;
									base[j]--;
								}
							}
						}
						base[2] += base[0];
						base[0] = 0;
						base[1]++;
					}
					
					else if (hitter == 3 || hitter == 4) {
						for (int j = 2; j >= 0; j--) {
							if (base[j] != 0) {
								while(base[j] != 0) {
									score++;
									base[j]--;
								}
							}
						}
						if (hitter == 3) base[2]++;
						else score++;
					}
					
					if (out == 3) { //이닝이 끝날 경우 break
                        start = i + 1; //다음타순 넘겨줌
                        if (start == 9) 
                            start = 0;
                        break outer;
                    }
						
				}
				start = 0;
			}
		}
		ans = Math.max(ans, score); //최댓값 저장
	}	
}