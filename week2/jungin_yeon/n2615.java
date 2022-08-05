import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] array=new int[19][19];
		for(int i=0; i<19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<19; j++) {
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		boolean button=false;
		int answer=0;
		int x=0; int y=0; // 위치
		
		int[] dxs= {0,1,1,-1}; // 우상, 우, 우하, 하
		int[] dys= {1,1,0,1};
		
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++) {
				if(array[i][j]>0) {		
					for(int k=0; k<4; k++) {
						int cnt=1;
						int nx=i+dxs[k]; int ny=j+dys[k];
						while(nx>=0 && nx<19 && ny>=0 && ny<19 && array[nx][ny]==array[i][j]) {
							cnt++;
							if(cnt==5) {
								if((i-dxs[k]>=0 && i-dxs[k]<19 && j-dys[k]>=0 && j-dys[k]<19 && array[i-dxs[k]][j-dys[k]]==array[i][j])) {
									cnt++;
									break;
								}
								if((nx+dxs[k]>=0 && nx+dxs[k]<19 && ny+dys[k]>=0 && ny+dys[k]<19 && array[nx+dxs[k]][ny+dys[k]]==array[i][j])) {
									cnt++;
									break;
								}
							}
							if(cnt==5) {								
								button=true;
								answer=array[i][j];
								x=i; y=j;
								break;
							}
							nx+=dxs[k];
							ny+=dys[k];
						}
					}
				}
				if (button) break;
			}
			if (button) break;
		}
		if(!button) {
			System.out.println(0);
		}else {
			System.out.println(answer);
			System.out.println((x+1) + " " + (y+1));
		}
	}
}
/*
1: 검은 바둑알, 2: 흰 바둑알, 0 : 빈자리

출력
검은색 win: 1 , 흰색 win : 2, 승부 x : 0
	승부가 났을 때, 가장 왼쪽에 있는 바둑알의 row, col 출력

*/