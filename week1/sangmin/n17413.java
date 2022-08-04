import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();					
		
		char c;
		for(int i=0;i<str.length();i++) {
			
			c = str.charAt(i);
			if(c=='<') {
				int j=i;
				for(;str.charAt(j)!='>';j++) {
					sb.append(str.charAt(j));
				}
				System.out.print(sb.append('>').toString());
				sb.setLength(0);
				i=j;
			}
			else if(c==' ') {
				System.out.print(' ');
			}
			else {
				int j=i;
				for(;j<str.length()&&str.charAt(j)!=' '&&str.charAt(j)!='<';j++) {
					sb.append(str.charAt(j));
				}
				System.out.print(sb.reverse().toString());
				sb.setLength(0);
				i=j-1;
			}			
		}
		br.close();
	}	
}