package study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n17276 {	
	static int[][] array;
	static int n;
	
	// 배열 깊은 복사
	private static int[][] copy(int[][] newArray, int[][] originalArray){
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				newArray[i][j]=originalArray[i][j];
			}
		}
		return newArray;
	}
	
	// 시계방향으로 회전
	private static int[][] clockwise(int angle) { 
		int cnt = angle / 45; // 반복 횟수
		int[][] newArray= new int[n][n];
		newArray=copy(newArray,array);
		
		for (int i = 0; i < cnt; i++) {
			// 주 대각선 (왼쪽)
			for(int j=0; j<n; j++) {
				newArray[j][n/2]=array[j][j];
			}
			// 가운데 열
			for(int j=0; j<n; j++) {
				newArray[j][n-1-j]=array[j][n/2];
			}
			// 부 대각선 (오른쪽)
			for (int j = 0; j < n; j++) {
				newArray[n/2][n-1-j]=array[j][n-1-j];
			}
			// 가운데 행
			for (int j = 0; j < n; j++) {
				newArray[j][j]=array[n/2][j];
			}
			array=copy(array,newArray);
		}
		return newArray;
	}
	
	// 반시계방향으로 회전
	private static int[][] anticlockwise(int angle) { 
		int cnt = angle / 45; // 반복 횟수
		int[][] newArray= new int[n][n];
		newArray=copy(newArray,array);
		for (int i = 0; i < cnt; i++) {
			// 주 대각선 (왼쪽)
			for(int j=0; j<n; j++) {
				newArray[n/2][j]=array[j][j];
			}
			// 가운데 열
			for(int j=0; j<n; j++) {
				newArray[j][j]=array[j][n/2];
			}
			// 부 대각선 (오른쪽)
			for (int j = 0; j < n; j++) {
				newArray[j][n/2]=array[j][n-1-j];
			}
			// 가운데 행
			for (int j = 0; j < n; j++) {
				newArray[n-1-j][j]=array[n/2][j];
			}
			array=copy(array,newArray);
		}
		return newArray;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T= Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// 입력값 받아오기
			StringTokenizer st = new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			array= new int[n][n];
			for (int i = 0; i < n; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					array[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			if (d==0 || d==360) {
				// 그대로 출력
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						bw.write(array[i][j]+" ");
					}
					bw.write("\n");
				}
			}else if(d<0) {
				// 반시계 방향
				array=anticlockwise(Math.abs(d));
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						bw.write(array[i][j]+" ");
					}
					bw.write("\n");
				}
			}else {
				// 시계 방향
				array= clockwise(d);
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						bw.write(array[i][j]+" ");
					}
					bw.write("\n");
				}
			}
		}
		bw.close();
	}

}
/*
45° 의 배수만큼 시계방향 혹은 반시계방향 (*연산이 동시에 X에 적용)
(1).시계 방향 회전
	1. 왼쪽 대각선 -> 가운데 열로 이동
	2. 가운데 열 -> 오른쪽 대각선 위치로 이동
	3.  
*/

