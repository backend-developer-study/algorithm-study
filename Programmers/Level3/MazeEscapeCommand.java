class Solution {
    int[] dlru = new int[4];
    String[] dlruStr = {"d", "l", "r", "u"};
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // S -> E 까지 최소 거리 구하기 단 이동 방식도 저장할 필요 있다.
        // n*m 미로 크기
        // x, y 시작점
        // r, c 도착점
        // k 이동 횟수
        // 1. 최소 거리 구하기
         int downUp = x - r;
        int rightLeft = y - c;
        int min = Math.abs(downUp) + Math.abs(rightLeft);
        if (downUp > 0)
            dlru[3] = downUp;
        else dlru[0] = Math.abs(downUp);
        if (rightLeft > 0) dlru[1] = rightLeft;
        else dlru[2] = Math.abs(rightLeft);

        // 남은 이동횟수
        k -= min;
        // k - 최소거리가 홀수이면 impossible
        if (k % 2 != 0) return "impossible";
        // 짝수이면, 사전순으로 가장 우선이 되는 탈출 경로 반환
        int curX = x;
        int curY = y;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            while (dlru[i] > 0) {
                sb.append(dlruStr[i]);
                dlru[i]--;
                curX += dx[i];
                curY += dy[i];
            }
            if(k > 0) {
                if (n >= curX + dx[i] && m >= curY + dy[i] && 0 < curX + dx[i] && 0 < curY + dy[i]) {
                    curX += dx[i];
                    curY += dy[i];
                    dlru[3 - i]++;
                    sb.append(dlruStr[i]);
                    k-=2;
                }

            }
        }
        for (int i = 0; i < 4; i++) {
            while (dlru[i] > 0) {
                sb.append(dlruStr[i]);
                dlru[i]--;
            }
        }

        return sb.toString();
    }
}