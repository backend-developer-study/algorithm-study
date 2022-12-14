
# 문제 구성 📖
> 코딩테스트 사이트 : 프로그래머스 
> 난이도 : 2단계    
> 풀이 날짜 : 2022.09.05  
> 사용한 풀이 방법 : 조합, 완전탐색, HashMap  
> 소요시간 : 1시간 10분 
## 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/72411

<br></br>
<br></br>


## 삽질코딩
> 역시 카카오 문제..같은 2단계라 할지라도, 체감난이도는 훨씬 높았다. 
> 일단 가장먼저, 손님들의 데이터를 어떻게 보관해야될지 부터 생각해봤다. 
### 풀이
 - 손님 주문데이터를 메뉴별로 나눠 보관할까 했지만, 의미가 없었고, A~Z까지가 메뉴니까, 배열을 만들어 담아볼까 해도 오히려 복잡해지는 로직이다. 
 - 문득 조합을 생각해냈고, 미리 조합된 내용을 가지고 HashMap에 저장함으로써, 갯수도 세면 좋겠다는 생각을 했다. 

#### 순서
 - Course에 조합할 메뉴순으로 하나씩 진행한다.(최대길이 10)
 - 조합할 메뉴 갯수에 맞춰 1번 손님부터 조합을 진행한다.(최대 20손님)
   - 조합에 나온 결과값을 만들어 놓은 HashMap에 집어 넣는다. 
   - 모든 손님을 진행했다면, 가장 큰수를 뽑아, 답변 배열에 추가한다. 
 - 답변배열은, 길이와 상관없이 오름차순으로 정렬한다.

### 코드
```java
package programmers.two;

import java.util.*;
import java.util.stream.Collectors;

public class MenuRenewal {
    public static void main(String[] args) {
        MenuRenewal1 m = new MenuRenewal1();
        String[] s1 = m.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},new int[]{2,3,4});
        String[] s2 = m.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"},new int[]{2,3,5});
        String[] s3 = m.solution(new String[]{"XYZ", "XWY", "WXA"},new int[]{2,3,4});

        System.out.println("s1 = " + Arrays.toString(s1));
        System.out.println("s2 = " + Arrays.toString(s2));
        System.out.println("s3 = " + Arrays.toString(s3));
    }
}
class MenuRenewal1 {
    public String[] solution(String[] orders, int[] course) {

        ArrayList<String> answer = new ArrayList<>();
        int max= 0;
        for(int i : course){
            HashMap<String, Integer> menu = new HashMap();
            Stack<Character> stack = new Stack<>();
            for (String s : orders){
                combi(s,s.length(), i,0,stack,menu);
            }
            List<Map.Entry<String, Integer>> entries = menu.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
            max = 0;
            for(Map.Entry<String, Integer> entry:entries){
                if(max <= entry.getValue() ){
                    answer.add(entry.getKey());
                    max = entry.getValue();
                }
            }

        }
        Collections.sort(answer);
        int index = 0;
        String[] result = new String[answer.size()];
        for(String ans: answer){
            result[index] = ans;
            index++;
        }
        return result;
    }

    public void combi(String s, int n, int r, int start, Stack<Character> stack, HashMap<String, Integer> menu){
        if(r ==0){
            StringBuilder sb= new StringBuilder();
            for(char cha : stack){
                sb.append(cha);
            }
            int cnt =  menu.getOrDefault(sb.toString(), 0);
            menu.put(sb.toString(),cnt+1);
        }
        for(int i = start; i < n; i++){
            stack.add(s.charAt(i));
            combi(s, n, r-1, i+1 ,stack, menu);
            stack.pop();
        }
    }

}
```

### 결과
<img src="https://user-images.githubusercontent.com/104331549/188357777-77128cc7-cc5f-4b1d-a442-b8eeadd22332.png">

- 예제랑 다르게, 주문된 코스요리 조합의 횟수가 1번만 불린게, 최대값이면 위와같이 출력되어, 범위도 정해줬더니,

### 추가된 코드 

```java
if(max <= entry.getValue() && entry.getValue() > 1){ // 1보다 클 경우만
    answer.add(entry.getKey());
    max = entry.getValue();
}
```

<img src="https://user-images.githubusercontent.com/104331549/188358014-f662756e-dba8-4bf8-993a-49563aed392b.png">

- 예제3번 답이 이상해서 다시 한번 확인해 보니
<img src= "https://user-images.githubusercontent.com/104331549/188358117-ca4f8bed-7c9b-4bad-b4a5-016b848143f3.png">

- 주문한 순서에 관계없이 출력됨을 고려해야되서, 어차피 답은 알파벳 오름차순으로 정렬해야되기에, 
- 정렬 메소드를 하나더 추가해줬다.
- 하지만, 이번에는 계속 아래와 같이 결과물이 나오는 것이다. (`["XY"]` 가 사라짐)

<img src ="https://user-images.githubusercontent.com/104331549/188360734-675f584d-fedc-4f95-b2d3-6b80498eeece.png">


### 문제 해결
 - 정렬에서 문제가 있었던 것이다. 
 - 조합때 사용하는 자료구조는 그대로 두고, HashMap에 추가될 문자열을 정렬하여 해결하였다.
### 코드 
```java
public void combi(String s, int n, int r, int start, Stack<Character> stack, HashMap<String, Integer> menu){
    if(r ==0){ // 수정된 로직
        char[] chars = new char[stack.size()];
        int index = 0 ;
        for(char cha : stack){
            chars[index] = cha;
            index++;
        }
        Arrays.sort(chars);
        String combiString= new String(chars);
        int cnt =  menu.getOrDefault(combiString, 0);
        menu.put(combiString,cnt+1);
        return;
    }
    for(int i = start; i < n; i++){
        stack.add(s.charAt(i));
        combi(s, n, r-1, i+1 ,stack, menu);
        stack.pop();
    }
}
```
- 생각나는데로 적다보니, Combi 메소드인자가 너무 더러워졌다. 
- 만약 시간이 남는다면, menu나 ,stack같은 변수는 전역변수로 만들어 보다 깔끔한 코드를 사용할 수 있을 것 같다.

### 제출한 코드
```java

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        int max= 0;
        for(int i : course){
            HashMap<String, Integer> menu = new HashMap();
            Stack<Character> stack = new Stack<>();
            for (String s : orders){
                combi(s,s.length(), i,0,stack,menu);
            }
            List<Map.Entry<String, Integer>> entries = menu.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
            max = 0;
            for(Map.Entry<String, Integer> entry:entries){
                if(max <= entry.getValue() && entry.getValue() > 1){
                    answer.add(entry.getKey());
                    max = entry.getValue();
                }
            }

        }
        Collections.sort(answer);
        int index = 0;
        String[] result = new String[answer.size()];
        for(String ans: answer){
            result[index] = ans;
            index++;
        }
        return result;
    }

    public void combi(String s, int n, int r, int start, Stack<Character> stack, HashMap<String, Integer> menu){
        if(r ==0){
            char[] chars = new char[stack.size()];
            int index = 0 ;
            for(char cha : stack){
                chars[index] = cha;
                index++;
            }
            Arrays.sort(chars);
            String combiString= new String(chars);
            int cnt =  menu.getOrDefault(combiString, 0);
            menu.put(combiString,cnt+1);
            return;
        }
        for(int i = start; i < n; i++){
            stack.add(s.charAt(i));
            combi(s, n, r-1, i+1 ,stack, menu);
            stack.pop();
        }
    }
}
```
<img src="https://user-images.githubusercontent.com/104331549/188361328-ff4ae07e-dec7-43db-8da7-e450f821db9b.png">