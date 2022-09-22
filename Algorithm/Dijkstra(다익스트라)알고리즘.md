# 내용 구성 📖
> 사용할 알고리즘 : 다익스트라(Dijkstra Algorithm)  
> 응용분야 : 한 정점의 최단경로  
> 같이 알아보면 좋은 알고리즘 : 플로이드 와샬 알고리즘


# 다익스트라 알고리즘이란?
 - 다이나믹 프로그래밍을 활용한 가장 대표적인 최단 경로 탐색 알고리즘
    - 하나의 최단 거리를 구할 때, 그 이전까지 구했던 최단 거리 정보를 활용하여 최단거리를 구하기때문에, 다이나믹 프로그래밍이라고 할 수 있다.
 - 한 정점에서 다른 모든 정점으로 가는 최단 경로를 알려준다. 
   -  단, 음의 간선이 있을 경우 포함할 수 없다.
 - 흔히, 인공위성 GPS 소프트웨어 등에서 사용
 - 내가 원하는 한 도시(지점)까지 가는 최단 비용 혹은 경로를 구하기 좋다.
> 다시한번 정리하자면   
> 노드 = 정점 = 도시  
> 간선 = 거리 = 비용

<br></br>
<br></br>

# 예시 상황
> 보다 좋은 이해를 위해 예시상황을 만들어 보자
> 아래 그래프에서 1번 노드에서 다른 모든 노드로 가는 최단거리를 구해보자.

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/191706518-5e71baae-375d-4667-ac84-1b0bab1ef8bb.png" width="65%"></p> 

<br></br>

## 초기값 정하기
 - 초기값은 1노드에서 현재 갈수 있는 위치만 계산하는 형태이다. 
 - 아래 그림처럼, 1노드에서 갈수 있는 곳은 2번, 4번 노드이며, 갈수 없는 곳은 `INF`로 `최단거리 배열`을 초기화를 한다. 

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/191718054-33e17528-70a8-48ee-8e6c-d1037e32ce15.png" width="65%"></p> 

- 다음 이동할 수 있는 노드를 별도의 공간에 저장한다.

<br></br>

## 가장 최단거리 비용이 작은 곳 부터 
- 다익스트라 알고리즘의 가장 핵심이 되는 부분이라 생각이 드는 곳인데, 
- 현재 노드를 기준으로 비용이 가장 작은곳에서 뻗어나가는 형태로 최단거리를 다시 계산한다.

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/191718558-b4f27314-8c64-4008-9f48-f6c14069191e.png" width="65%"></p> 

- 물론, 비용이 작은 순서대로 꺼내야 하기에, 우선순위 큐를 사용하는 형태이며, 비용의 기준점은, `최단거리 배열`을 기준으로 진행한다.
- 최단 거리비용이 작은 곳 부터 순환을 해야하기에, Java의 경우 Class형태로 많이 만든다. 
  - 또한 이미 방문했던 노드는 다시 방문하지 않게, 방문이력도 관리해줘야한다.

<br></br>

## 그다음 노드 구하기

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/191719580-c8a0e9d1-b22e-41db-b511-cf4e38d64db2.png" width="65%"></p> 

 - 위 단계까지 진행했다면, 남은 노드는 `3`,`4`,`5` 노드이다.
 - 최단거리 배열을 보면, 각 노드 별로 `3번`노드는 4, `4번`노드는 2, `5번`노드는 3으로 4번노드가 제일 작기에, 여기서 다시 시작하는 것이다.
   - 절대로 `Queue`로직으로 `pop`되어 계산하는게 아님을 유의하자!

<br></br>

## 마지막 계산 
<p align="center"><img src="https://user-images.githubusercontent.com/104331549/191720244-951d76ca-d10e-4b09-8519-f1715e07867e.png" width="65%"></p> 

- 3번 노드, 5번 노드가 남았지만, 이번에도 5번 노드가 최단거리 비용이 더 작기때문에, 5번 노드 중심으로 최단거리를 계산을 한다.
- 그리고 마지막으로, 3번 노드의 계산을 해야되지만, 이미 3번을 제외한 모든 노드를 방문했기때문에 바로 반환되며, 계산이 종료된다.

<br></br>
<br></br>


# 코드로 짜보기
- 다익스트라의 경우 인접행렬로 구하게되면, 시간복잡도가 O(N^2)가 걸려, 효율성이 떨어질수 가 있다. 
- 그러므로 인접 리스트 방식을 활용하면, O(NlogN)으로 효율적이게 짤 수 있다.

- 먼저 인접리스트부터 만들어 보자

## 인접리스트 만들기
 - 총 노드의 갯수는 5개이며, 간선은 총 6개이다. 
 - 간선은 양방향 적용이 가능하다.
 - 여기서 자료구조를 잘 생각해야한다. 구성해야할 인접 리스트는 아래와 같다.
 - 또한, 인접리스트 안에 구성하고 있는 요소들은 ArrayList이며, ArrayList안에 구성하고 있는 요소들은 Class이다. 
 - 즉, 도착노드와 비용을 인자로 가지는 `Class`도 만들어 줘야한다.

<p align="center"><img src="https://user-images.githubusercontent.com/104331549/191729928-8db8a7b9-0976-48e6-ac70-d07cf4dceb97.png" width="65%"></p>

 - 1번 노드는 갈수 있는 곳이 2번과 4번 두곳이며, 각각의 비용은 1과 2만큼 든다. 
 - 2번 노드는 갈수 있는 곳이 총 3곳이며, 1번, 3번, 5번으로 갈 수 있다.(비용이야기 생략)
 - 이렇게 고려할 사항을 생각하여 자료구조를 만들어야 한다.


