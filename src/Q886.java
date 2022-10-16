import java.util.HashSet;
import java.util.Set;

public class Q886 {
    public static void main(String[] args) {
        boolean flag = new SolutionQ886().possibleBipartition(5,new int[][]{{1,2},{1,3},{1,4},{1,5}});
        System.out.println(flag);
    }
}

/**
 * 深度优先遍历，但复杂度高
 * 参考题解，是一种二分图题-->染色法
 */

class SolutionQ886 {

    Set<Integer> group1 = new HashSet<>();
    Set<Integer> group2 = new HashSet<>();
    int visit[] = new int[10000];

    public boolean possibleBipartition(int n, int[][] dislikes) {
        if(dislikes.length == 0)
            return true;
        for(int i = 0; i < dislikes.length; i++){
            if(visit[i] == 0){
                if(!dfs(dislikes[i][0], dislikes, true))
                    return false;
            }
        }
        return true;
    }

    boolean dfs(int n, int[][] dislikes, boolean flag){
        if(flag){
            if(group1.contains(n))
                return false;
            group2.add(n);
        }else{
            if(group2.contains(n))
                return false;
            group1.add(n);
        }
        for(int i = 0; i < dislikes.length; i++){
            if(dislikes[i][0] == n && visit[i] == 0){
                int temp = dislikes[i][1];
                visit[i] = 1;
                if(!dfs(temp, dislikes, !flag))
                    return false;
            }
            if(dislikes[i][1] == n && visit[i] == 0){
                int temp = dislikes[i][0];
                visit[i] = 1;
                if(!dfs(temp, dislikes, !flag))
                    return false;
            }
        }
        return true;
    }
}