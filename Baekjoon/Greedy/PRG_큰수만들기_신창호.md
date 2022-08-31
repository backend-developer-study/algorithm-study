
# 문제 구성 📖
> 코딩테스트 사이트 : 프로그래머스  
> 난이도 : 2단계    
> 풀이 날짜 : 2022.08.30  
> 사용한 풀이 방법 :    
> 소요시간 :
## 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42883
<br></br>
<br></br>


## 삽질코딩
### 풀이
 - 처음에는 그냥, 앞에서 부터 무조건 작은 수를 빼주려고만 했다.

### 코드
```java
class Solution {
    static String[] numbers= {"1","2","3","4","5","6","7","8","9"};
    public String solution(String number, int k) {
        
        for(String n : numbers){
            while(number.contains(n)){
                number = number.replaceFirst(n,"");
                k--;
                if(k == 0)break;
            }
           if(k == 0)break;
            
        }
        String answer = "";
        return number;
    }
}
```
<img src="https://user-images.githubusercontent.com/104331549/187363165-eb81718e-5059-4fcf-aaee-47d82537ae16.png">

<br></br>
<br></br>

## 앞자리부터 우선시, 
- 맨 앞자리에서 계산하면서, 작은 값을 없애주면 된다는 생각에, 
- 맨 앞자리부터 2개씩 비교하였다. 
  - 코드가 이미 없어서 기록해놓지는 못했지만, `next`값과 `current`값을 비교함.
  - `next` > `current` 일경우 , `current` 값 삭제
  - `next` <= `current` 일경우, `next = current` 로 변수 변환
### 에러 발생
- 하지만, 예제 코드 3번이 계속 실패..
  - number = `4177252841` , k= `4` 일떄 
    - 기대값 : `775841`   , 결과 :`477584`

<br></br>
<br></br>

## 계속 보관할 자료구조의 필요성 
- 이전 값들을 보관해야겠다는 생각이 들었다. 
- 그리고 하나씩 꺼내어 비교를 해야됨도 느껴, Stack자료구조를 사용하였다.

```java
package programmers.two;

import java.util.Stack;

public class makeBigNember {
    public static void main(String[] args) {
        Solution s = new Solution();
//        String s1 = s.solution("1924", 2);
        String s2 = s.solution("4321", 1);
        System.out.println("s2 = " + s2);
    }
}

class Solution {

    public String solution(String number, int k) {

        int next = 0;
        int current = number.charAt(0) - '0';
        Stack<Integer> stack = new Stack<>();
        stack.add(current);
        for (int i = 1; i < number.length(); i++) {
                next = number.charAt(i) - '0';
                while (!stack.isEmpty()) {
                    current = stack.pop();
                    if (current < next && k > 0) {
                        k--;
                    } else {
                        stack.add(current);
                        break;
                    }
                }
                stack.add(next);
        }
        StringBuffer sb =new StringBuffer();
        for (int i : stack){
            sb.append(i);
        }

        return sb.toString();
    }
}
```

<img src="https://user-images.githubusercontent.com/104331549/187364877-ff736e52-dc5d-4934-bca5-678d00608abb.png">

- 다 통과하지만, 12번만 실패하였다. 
- 원인은 마지막까지 제거할 값이 없을 경우, 적용이 안되는 것이였다.

<br></br>

### 12번 해결 코드 
```java
while (k > 0){
        stack.pop();
        k--;
        }
```

<br></br>
<br></br>

### 제출 코드

```java
import java.util.Stack;

class Solution {
    
    public String solution(String number, int k) {

        int next = 0;
        int current = number.charAt(0) - '0';
                Stack<Integer> stack = new Stack<>();
                stack.add(current);
        for (int i = 1; i < number.length(); i++) {
                    next = number.charAt(i) - '0';
            while (!stack.isEmpty()) {
                        current = stack.pop();
                if (current < next && k > 0) {
                            k--;
                } else {
                            stack.add(current);
                    break;
                }
            }
                    stack.add(next);
        }
        while (k > 0){
                    stack.pop();
                    k--;
        }
                StringBuffer sb =new StringBuffer();
        for (int i : stack){
                    sb.append(i);
        }

        return sb.toString();
    }
}
```

<img src="https://user-images.githubusercontent.com/104331549/187364890-f3810dc7-6a02-4edf-bda3-7a1329077762.png">
