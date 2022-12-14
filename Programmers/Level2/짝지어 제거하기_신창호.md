
# 문제 구성 📖
> 코딩테스트 사이트 : 프로그래머스  
> 난이도 : 2단계      
> 풀이 날짜 : 2022.09.14   
> 사용한 풀이 방법 : stack   
> 소요시간 : 15분
## 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/12973

<br></br>
<br></br>


## 삽질코딩
### 풀이
 - 두개씩 짝지어 제거하기에, stack 자료구조가 생각났다.

### 코드
```java
import java.util.Stack;

class Solution
{
    public int solution(String s)
    {
       int amswer = 0;
        Stack<Character> stack =new Stack<>();
        stack.push(s.charAt(0)); // 첫번째 값 입력

        for (int i = 1; i < s.length(); i++) {
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
                continue;
            }
            else{
                if(stack.peek()== s.charAt(i)){
                    stack.pop();
                }
                else{
                    stack.add(s.charAt(i));
                }
            }
        }
        if(stack.isEmpty())
            amswer = 1;
        return amswer;
    }
}
```

<img src="https://user-images.githubusercontent.com/104331549/190072080-29730945-8c57-4faa-8920-918478ec71e0.png">