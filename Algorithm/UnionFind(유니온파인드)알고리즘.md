# 내용 구성 📖
> 사용할 알고리즘 : 유니온파인드(UnionFind Algorithm)    
> 응용분야 : 같은 그래프에 속해 있는지 유무 파악  
> 같이 알아보면 좋은 알고리즘 : Kruskal Algorithm  

# 유니온파인드 알고리즘이란?
- 그래프 알고리즘으로 두 노드(=지점,도시등)가 같은 그래프에 속하는 지 판별하는 알고리즘이다.
  - 판별이 핵심
- 서로소 집합, 상호 배타적 집합으로도 불린다.
- 트리 구조로 이루어진 자료구조이다.

> 다시한번 정리하자면   
> 노드 = 정점 = 도시  
> 간선 = 거리 = 비용

<br></br>
<br></br>

# 예시 상황
> 각 독립적으로 자유분방하게 여러 노드가 존재한다고 가정하자  
>아래 그림은 6개의 노드가 존재하고, 모두 자기 자신을 가리킨다고 할 수가 있다.
<p align="center"><img src="https://user-images.githubusercontent.com/104331549/175772708-111b0929-2f8a-467f-881c-44a97ddd49c1.png" width="80%"></p>

- 여섯개의 노드가 가리키는 부모노드는 자기 자신임으로 배열을 사용하여 아래와 같이 표현할 수 있다.

<img src="https://user-images.githubusercontent.com/104331549/175772859-ef9f1b89-1e5d-4e85-87ab-68a3d2024b2f.png">

### 첫번째 노드 연결
> 1번 노드와 2번 노드가 연결되었다고 해본다.
<p align="center"><img src="https://user-images.githubusercontent.com/104331549/175772945-65516d0b-e5ac-4ee3-b9e9-4fdaafb37e93.png" width="80%"></p>

- 이때 컴퓨터상에서 1과 2가 연결되었다는 것을 표현하기 위해, 1번 노드와 2번 노드 중에 작은 수를 배열의 값으로 넣어준다.

<img src="https://user-images.githubusercontent.com/104331549/175773091-23797bb5-ba2c-4752-8d74-29b7c3b95a69.png">

- 이렇게 부모를 합칠때 일반적으로 더 작은값쪽으로 합치는데, 이것을 `Union`이라고 한다.

### 두번째 노드 연결
> 이번에는 2번과 3번 노드가 연결 되었다고 해보자.
<p align="center"><img src="https://user-images.githubusercontent.com/104331549/175773117-a84e4fda-89d2-47e1-934b-3bb413b55a3f.png" width="80%"></p>
<img src="https://user-images.githubusercontent.com/104331549/175773204-6ec7033e-81db-42a1-a701-fb9a35820251.png">

- 당연히 이번에도, 2번 노드와 3번 노드중에 작은 수로 값을 넣어준다.

> 여기서 유니온 파인드의 핵심,   
> 1번 노드와 3번 노드는 같은 그래프에 있는데, 어떻게 연결되었다고 파악할 수 있을까요?

- 각 부모 노드만 보고는 한번에 파악할 수 없으니, `재귀함수`로 확인을 한다.
- 3번 노드의 부모인 2번노드를 찾고, 또 다시 2번노드의 부모인 1번 노드를 찾아 올라가는 형태인 거죠.
- 찾은 부모의 노드가 자기 자신을 가리켰을 때, 더이상 부모노드가 없음을 확인하고 그 값을 넣는 것이죠,

<img src="https://user-images.githubusercontent.com/104331549/175773378-c52021db-c450-44a0-8ccd-43d685354468.png">

- 위와 같이 세가지 노드의 가리키는(부모노드)의 값이 1이기 때문에, 모두 같은 그래프에 속한다고 할 수 있다.
- 이것이 바로, Union으로 찾았다고 해서, `Union-Find`라고 한다.

> 코드로 보면 이해가 될 것이다.

<br></br>
<br></br>

# 코드로 짜보기<a name="exampleCode"></a>

### getParent 메소드
- 먼저 부모노드를 재귀함수로 구현을 해야한다.
- 탈출 조건은 부모노드가 자기자신이면 뿌리라는 것이니 탈출한다.

```java
// 부모 노드 얻기
public static int getParent(int[] parent, int x){
    if(parent[x] == x) return x; // 자기자신을 가리킨다면 반환 
    return parent[x] = getParent(parent, parent[x]);
}
```

<br></br>


### unionParent 메소드
- 그 다음이제 두 노드를 연결한다고 할 때, 두 노드가 가리키는 부모노드를 통일 시켜야한다.
- 여기서 작은 수로 합쳐주면 된다.
```java
// 두 부모 노드 합치기
public static void unionParent(int[] parent, int x, int y){
        x = getParent(parent, x);
        y = getParent(parent, y);
        if(x > y)  parent[x] = y;
        else parent[y] =x ;
    }
```

<br></br>


### findParent
- 마지막, 유니온파인드의 핵심
- 두 노드가 같은 그래프에 존재하는지 알려주는 메소드를 만들면 된다.
- 비교하는 방법은, 두 부모노드가 같은지 유무만 파악하면 된다.
```java
public static boolean findParent(int[] parent, int x, int y){
        x = getParent(parent, x);
        y = getParent(parent, y);
        return x == y;
        
        //풀어 쓰면, 
        //if(x==y) return true;
        //else return false;
    }
```
<br></br>
<br></br>

# 유니온파인드 테스트 코드

```java
import java.util.Arrays;
public class unionFindTest {
    // 부모노드 얻기
    public static int getParent(int[] parent, int x){
        if(parent[x] == x) return x;
        return parent[x] = getParent(parent, parent[x]);
    }
    // 두 부모 노드 합치기
    public static void unionParent(int[] parent, int x, int y){
        x = getParent(parent, x);
        y = getParent(parent, y);
        if(x > y)  parent[x] = y;
        else parent[y] =x ;
    }
    // 부모가 같은지 확인 -> 즉, 같이 연결되어 있는지 확인
    public static boolean findParent(int[] parent, int x, int y){
        x = getParent(parent, x);
        y = getParent(parent, y);
        return x == y;
    }
    
    
    // 테스트 코드 
    public static void main(String[] args) {
        
        int[] parent = new int[7];
        for(int i =1 ; i< parent.length; i++){
            parent[i] = i; // 모든 부모노드 초기화
        }
        unionParent(parent, 1,2);
        unionParent(parent, 2,3);
        unionParent(parent, 3,4);
        unionParent(parent, 5,6);

        findParent(parent, 1,4); // true
        findParent(parent, 4,5); // false
    }

}
```