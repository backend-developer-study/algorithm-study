import java.util.*;

class Solution {
    String[][] table;
    ArrayList<String> result;

    public String[] solution(String[] commands) {
        table = new String[51][51];
        result = new ArrayList<>();
        for (String command : commands) {
            commandResolver(command);
        }
        int index = 0;
        String[] answer = new String[result.size()];
        for (String a : result) {
            answer[index] = a;
            index++;
        }

        return answer;
    }


    private void commandResolver(String command) {
        String[] cmdArr = command.split(" ");
        String cmd = cmdArr[0];
        int r1 = 0;
        int c1 = 0;
        int r2 = 0;
        int c2 = 0;
        String value1 = "";
        String value2 = "";

        if (cmd.equals("UPDATE")) {
            int len = cmdArr.length;
            if (len == 4) {
                r1 = Integer.parseInt(cmdArr[1]);
                c1 = Integer.parseInt(cmdArr[2]);
                value1 = (cmdArr[3] != null) ? cmdArr[3] : "";
                setColumntoValue(r1, c1, value1);
            }
            if (len == 3) {
                value1 = (cmdArr[1] != null) ? cmdArr[1] : "";
                ;
                value2 = (cmdArr[2] != null) ? cmdArr[2] : "";
                ;
                setValuetoValue(value1, value2);
            }
        }
        if (cmd.equals("MERGE")) {
            r1 = Integer.parseInt(cmdArr[1]);
            c1 = Integer.parseInt(cmdArr[2]);
            r2 = Integer.parseInt(cmdArr[3]);
            c2 = Integer.parseInt(cmdArr[4]);
            mergeColumn(r1, c1, r2, c2);
            //값 합치기
        }
        if (cmd.equals("UNMERGE")) {
            r1 = Integer.parseInt(cmdArr[1]);
            c1 = Integer.parseInt(cmdArr[2]);
            // 값 분리
            unmergeColumn(r1, c1);
        }
        if (cmd.equals("PRINT")) {
            r1 = Integer.parseInt(cmdArr[1]);
            c1 = Integer.parseInt(cmdArr[2]);
            // 출력
            result.add(printValue(r1, c1));
        }
    }

    // 값 입력
    private void setColumntoValue(int r, int c, String val) {
        if (!isMergedColumn(r, c)) {
            table[r][c] = val;
            return;
        }
        // 병합된 컬럼이면, 병합된 컬럼에도 값 넣어주기
        String log = " "+getMergedLog(r, c);
        for (int[] rc : getMergedColumn(r, c)) {
            table[rc[0]][rc[1]] = val + log;
        }
    }

    // 값 찾고 변경
    private void setValuetoValue(String preVal, String afterVal) {
        // 찾기
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (table[i][j] == null) continue;
                if (table[i][j].equals(preVal)) {
                    if (!isMergedColumn(i, j)) table[i][j] = afterVal;
                    else {
                        for (int[] rc : getMergedColumn(i, j)) {
                            table[rc[0]][rc[1]] = afterVal;
                        }
                    }
                }
            }
        }
    }

    // 병합
    private void mergeColumn(int r1, int c1, int r2, int c2) {
        StringBuilder str = new StringBuilder();
        if (!table[r1][c1].equals("")) {
            extracted(r1, c1, str);
            if (isMergedColumn(r2, c2)) { // 2번째 값이 병합 정렬이라면
                String mergedLog = getMergedLog(r2, c2);
                str.append(mergedLog);
            }
            else{
                str.append(r2).append(",").append(c2);
            }
        } else if(!table[r2][c2].equals("")){ //첫번째는 없고 두 번째 값이 있다면,
            extracted(r2, c2, str);
            str.append(r1).append(",").append(c1);
        }
        else { // 둘다 빈값
            str.append("null").append(r1).append(",").append(c1).append(" ").append(r2).append(",").append(c2);
        }
        table[r1][c1] = str.toString();
        table[r2][c2] = str.toString();
        for (int[] rc : getMergedColumn(r1, c1)) {
            table[rc[0]][rc[1]] = str.toString();
        }
        for (int[] rc : getMergedColumn(r2, c2)) {
            table[rc[0]][rc[1]] = str.toString();
        }

    }

    private void extracted(int r, int c, StringBuilder str) {
        if(isMergedColumn(r, c)){
            str.append(table[r][c]);
        }else{
            str.append(table[r][c]).append(" ").append(r).append(",").append(c);
        }
        str.append(" ");
    }

    // 헤제
    private void unmergeColumn(int r, int c) {
        //분리해야됨
        if (isMergedColumn(r, c)) {
            String init = table[r][c].split(" ")[0];
            for (int[] rc : getMergedColumn(r, c)) {
                table[rc[0]][rc[1]] = "";
            }
            table[r][c] = init;
        }
    }


    //프린트
    private String printValue(int r, int c) {
        String str = table[r][c];
        if(isMergedColumn(r,c)) {
            if(str.split(" ")[0].equals("null")){
                str = "EMPTY";
            }
            else{
                str = str.split(" ")[0];
            }
        }
        if (str.equals("")) {
            str = "EMPTY";
        }
        return str;
    }

    // 병합된 컬럼인지 확인
    private boolean isMergedColumn(int r, int c) {
        if (table[r][c] == null) return false;
        String[] strArr = table[r][c].split(" ");
        if (strArr.length > 1) {
            return true;
        }
        return false;
    }
    // 병합된 기록 남기기
    private String getMergedLog(int r, int c) {
        StringBuilder log = new StringBuilder();
        String[] strArr = table[r][c].split(" ");
        for (int i = 1; i < strArr.length; i++) {
            log.append(strArr[i]);
            log.append(" ");
        }
        return log.toString();
    }

    // 병합된 컬럼일 경우 병합된 rc 리턴
    private ArrayList<int[]> getMergedColumn(int r, int c) {
        ArrayList<int[]> mergedColumn = new ArrayList<>();
        String[] strArr = table[r][c].split(" ");
        for (int i = 1; i < strArr.length; i++) {
            String[] rcArr = strArr[i].split(",");
            int r1 = Integer.parseInt(rcArr[0]);
            int c1 = Integer.parseInt(rcArr[1]);
            mergedColumn.add(new int[]{r1, c1});
        }
        return mergedColumn;
    }
}