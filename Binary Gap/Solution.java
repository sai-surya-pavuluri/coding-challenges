
import java.util.regex.*;

class Solution {
    public int solution(int N) {
        String binary = Integer.toBinaryString(N);
        int optimalGap = 0;
        Pattern pattern = Pattern.compile("10*1");
        Matcher matcher = pattern.matcher(binary);
        while(matcher.find()){
            int foundGap = (matcher.group().length() - 2);
            if(optimalGap < foundGap){
                optimalGap = foundGap;
            }
        }
        return optimalGap;
    }

    public static void main(String[] args){
        Solution obj = new Solution();
        System.out.println(obj.solution(9));

    }
}
