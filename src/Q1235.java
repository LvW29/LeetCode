public class Q1235 {
    public static void main(String[] args) {
        int x = new SolutionQ1235().jobScheduling(new int[]{1,2,3,4,6},new int[]{3,5,10,6,9},new int[]{20,20,100,70,60});
        System.out.println(x);
    }
}

/**
 * 动态规划，dp[i]表示前i份工作的最多报酬
 * 但要注意，工作需要按照endTime从小到大排序
 * 这里用了最笨的n2的排序
 * 注意学习nlogn排序的写法
 * 另外，找j的时候，可以用二分查找，速度更快
 */
class SolutionQ1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(endTime[i] > endTime[j]){
                    int temp = endTime[i];
                    endTime[i] = endTime[j];
                    endTime[j] = temp;

                    temp = startTime[i];
                    startTime[i] = startTime[j];
                    startTime[j] = temp;

                    temp = profit[i];
                    profit[i] = profit[j];
                    profit[j] = temp;
                }
            }
        }
        /*
                int[][] jobs = new int[n][];
                for (int i = 0; i < n; i++) {
                    jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
                }
                Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
                //组成一个整体，这个整体是一个数组，于是用一个二维数组来存储
         */

        int dp[] = new int [n];
        dp[0] = profit[0];
        for(int i = 1; i < n; i++){
            int j = i-1;
            while(j >= 0 && endTime[j] > startTime[i])
                j--;
            if(j < 0){
                dp[i] = Math.max(dp[i-1],profit[i]);
            }else{
                dp[i] = Math.max(dp[i-1],dp[j]+profit[i]);
            }
        }
        return dp[n-1];
    }
}
