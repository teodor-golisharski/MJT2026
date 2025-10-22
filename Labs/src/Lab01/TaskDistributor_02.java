package Lab01;

public class TaskDistributor_02 {
    public static int minDifference(int[] tasks) {
        int total = 0;
        for (var task : tasks) {
            total += task;
        }

        int target = total / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (var task : tasks) {
            for (int j = target; j >= task; j--) {
                dp[j] = dp[j] || dp[j - task];
            }
        }

        for (int j = target; j >= 0; j--) {
            if (dp[j]) {
                return total - 2 * j;
            }
        }

        return 0;
    }


    void main() {
        int result = minDifference(new int[]{9, 1, 1, 1});
        System.out.println(result);
    }
}
