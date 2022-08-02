package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n2578{ 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] array=new int[5][5];
		boolean[][] visited=new boolean[5][5];
		
		HashMap<Integer, Integer> arrayInfo=new HashMap<>(); // 파이썬 딕셔너리와 같은 존재
		for (int i = 0; i < 5; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				array[i][j]=Integer.parseInt(st.nextToken()); // 숫자 배열에 넣는 법
				arrayInfo.put(array[i][j], i*5+j); // 숫자는 key, 인덱스는 value
			}
		}
		
		int cnt=0; // 빙고된 줄 개수	
		boolean button=false;
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				cnt++;
				int num=Integer.parseInt(st.nextToken());
				int idx=arrayInfo.get(num); // 딕셔너리에서 value값 가져오기
				visited[idx/5][idx%5]=true; // 방문처리
				int sum=0;
				
				for(int x=0; x<5; x++) {
					int r=0,c=0;
					for(int y=0; y<5; y++) {
						if(visited[x][y]) r++; // 가로
						if(visited[y][x]) c++; // 세로
					}
					if (r==5) sum++; // 가로
					if (c==5) sum++; // 세로
				}
				// 왼쪽 대각선 체크
				int temp=0; 
				for(int x=0; x<5; x++) {
					if(visited[x][x]) temp++;
				}
				if (temp==5) sum++;
				
				// 오른쪽 대각선 체크
				temp=0;
				for(int x=0; x<5; x++) {
					if(visited[x][4-x]) temp++;
				}
				if (temp==5) sum++;
				
				if (sum>=3) {
					System.out.println(cnt);
					button=true;
					break;
				}
			}
			if (button) {
				break;
			}
		}
		
	}
}