```java
public class Delivery {
    // 첫번째 인자는 출발 노드
    // 두번째 인자는 도착 노드
    // 세번째 인자는 간선 비용
    static int[][] road = {
            {1,2,1},
            {2,3,3},
            {5,2,2},
            {1,4,2},
            {5,3,1},
            {5,4,2}
    };

    public static void main(String[] args) {
        //인접 리스트 만들기,
        ArrayList<Node>[] adjList = new ArrayList[6]; // 노드 갯수 + 1만큼 배열을 만든다.

        for (int i = 0; i < 6; i++) {
            adjList[i] = new ArrayList<>(); // 배열 내부, ArrayList로 초기화
        }

        for (int[] r:road) {
            Node node1 = new Node(r[1], r[2]);
            Node node2 = new Node(r[0], r[2]);

            adjList[r[0]].add(node1); // 양방향이기에
            adjList[r[1]].add(node2); // 양방향이기에
        }

        System.out.println("adjList = " + adjList);
    }

    static class Node implements Comparable<Node> {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int getEnd() {
            return end;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
```
 - 이로써, 초기세팅이 끝났다. 이제는 Dijkstra Algorithm을 만들어보자

## Dijkstra 코드구현
```java
public class Delivery {
    //생략..

public static void dijkstra(int start, ArrayList<Node>[] adjList){
        boolean[] visited = new boolean[5+1]; // 방문 노드 체크
        int[] distance = new int[5+1]; // 최단거리 배열
        int INF = 123456;
        Arrays.fill(distance, INF); // 배열 초기화
        distance[start] = 0; // 시작지점 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue();
        priorityQueue.offer(new Node(start,0)); // 시작점 넣어주기

        while (!priorityQueue.isEmpty()){
            //현재 지점 추출
            int current = priorityQueue.poll().getEnd();

            for(Node next :adjList[current]){ // 현재 노드에서 다음 노드로 갈수 있는 곳 한번씩 훏어보기
                if(distance[next.getEnd()] > distance[current] + next.distance){ //적혀있는 최단거리보다, 새로 탐색한 곳의 거리비용이 더 작다면
                    distance[next.getEnd()] = distance[current] + next.distance;
                    priorityQueue.offer(new Node(next.getEnd(), distance[next.getEnd()]));
                }
            }
        }
    }
}
```
 - 우선순위 큐에, 갱신되는 노드의 최단거리를 넣어줌으로써, 계속해서 최단거리 비용이 작은 곳 부터 꺼내어 계산이 가능해진다. 
 - 여기서 최종적으로 얻어야할 자료는 최단거리 배열 `distance[]`이다.

### 실행결과 
```
distance = [INF, 0, 1, 4, 2, 3] // 인덱스 0은 무시해도 된다.
```
 - 최종적으로 1번 노드에서 출발하여, 각 노드까지 도달하기위한 최단거리 계산이 끝났다.


## 전체코드

```java
public class Delivery {
    // 첫번째 인자는 출발 노드
    // 두번째 인자는 도착 노드
    // 세번째 인자는 간선 비용
    static int[][] road = {
            {1,2,1},
            {2,3,3},
            {5,2,2},
            {1,4,2},
            {5,3,1},
            {5,4,2}
    };
    static int[] distance = new int[5+1];
    public static void main(String[] args) {
        //인접 리스트 만들기,
        ArrayList<Node>[] adjList = new ArrayList[5+1]; // 노드 갯수 + 1만큼 배열을 만든다.

        for (int i = 0; i < 6; i++) {
            adjList[i] = new ArrayList<>(); // 배열 내부, ArrayList로 초기화
        }

        for (int[] r:road) {
            Node node1 = new Node(r[1], r[2]);
            Node node2 = new Node(r[0], r[2]);

            adjList[r[0]].add(node1); // 양방향이기에
            adjList[r[1]].add(node2); // 양방향이기에
        }

        dijkstra(1, adjList);
        System.out.println("distance = " + Arrays.toString(distance));
    }
    public static void dijkstra(int start, ArrayList<Node>[] adjList){
        boolean[] visited = new boolean[5+1]; // 방문 노드 체크
        int INF = 123456;
        Arrays.fill(distance, INF); // 배열 초기화
        distance[start] = 0; // 시작지점 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue();
        priorityQueue.offer(new Node(start,0)); // 시작점 넣어주기

        while (!priorityQueue.isEmpty()){
            //현재 지점 추출
            int current = priorityQueue.poll().getEnd();

            //방문했던곳 제외 로직
            if(visited[current]) continue;
            visited[current] = true;

            for(Node next :adjList[current]){ // 현재 노드에서 다음 노드로 갈수 있는 곳 한번씩 훏어보기
                if(distance[next.getEnd()] > distance[current] + next.distance){ //적혀있는 최단거리보다, 새로 탐색한 곳의 거리비용이 더 작다면
                    distance[next.getEnd()] = distance[current] + next.distance;
                    priorityQueue.offer(new Node(next.getEnd(), distance[next.getEnd()]));
                }
            }
        }
    }


    static class Node implements Comparable<Node> {
        int end;
        int distance;

        public Node(int end, int cost) {
            this.end = end;
            this.distance = cost;
        }

        public int getEnd() {
            return end;
        }

        public int getCost() {
            return distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
```

