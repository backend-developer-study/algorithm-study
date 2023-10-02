import java.util.*;

public class NumberOfCounselors {
    public static void main(String[] args) {
        NumberOfCounselorsSolution solution = new NumberOfCounselorsSolution();
        int k = 5;
        int n = 20;
        //[[10, 60, 1], [15, 100, 3], [20, 30, 1], [30, 50, 3], [50, 40, 1], [60, 30, 2], [65, 30, 1], [70, 100, 2]]
        int[][] reqs = {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}};
        int result = solution.solution(k, n, reqs);
        System.out.println(result);
    }

}
class NumberOfCounselorsSolution {
    int minTime = Integer.MAX_VALUE;
    int[][] greqs = null;
    public int solution(int k, int n, int[][] reqs) {
        // 여분 멘토
        int remainMentor = n - k;
        int[] mentorList = new int[k];
        greqs = reqs;
        combi(k,remainMentor, mentorList);
        return minTime;

    }
    private void combi(int k, int n, int[] mentorList){
        if(n == 0){
            // 로직 수행
            minTime = Math.min(minTime, getWatingTime(k, mentorList.clone()));
            return;
        }
        for(int i = 0; i<k; i++){
            mentorList[i]++;
            combi(k, n-1 , mentorList);
            mentorList[i]--;
        }
    }

    private int getWatingTime(int k ,int[] mentorList){
        // 우선순위 큐로 넣되, 멘토리스트를 참고하여 넣자
        HashMap<Integer, PriorityQueue<Integer>> consultingNumMap = new HashMap<>();
        for(int i = 1; i<= k; i++){
            PriorityQueue<Integer> mentorQueue = new PriorityQueue<>();
            while(mentorList[i-1]> -1){
                mentorQueue.add(0);
                mentorList[i-1]--;
            }
            consultingNumMap.put(i, mentorQueue);
        }

        int curTime = 0;
        int waiting = 0;
        //상담 시작
        for(int[] req : greqs){
            curTime = req[0];
            int consultingTime = consultingNumMap.get(req[2]).peek();
            // 멘토 리스트 , 멘토리스트 대신 우선순위 큐로 대체
            if(curTime < consultingTime){
                // 대기
                waiting += (consultingTime - curTime);
                consultingNumMap.get(req[2]).poll();
                consultingNumMap.get(req[2]).add(consultingTime + req[1]);
            }
            // 상담이 마쳤다면
            else{
                consultingNumMap.get(req[2]).poll();
                consultingNumMap.get(req[2]).add(curTime+ req[1]);
            }
        }
        return waiting;
    }

}
