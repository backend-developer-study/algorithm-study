class Solution {
    public int solution(int n) {

        /// DP 문제
        int answer = 1;
        int[] dp = new int[100001];
        dp[0] = 1;
        dp[1] = 1;
        if(n > 1)
            dp[2] = 3;
        if(n > 2)
            dp[3] = 10;
        // dp[4] = (dp[1] *4) + (dp[2] * 3) + dp[3]; (2개 포함)
        // dp[5] =  아래와 같은 모양을 추가할 수 있다. (반대 포함 2개)
        // dp[6] = 4개 필요

        // ㅡㅡㅡ ㅡㄱ
        // ㅣ ㅡㅡㅡ ㅣ
        // ㄴㅡ ㅡㅡㅡ
        //
        // ⎮   ㅡㅡㅡ  2개
        // ㄴㅡ  ㅡ
        // ㅡㅡㅡ   ⏋

        //

        if(n > 3){
            int one = 0;
            int two = 0;
            int three = 0;
            int four = 0;
            int five = 0;
            int six = 0;
            for(int i = 4; i <= n; i++){
                one = (dp[i-3] * 5)%1000000007;
                two = (dp[i-2] * 2)%1000000007;
                three = (dp[i-1])%1000000007;
                if(i % 4 == 0){
                    four = (dp[i-4] * 2)%1000000007;
                    five = 0;
                    six = 0;
                }
                if(i % 5 == 0){
                    four = 0;
                    five = (dp[i-5] * 2)%1000000007;
                    six = 0;
                }
                if(i % 6 == 0){
                    four = 0;
                    five = 0;
                    six = (dp[i-6] * 4)%1000000007;
                }
                dp[i] = (one+two+three+four + five + six)%1000000007;
            }
        }
        answer = dp[n];
        return answer;
    }
}