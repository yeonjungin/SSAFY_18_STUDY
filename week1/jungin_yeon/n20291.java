package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n20291{ 
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		Map<String,Integer> map=new TreeMap();
		for (int i = 0; i < n; i++) {
			String temp=br.readLine(); // 한줄 입력받기
			temp=temp.substring(temp.indexOf(".")+1); // 확장자
			if(map.get(temp)==null) {
				map.put(temp, 1);
			}else {
				map.put(temp, map.get(temp)+1);
			}
		}
		for (Map.Entry<String, Integer> element : map.entrySet()) {
			System.out.println(element.getKey()+ " " + element.getValue());
		}
	}
}