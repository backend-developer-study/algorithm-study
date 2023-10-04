import java.util.*;

public class NumberOfCounselors {
    public static void main(String[] args) {
        NumberOfCounselorsSolution solution = new NumberOfCounselorsSolution();
        int k = 3;
        int n = 5;
        int[][] reqs = {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        int k2 = 2;
        int n2 = 3;
        int[][] reqs2= {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}};
//        int result = solution.solution(k, n, reqs);
        int result2 = solution.solution(k2, n2, reqs2);
        System.out.println(result2);
    }

}

class NumberOfCounselorsSolution {
    int minTime = Integer.MAX_VALUE;
    HashMap<Integer, int[]> map = new HashMap<>();

    public int solution(int k, int n, int[][] reqs) {
        // 상담유형 테이블 만들기,
        for(int i = 1; i <= k; i++) {
            int[] arr= new int[1100];
            map.put(i, arr);
        }

        // reqs를 순회하면서 상담유형 테이블에 인원을 기록한다.
        for(int[] req : reqs) {
            int start = req[0];
            int end = req[1]+start;
            int type = req[2];
            for(int i = start; i < end; i++) { // 끝나는 시간엔 다른 상담원을 받을 수 있으니 빼주자
                map.get(type)[i]++;
            }
        }
        // 상담원수 n이 상담유형에 배치될 수 있는 방법을 구한다.

        combi(k,  n - k , new int[k+1]);
        return minTime;
    }
    private void combi(int k, int n, int[] mentorList){
        if(n == 0){ //더이상 배분할 멘토수가 없다면
            minTime = Math.min( minTime, getTotalTime(mentorList));
            // 로직 수행
            return;
        }
        for(int i = 1; i<=k; i++){
            mentorList[i]++;
            combi(k, n-1 , mentorList);
            mentorList[i]--;
        }
    }
    // 상담원리스트을 받아서, 모든 유형의 대기시간을 구한다.
    private int getTotalTime(int[] mentorList) {
        int totalTime = 0;
        for(int i = 1; i < mentorList.length; i++) {
            int waitTime = getWaitTime(mentorList[i] + 1, map.get(i));
            totalTime += waitTime;
        }
        return totalTime;

    }

    // 상담원수랑 상담유형 필요인원테이블을 비교하여, 상담원 수보다 작을 경우 대기시간을 올린다.
    private int getWaitTime(int mentorNum,int[] table) {
        int waitTime = 0;
        for(int n : table) {
            if( n > mentorNum) {
                waitTime+= n - mentorNum;
            }
        }
        return waitTime;
    }
}