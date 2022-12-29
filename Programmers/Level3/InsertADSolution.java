class InsertADSolution {
    public String solution(String play_time, String adv_time, String[] logs) {

        int play_second = timeConvertSecond(play_time);
        int adv_second = timeConvertSecond(adv_time);
        int[] viewer = new int[play_second+1];

        for (String log:logs) {
            String[] viewTerm = log.split("-");
            int startView = timeConvertSecond(viewTerm[0]);
            int endView = timeConvertSecond(viewTerm[1]);
            for (int i = startView + 1; i < endView + 1; i++) {
                viewer[i]++;
            }

        }
        // 투포인터 확인
        int start = 0;
        int end = 0;
        int sum = 0;
        for (end = 0; end < adv_second; end++) {
            sum += viewer[end];
        }
        int max = sum;
        int mostTime = 0;
        while (end < play_second){
            end++;
            start++;
            sum += (viewer[end] - viewer[start]);
            if(max < sum){
                max = sum;
                mostTime = start;
            }
        }

        return secondConvertTime(mostTime);
    }

    // 시간 문자열 -> 초단위로
    private int timeConvertSecond(String time){
        String[] split = time.split(":");
        int second = (Integer.parseInt(split[0])* 3600);
        second += (Integer.parseInt(split[1])* 60);
        second += (Integer.parseInt(split[2]));

        return second;
    }

    // 초 -> 시간문자열로
    private String secondConvertTime(int second){
        String time ="";
        int H = second/3600;
        second = second % 3600;
        int M = second/60;
        second = second % 60;
        int S = second;
        return changTwoDigit(H).append(":").append(changTwoDigit(M)).append(":").append(changTwoDigit(S)).toString();
    }
    // 한자리 숫자일 수도 있으니, 무조건 두 자리 문자열로
    private StringBuilder changTwoDigit(int number){
        StringBuilder twoDigit = new StringBuilder();
        if(number < 10){
            twoDigit.append("0");
        }
        return twoDigit.append(number);
    }
}