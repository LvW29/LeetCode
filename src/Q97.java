import java.util.Arrays;

public class Q97 {
    public static void main(String[] args) {

    }
}

/**
 * 字符串的动态规划，一般要用dfs解决的字符串问题，都是要用dp解决的
 */

class SolutionQ97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() == 0){
            if(s2.length() == 0 && s1.length() == 0)
                return true;
            else
                return false;
        }else{
            if(s1.length() == 0 && s2.equals(s3) || s2.length() == 0 && s1.equals(s3))
                return true;
        }
        if(s3.length() != s2.length() + s1.length())
            return false;

        boolean[][] dp = new boolean[s2.length()+1][s1.length()+1];
        for(int i = 0; i < s2.length(); i++){
            Arrays.fill(dp[i],false);
        }
        dp[0][0] = true;
        for(int i = 1; i <= s2.length(); i++){
            dp[i][0] = dp[i-1][0] && (s3.charAt(i-1) == s2.charAt(i-1));
        }
        for(int i = 1; i <= s1.length(); i++){
            dp[0][i] = dp[0][i-1] && (s3.charAt(i-1) == s1.charAt(i-1));
        }
        for(int i = 1; i <= s2.length(); i++){
            for(int j = 1; j <= s1.length(); j++){
                dp[i][j] = (dp[i-1][j] && s3.charAt(i+j-1) == s2.charAt(i-1)) || (dp[i][j-1] && s3.charAt(i+j-1) == s1.charAt(j-1));
                //System.out.println(dp[i][j]);
            }
        }
        return dp[s2.length()][s1.length()];
    }
}