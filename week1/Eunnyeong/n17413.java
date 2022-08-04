import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == '<') {
        		sb.append(s.charAt(i++));
        		while(s.charAt(i) != '>')
        			sb.append(s.charAt(i++));
        		sb.append(s.charAt(i));
        	}
        	else
        		if (s.charAt(i) != ' ')
        			tmp.append(s.charAt(i));
        
        	if ((i + 1 < s.length() && s.charAt(i + 1) == '<' )|| i == s.length() - 1 || (i + 1 < s.length() && s.charAt(i + 1) == ' ' )) {
        		if (tmp.length() > 0)  {
        			sb.append(tmp.reverse());
        			tmp.setLength(0);
        		}
        		if (i + 1 < s.length() && s.charAt(i + 1) == ' ')
        			sb.append(" ");
        	}
       }
        bw.write(sb.toString());
        bw.flush(); bw.close(); br.close();
    }
}