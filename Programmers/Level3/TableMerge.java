import java.util.*;
class Solution {
    int[] parent;
    String[] table;
    List<String> result;

    public String[] solution(String[] commands) {
        parent = new int[2501];
        table = new String[2501];
        result = new ArrayList<>();
        for (int i = 1; i <= 2500; i++) {
            parent[i] = i;
        }

        for (String cmd : commands) {
            commandResolver(cmd);
        }


        String[] answer = new String[result.size()];
        int idx = 0;
        for (String str : result) {
            answer[idx] = str;
            idx++;
        }

        return answer;
    }

    private void commandResolver(String str) {
        String[] cmdArr = str.split(" ");
        String cmd = cmdArr[0];
        int len = 0;
        int r = 0;
        int c = 0;
        int r2 = 0;
        int c2 = 0;
        String val1 = "";
        String val2 = "";

        if (cmd.equals("UPDATE")) {
            len = cmdArr.length;
            if (len == 4) {
                //해당 위치에 값 기입
                r = Integer.parseInt(cmdArr[1]);
                c = Integer.parseInt(cmdArr[2]);
                val1 = cmdArr[3];
                updateRCvalue(makeParentIndex(r, c), val1);
            }
            // 모든 값 바꾸기
            if (len == 3) {
                val1 = cmdArr[1];
                val2 = cmdArr[2];
                updateChangeValue(val1, val2);
            }
            return;
        }
        r = Integer.parseInt(cmdArr[1]);
        c = Integer.parseInt(cmdArr[2]);

        if (cmd.equals("MERGE")) {
            r2 = Integer.parseInt(cmdArr[3]);
            c2 = Integer.parseInt(cmdArr[4]);
            mergeTwoRC(makeParentIndex(r, c), makeParentIndex(r2, c2));
        }
        if (cmd.equals("UNMERGE")) {
            unMerge(makeParentIndex(r, c));
        }
        if (cmd.equals("PRINT")) {
            printValue(makeParentIndex(r, c));
        }
        return;
    }

    private void updateRCvalue(int x, String val) {
        //해당 위치의 parent값과 동일한지 찾기
        table[getParent(x)] = val;
    }

    private void updateChangeValue(String val1, String val2) {
        getSameValueList(val1, val2);
    }

    private void mergeTwoRC(int x1, int x2) {

        String str;
        if (table[getParent(x1)] != null) {
            str = table[getParent(x1)];
        } else {
            str = table[getParent(x2)];
        }
        table[getParent(x1)] = null;
        table[getParent(x2)] = null;
        int idx = unionParent(x1, x2);
        table[idx] = str;
    }

    private void unMerge(int x) {
        String str = table[getParent(x)];
        int t = getParent(x);

        for (int i = 2500; i > 0; i--) {
            if (findParent(t, i)) {
                //초기화
                table[i] = null;
                parent[i] = i;
            }
        }
        table[x] = str;

    }

    private void printValue(int x) {
        int idx = getParent(x);
        if (table[idx] == null) {
            result.add("EMPTY");
            return;
        }
        result.add(table[idx]);
    }


    private void getSameValueList(String val, String val2) {
        for (int i = 0; i < 2500; i++) {
            if (table[i] == null) continue;
            if (table[i].equals(val)) {
                updateRCvalue(i, val2);
            }
        }
        return;
    }

    private int getParent(int x) {
        if (parent[x] == x) {
            return x;
        }
        return getParent(parent[x]);
    }

    // 합치기
    private int unionParent(int x, int y) {
        x = getParent(x);
        y = getParent(y);
        if (x > y) parent[x] = y;
        else parent[y] = x;
        return parent[x];
    }

    // 조회
    private boolean findParent(int x, int y) {
        x = getParent(x);
        y = getParent(y);
        return x == y;
    }

    // r, c 조회
    private int[] getCoordinate(int x) {
        int r = x / 50;
        int c = x % 50;
        return new int[]{r, c};
    }

    private int makeParentIndex(int r, int c) {
        return (r-1) * 50 + c;
    }


}