class Solution {
    public int[] solution(int e, int[] starts) {

        // 1
        // 2 = 1, 2
        // 3 = 1, 3
        // 4 = 1,2,4
        // 5 =  1, 5
        // 6 = 1,2,3,6
        // 7 = 1, 7
        // 8 = 1,2,4,8   공약수의 갯수가 곧 회쇼ㅜ가 된다.
        // 5*10^6 범위에, s는 1*10^5의 길이를 가지고 있으므로, 완전 탐색은 어렵고, 한번 확인했던 것은 기억해놓는 것 이 좋다.
        // e라는 최대값이 존재함으로, e에 맞춰 값을 만든다.
        int[] arr = new int[e+1];
        int index_max = e;
        arr[e] = cntNumber(e);
        int[] answer = new int[starts.length];
        int index = 0;
        for(int start : starts){
            for(int i = e; i >= start; i--){
                if(arr[i] == 0){
                    arr[i] = cntNumber(i);
                }
                if(arr[i] >= arr[index_max]){
                    index_max = i;
                }
            }
            answer[index] = index_max;
            index++;
        }

        return answer;
    }

    private int cntNumber(int n){
        int cnt = 0;

        int i2 = n*n;
        if(i2 > 100000000) i2 = 100000000;
        for(int i= 1; i * i <= i2 ; i++){
            if(i*i > 100000000) continue;
            if(i*i == n) cnt++;
            else if(n % i == 0) cnt+=2;
        }
        return cnt;
    }
}