package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
* 시작 시간이 아니라 끝 시간을 기준으로 회의를 갯수를 카운팅 하는게 핵심
* 만약 처음 시간을 카운팅의 기준으로 삼으면 (1, 1000) (2, 3) (4,5) 의 경우 답이 2여야 하지만 1이 됨
* 끝시간으로 카운트 하면 답이 2가 됨
* (3,3) (1, 3) (2,3)의 경우 정렬 기준이 끝 시간만있으면 답이 1이 됨 왜냐면 (이전 회의 끝 시간 <= 현재 회의 첫시간)이어야 카운트 +1
* 따라서 예외적 케이스를 제거하기 위해 만약 끝 시간이 같다면 시작 시간도 오름차순으로 정렬해야됨
*
* */
class Meeting implements Comparable<Meeting> {
    int start;  // 시작 시간
    int end;    // 끝 시간

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting o) {
        if (this.end == o.end)      // 만약 끝 시간이 같다면 시작 시간을 오름차순으로 정렬
            return this.start - this.end;
        return this.end - o.end;    // 기본적으로 끝 시간을 오름차순으로 정렬
    }
}

public class 회의실배정_1931 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 회의 갯수 N

        // 리스트에 시작과 끝시간을 담고 있는 회의를 담아준다.
        StringTokenizer st;
        ArrayList<Meeting> list = new ArrayList<>();
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Meeting(start, end));
        }

        // 리스트에 담긴 회의들을 기준에 맞게 정렬한다.
        Collections.sort(list);
        int answer = 0;

        int endMeeting = 0; // 초기 설정
        for (Meeting meeting : list) {
            if (endMeeting <= meeting.start) {
                answer++;
                endMeeting = meeting.end;
            }
        }

        System.out.println(answer);
    }
}
