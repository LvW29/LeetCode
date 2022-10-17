import java.util.HashMap;
import java.util.Map;

public class Q904 {
    public static void main(String[] args) {
        int x = new SolutionQ904().totalFruit(new int[]{1,0,1,4,1,4,1,2,3});
        System.out.println(x);
    }
}

/**
 * 求只包含两种元素的最长子序列
 * 双指针-->滑动窗口
 */
class SolutionQ904{
    public int totalFruit(int[] fruits) {
        int left = 0;
        int right = 0;
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        while(right < fruits.length){
            if(map.containsKey(fruits[right])){
                map.put(fruits[right],map.get(fruits[right])+1);
                right++;
            }else if(map.size() < 2){
                map.put(fruits[right],1);
                right++;
            }else{
                map.clear();
                left = right-1;
                while(fruits[left] == fruits[right-1]){
                    left--;
                }
                left++;
                map.put(fruits[right-1],right-left);
                map.put(fruits[right],1);
                right++;
            }
            res = Math.max(right-left,res);
        }
        return res;
    }
}