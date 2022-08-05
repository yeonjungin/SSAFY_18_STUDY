import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	
	public static int[][] arrayCopy(int[][] newArray,int[][] array){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newArray[i][j]=array[i][j];
			}
		}
		return newArray;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		int r=Integer.parseInt(st.nextToken());
		int[][] array=new int[n][m];
		for (int i = 0; i < n; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				array[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int[][] newArray=new int[n][m];
		newArray=arrayCopy(newArray,array);
		int cnt = Math.min(n,m)/2;
		
		for (int i = 0; i < r; i++) {
			int fn=0; int en=n-1; int fm=0; int em=m-1;
			int temp= array[0][0];			
			for (int p = 0; p <cnt; p++) {
				// 왼쪽으로 이동 (맨윗줄)
				for (int j = fm; j <em ; j++) {
					newArray[fn][j]=array[fn][j+1];
				}
				// 위쪽으로 이동 (맨오른쪽줄)
				for (int j = fn; j < en; j++) {
					newArray[j][em]=array[j+1][em];
				}
				// 오른쪽으로 이동 (맨아래줄)
				for (int j = em; j >fm; j--) {
					newArray[en][j]=array[en][j-1];
				}
				// 아래쪽으로 이동 (맨왼쪽줄)
				for (int j = en; j >fn ; j--) {
					newArray[j][fm]=array[j-1][fm];
				}
				fn++; en--; fm++; em--;
			}
			array=arrayCopy(array,newArray);
		}
		for(int a=0; a<n; a++) {
			for (int b = 0; b < m; b++) {
				bw.write(array[a][b]+" ");
			}
			bw.write("\n");
		}
		bw.close();
	}

}
