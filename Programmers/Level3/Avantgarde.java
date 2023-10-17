import java.util.*;

class Solution {
    public int solution(int n) {
/// DP 문제
        int answer = 1;
        int[] dp = new int[100001];
        dp[0] = 1;
        dp[1] = 1;
        if (n > 1) dp[2] = 3;
        if (n > 2) dp[3] = 10;
        //2422  2242 4224
        ArrayList<Integer>[] memo = new ArrayList[3];
        memo[0] = new ArrayList<>(List.of(2, 6, 12, 32));
        memo[1] = new ArrayList<>(List.of(2, 4, 16, 36));
        memo[2] = new ArrayList<>(List.of(4, 6, 12, 52));


        // 4
        // 2
        // 2

        for (int i = 4; i <= n; i++) {
            int now = (i - 1) % 3;
            int t1 = (now + 1) % 3;
            int t2 = (now + 2) % 3;

            dp[i] = (dp[i - 1] + dp[i - 2] * 2 + dp[i - 3] * 5 + memo[now].get(i - 4)) % 1000000007;

            memo[now].add((memo[now].get(i - 1) + dp[i] * 4) % 1000000007);
            memo[t1].add((memo[t1].get(i - 1) + dp[i] * 2) % 1000000007);
            memo[t2].add((memo[t2].get(i - 1) + dp[i] * 2) % 1000000007);
        }
        return dp[n];
    }
}