package 프로그래머스.레벨2.큰수만들기;
/*
*
* #만약 리턴해야하는 값이 4자리의 수라면, 입력값에서 맨뒤의 3자리를 제외한 문자열 부분에서 가장 큰 수를 찾는다.
* 그리고나서 큰 수를 찾은 자리 이후 부터 마지막 2자리를 제외한 곳에서 큰값을 찾는다. 이 과정을 반복하면 된다.
ex) 5678543 => 5,6,7,8중 가장 큰 수 8
Ex) 23563850135 에서 4자리 => 맨 뒤 1,3,5를 제외한 숫자중에 가장 큰 수를 찾는다. 23563850 ==> 8이다.
answer = "8" 오른쪽부터 다시 탐색을 한다. 50135중에 맨뒤 2개를 제외하고 가장 큰 값을 찾는다. 5이다. answer = "85"
0135 중 맨뒤 5를 제외하고 0,1,3 중에 가장 큰 값을 찾는다 => 3 ==> answer = "853" 맨뒤 5를 붙이면 Answer = "8535"가 된다.
* */
public class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int idx = 0;
        int max = 0;
        for(int i=0; i<number.length()-k; i++){ // 가장 큰 수가 N 자리 수라면 N번(전체 문자열길이 - k) 반복
            max = 0;
            for(int j=idx; j<=i+k; j++){
                if(max < number.charAt(j)-'0'){    // 지금까지 비교한 수보다 큰 수라면
                    max = number.charAt(j)-'0';
                    idx = j+1;  // 찾은 수 오른쪽부터 탐색
                }
            }
            answer.append(max);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution("23563850135",7));
    }
}
