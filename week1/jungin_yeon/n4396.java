package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n4396{ 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		char[][] bombArray=new char[n][n];
		char[][] result=new char[n][n];
		
		for (int i = 0; i < n; i++) {
			bombArray[i]=br.readLine().toCharArray();
		}
		for (int i = 0; i < n; i++) {
			result[i]=br.readLine().toCharArray();
		}
		int[] dxs = {-1,-1,-1,0,0,1,1,1};
		int[] dys= {-1, 0, 1, -1, 1, -1, 0, 1};
		
		boolean button=false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (result[i][j]=='.') {
					continue;
				}
				if (bombArray[i][j]=='.') {
					int temp=0;
					for (int k = 0; k < 8; k++) {
						int nx=i+dxs[k];
						int ny=j+dys[k];
						if(0<=nx && nx<n && 0<=ny && ny<n) {
							if(bombArray[nx][ny]=='*') {
								temp+=1;
							}
						}
					}
					result[i][j]=(char)(temp+'0');
				}else {
					button=true;
				}
			}
		}
		if (button) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(bombArray[i][j]=='*') {
						result[i][j]='*';
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(result[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}