import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(br.readLine());
    	
    	Set<Character> set = new HashSet<>();
    	set.add(' ');
    	
    	for(int t=0;t<n;t++) {
    		sb.setLength(0);
    		char[] str = br.readLine().toCharArray();
    		String[] splitStr = (new String(str)).split(" ");
    		boolean b = false;
    		for(String s : splitStr) {
    			// 첫 글자가 지정되어 있는지 확인
    			if(!b && !set.contains(Character.toUpperCase(s.charAt(0)))) {
    				set.add(Character.toUpperCase(s.charAt(0)));
    				sb.append("[").append(s.charAt(0)).append("]");
    				sb.append(s.substring(1, s.length())).append(" ");
    				b = true;
    				continue;
    			}
    			sb.append(s).append(" ");
    		}
    		
    		if(b) {
    			System.out.println(sb);
    			continue;
    		}

    		sb.setLength(0);
    		
    		b = false;
    		for(int i=0;i<str.length;i++) {
    			if(!b && !set.contains(Character.toUpperCase(str[i]))) {
    				set.add(Character.toUpperCase(str[i]));
    				sb.append("[").append(str[i]).append("]");
    				b = true;
    				continue;
    			}
    			sb.append(str[i]);
    		}

    		System.out.println(sb);
    	}
    	
	}
    
    public static boolean isSame(char a, char b) {
    	if(Character.toUpperCase(a) == Character.toUpperCase(b)) {
    		return true;
    	}
    	return false;
    }
}
