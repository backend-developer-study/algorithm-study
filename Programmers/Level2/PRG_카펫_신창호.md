
# 문제 구성 📖
> 코딩테스트 사이트 : 백준  
> 난이도 : 2단계    
> 풀이 날짜 : 2022.08.26  
> 사용한 풀이 방법 : 완전탐색, 수학     
> 소요시간 : 15분
## 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/42842

<br></br>
<br></br>


## 삽질코딩
### 풀이
 - `Brown`의 범위가 `8~5000`까지길래
```text
12
12 1
6 2
4 3
```
- `Yellow` + `Brown`을 더해서, 약수를 구한다음 
  - 가로가 `W` 세로가 `H`라 했을 때 
  - Yellow = (H-2)(W-2)
  - Brown = 2(H+W-2)
  - 위 조건중 하나라도 맞으면 리턴하였다. 
  
### 코드
```java
class Solution {
    public int[] solution(int brown, int yellow) {
        
        int sum = brown + yellow;
        int W = 0;
        int H = 0;
        
        for(int i = 1; i<= Math.sqrt(sum); i++){
            if(sum % i ==0){
                W = sum/i;
                H = i;
                if(2*(W+H-2) == brown) {
                    break;
                }
            }
        }
        int[] answer = {W,H};
        return answer;
    }
}
```

<img src="https://user-images.githubusercontent.com/104331549/187580791-90747f3a-57d8-454d-b3c9-801e194ff792.png">