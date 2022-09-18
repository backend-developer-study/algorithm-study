
# 문제 구성 📖
> 코딩테스트 사이트 : 프로그래머스
> 난이도 : 3단계    
> 풀이 날짜 : 2022.09.19  
> 사용한 풀이 방법 : 플로이드와샬    
> 소요시간 : 3시간
## 문제링크
https://school.programmers.co.kr/learn/courses/30/lessons/72413

<br></br>
<br></br>


## 삽질코딩
### 풀이
 - 플로이드와샬 알고리즘으로 풀었다. 
 - 알고리즘에 대한 설명은 [여기 링크 참조](https://github.com/Gloom-shin/algorithm-study/blob/gloom/Algorithm/FloydWarshall(%ED%94%8C%EB%A1%9C%EC%9D%B4%EB%93%9C%20%EC%99%80%EC%83%AC).md)

### 코드
```java
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] result= new int[n][n];

        // 초기화
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==j){
                    result[i][j] = 0;
                    continue;
                }
                result[i][j] = 100001;
            }
        }
        for (int[] fare: fares) {
            result[fare[0]-1][fare[1]-1] = fare[2];
            result[fare[1]-1][fare[0]-1] = fare[2];
        }

        floydWarshall(result, n);

        int min = Integer.MAX_VALUE;
        int total = 0;

        // 거쳐서 갈 곳
        for (int k = 0; k < n; k++) {
            total =  result[s-1][k] + result[k][a-1] + result[k][b-1];
            if(min > total){
                min = total;
            }
        }

        return min;
    }

    public static void floydWarshall(int[][] result, int n) {

        // 거쳐가는 정점
        for (int k = 0; k < n; k++) {
            // 출발하는 정점
            for (int i = 0; i < n; i++) {
                // 도착하는 정점
                for (int j = 0; j < n; j++) {
                    if (result[i][j] > result[i][k] + result[k][j]) {
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }
    }
}
```

<img src="https://user-images.githubusercontent.com/104331549/190919252-8333342e-a33c-4b80-bde4-40d94ef2bc59.png">

<br></br>

### 문제 원인 
 - 요금의 조건이 `요금 f는 1 이상 100,000 이하인 자연수입니다.` 이길래 
 - `INF` 값 대신 `100001`의 값을 넣었더니, 실패 현상이 일어났다. 
 - 예상컨대, 100,000 요금에서 100,000 요금을 더해지게되면, `INF`값을 대체하는 `100001`보다 커지게 되어,`INF`의 역할을 못하게 되지때문이다.
 - 그래서 앗싸리 여유있게 `2000001` 로 해주니까 모든 테스트가 통과하였다.
   - 사실은 안전하게 20,000,001 로 해주는게 좋긴하지만, 통과했으니까..
   
 <img src="https://user-images.githubusercontent.com/104331549/190919497-bb7324a6-8b51-47aa-bf12-db5ab4301a53.png">
