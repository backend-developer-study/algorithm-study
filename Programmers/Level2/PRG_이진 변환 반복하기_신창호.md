# 문제 구성 📖

> 코딩테스트 사이트 : 프로그래머스
> 난이도 : 2단계    
> 풀이 날짜 : 2022.09.13  
> 사용한 풀이 방법 : 구현    
> 소요시간 : 25분

## 문제링크

https://school.programmers.co.kr/learn/courses/30/lessons/70129

<br></br>
<br></br>

## 삽질코딩

### 풀이

- 0과 1로 이루어진 어떤 문자열 x에 대하여 0을 제거하고, 제거한 길이를 다시 이진 문자열로 만들어 1이 될때까지 진행하는 문제이다.
    - 여기서 `1`이 될때까지라는 점을 처음엔 읽지 못해, 좀 헤맸었다..
- 제거한 총 회차와 0을 제거한 총 회수를 구하는 문제이다.

### 코드
 - 여기서 핵심은 `int`타입인 숫자를 이진 String으로 변환해주는 `Integer.toBinaryString()` 함수이다.
```java
class Solution {
  static int len = 0;
  static String str;
  static int[] answer = new int[2];
  static int remove =0;
  static boolean check = true;
  public static int[] solution(String s) {
    len = s.length();
    str = s;
    remove = 0;
    check =true;
    // 1 3 7 15
    while (check) {
      loop();
      str = Integer.toBinaryString(len);
      if(str.equals("1"))break;
      len = str.length();
    }
    answer[0] = remove;
    return answer;
  }

  public static void loop(){
    int cnt = 0;
    for(int i= 0; i< len; i++){
      if(str.charAt(i) == '0'){
        cnt++;
      }
    }

    answer[1] += cnt;
    remove++;
    len -= cnt;
  }
    public static String convert() {    //필요없는 코드
        return Integer.toBinaryString(len);
    }

    public static boolean check() {
        if (len == 1) {
            return false;
        }
        return !(Math.sqrt(len + 1) % 2 == 0.0);  // 잘못된 접근법
    }

}
```

> 하지만, 코드가 더러워서 리팩토링을 진행했다.

<img src="https://user-images.githubusercontent.com/104331549/189784021-1580c2ba-d869-4a67-a453-ec2cae8c8bcc.png">

### 다시 고친 코드 
 - 리펙토링을 하면서 느낀건데, 굳이 하나하나 0을 제거하는 방법보단, 0을 모두 제거하고 비교하는 방식으로도 가능하다는 것을 깨달았다.
```java
class Solution {
  public static int[] solution(String s) {
    int len = 0;
    int replaceLen = 0;
    int cnt =0;
    int[] answer = new int[2];

    while (!s.equals("1")) {
      len = s.length();
      s = s.replaceAll("0", "");
      replaceLen = s.length();
      s =  Integer.toBinaryString(replaceLen);
      cnt = len - replaceLen;
      answer[0]++;
      answer[1] += cnt;

    }

    return answer;
  }
}
```
> 코드는 깔끔하게 정렬되었지만, 속도는 오히려 많이 늘어났다. 확실히 replaceAll()보다는 for문이 빠른 연산인거 같다.

<img src="https://user-images.githubusercontent.com/104331549/189783978-d221fdc6-3266-481d-80ac-13c42a0ff307.png">