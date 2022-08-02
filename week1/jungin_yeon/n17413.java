package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n17413{ 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] array=br.readLine().toCharArray();
		boolean button=false;
		String temp="";
		for (int i = 0; i < array.length; i++) {
			if(array[i]=='<') {
				if (temp.length()!=0) {
					StringBuffer sb=new StringBuffer(temp);
					String r=sb.reverse().toString();
					System.out.print(r);
					temp="";
				}
				System.out.print(array[i]);
				button=true;
			}else if(array[i]=='>') {
				System.out.print(array[i]);
				button=false;
			}else if (button) {
				System.out.print(array[i]);
				continue;
			}else if(array[i]==' ' ||  array[i]=='<'){
				StringBuffer sb=new StringBuffer(temp);
				String r=sb.reverse().toString();
				System.out.print(r +' ');
				temp="";
			}else {
				temp+=array[i];
			}
		}
		if (temp.length()!=0) {
			StringBuffer sb=new StringBuffer(temp);
			String r=sb.reverse().toString();
			System.out.print(r +' ');
		}
	}
}