class Solution {
    public int solution(int n) {

        /// DP 문제
        int answer = 1;
        int[] dp = new int[100001];
        dp[1] = 1;

        if(n > 1)
            dp[2] = 3;
        if(n > 2)
            dp[3] = 10;
        // dp[4] = (dp[1] *4) + (dp[2] * 3) + dp[3];
        // dp[5] = (dp[3] * dp[2]) + dp[4];
        if(n > 3){
            int one = 0;
            int two = 0;
            int three = 0;
            for(int i = 4; i <= n; i++){
                one = (dp[i-3] * 5)%1000000007;
                two = (dp[i-2] * 3)%1000000007;
                three = (dp[i-1])%1000000007;
                dp[i] = (one+two+three)%1000000007;
            }
        }
        answer = dp[n];
        return answer;
    }
}