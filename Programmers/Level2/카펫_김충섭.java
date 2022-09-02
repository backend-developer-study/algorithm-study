package 프로그래머스.레벨2.카펫;

import java.util.Arrays;

/*
* 갈색과 노란색 격자 수가 입력으로 주어지면 카펫의 가로 세로 크기를 리턴하는 함수를 작성하세요.
* 데이터크기 8<= brown <= 5,000, 1 <= yellow <= 2,000,000
* 가로 >= 세로
* 분석
* 카펫의 가로 길이와 세로 길이를 바꾸면서 노란색 격자수를 체크한다.
* 노란색 격자 수 = (가로-2)*(세로-2)
* 조건을 보면 노란색 격자수는 반드시 존재한다.( 1 <= yellow)
* 따라서 세로(=높이)는 무조건 최소 3이어야 한다. 왜냐하면 높이가 1 또는 2라면 노란색 격자가 존재하지 않기 때문이다.
* */
public class 카펫 {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow;
        int width;
        for (int height = 3; height<=total; height++) {
            if (total%height != 0)
                continue;
            width = total/height;
            if ((width-2)*(height-2) == yellow) {
                answer[0] = width;
                answer[1] = height;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        카펫 sol = new 카펫();
        System.out.println(Arrays.toString(sol.solution(24,24)));
    }
}
