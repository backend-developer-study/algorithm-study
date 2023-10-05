import java.util.*;

public class NumberOfCounselors {
    public static void main(String[] args) {
        NumberOfCounselorsSolution solution = new NumberOfCounselorsSolution();
        int k = 3;
        int n = 5;
        int[][] reqs = {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        int k2 = 2;
        int n2 = 3;
        int[][] reqs2 = {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}};
        int result = solution.solution(k, n, reqs);
        int result2 = solution.solution(k2, n2, reqs2);

        System.out.println(result2);
    }

}

class NumberOfCounselorsSolution {
    int minTime = Integer.MAX_VALUE;
    int[][] greqs = null;
    int K = 0;

    public int solution(int k, int n, int[][] reqs) {
        // 여분 멘토
        int remainMentor = n - k;
        K = k;
        int[] mentorList = new int[k];
        greqs = reqs;
        combi(0, remainMentor, mentorList);
        return minTime;

    }

    private void combi(int idx, int remain, int[] mentorList) {
        if (remain == 0) {
            // 로직 수행
            minTime = Math.min(minTime, getWatingTime(mentorList.clone()));
            return;
        }
        for (int i = idx; i < mentorList.length; i++) {
            mentorList[i]++;
            combi(i, remain - 1, mentorList);
            mentorList[i]--;
        }
    }

    private int getWatingTime(int[] mentorList) {
        // 우선순위 큐로 넣되, 멘토리스트를 참고하여 넣자
        HashMap<Integer, PriorityQueue<Integer>> consultingNumMap = new HashMap<>();
        for (int i = 1; i <= K; i++) {
            PriorityQueue<Integer> mentorQueue = new PriorityQueue<>();
            while (mentorList[i - 1] > -1) {
                mentorQueue.add(0);
                mentorList[i - 1]--;
            }
            consultingNumMap.put(i, mentorQueue);
        }

        int curTime = 0;
        int waiting = 0;
        //상담 시작
        for (int[] req : greqs) {
            curTime = req[0];
            int consultingTime = consultingNumMap.get(req[2]).peek();
            // 멘토 리스트 , 멘토리스트 대신 우선순위 큐로 대체
            if (curTime < consultingTime) {
                // 대기
                waiting += (consultingTime - curTime);
                consultingNumMap.get(req[2]).poll();
                consultingNumMap.get(req[2]).add(consultingTime + req[1]);
            }
            // 상담이 마쳤다면
            else {
                consultingNumMap.get(req[2]).poll();
                consultingNumMap.get(req[2]).add(curTime + req[1]);
            }
        }
        return waiting;
    }

}
