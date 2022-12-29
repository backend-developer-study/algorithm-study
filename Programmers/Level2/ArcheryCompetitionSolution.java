class ArcheryCompetitionSolution{
    int max;
    int apeach;
    int[] answer;
    public int[] solution(int n, int[] info) {
        int[] lianArr = new int[11];
        max = 0;

        for (int i = 0; i < 10; i++) {
            lianArr[i] = info[i]+1;
            if(info[i] > 0){
                apeach += (10-i);
            }
        }
        boolean[] visited = new boolean[11];
        dfs(n,lianArr,visited, 0);
        if (max == 0){
            answer = new int[]{-1};
        }
        return answer;
    }

    private void dfs(int use, int[] lianArr, boolean[] visited, int total){
        if(use == 0){ // 화살을 다 사용했으면 return
            if(total > apeach && total >= max){ // 확인해야됨, max 의 값인지랑 또한 어피치 총점보다 큰지
                max = total;
                answer = new int[11];
                answerCheck(visited, lianArr);
            }
        }
        int score;
        for (int i = 0; i < lianArr.length; i++) {
            if(visited[i]) continue;
            if(use>0 && i == lianArr.length -1){
                lianArr[i] = use;
            }
            if(use >= lianArr[i]){
                visited[i] = true;
                score = 10-i;
                if(lianArr[i] > 1){ // 어피치보다 많이 맞혔을 경우
                    score *=2;
                }
                total += score;
                dfs(use-lianArr[i] , lianArr, visited, total);
                visited[i] = false;
                total -= score;
            }

        }
    }
    private void answerCheck(boolean[] visited, int[] lianArr){
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]){
                answer[i] = lianArr[i];
            }
        }
    }
}