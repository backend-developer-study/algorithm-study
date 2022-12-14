# 내용 구성 📖
> 개념 : 노션 정리
> 코드 : github

### 개념정리 
 - [노션 링크](https://rare-fire-f1c.notion.site/06b47bd4792f4fdba15b3de360e9d901)


### 연습 코드

```java
import java.util.ArrayList;
import java.util.PriorityQueue;


class Subject{
    ArrayList<Integer> preSubject ;

    public Subject(ArrayList<Integer> preSubject) {
        this.preSubject = preSubject;
    }

    public ArrayList<Integer> getPreSubject() {
        return preSubject;
    }
}
public class Topological {
    public static void main(String[] args) {
        Subject[] subject= new Subject[9];
        for (int i = 1; i < subject.length; i++) {
            subject[i] = new Subject(new ArrayList<>());
        }
        ArrayList<Integer> answer = new ArrayList<>();
        setSubjectGraph(subject);

        // 먼저 진입차수를 나타내는 배열부터 만들자
        int[] inDegrees = new int[9];
        for (int i = 1; i < subject.length; i++) {
            for (int next: subject[i].getPreSubject()) {
                inDegrees[next]++;
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 1; i < inDegrees.length; i++) {
            if(inDegrees[i] == 0){
                queue.offer(i);
            }
        }
        int node;
        while (!queue.isEmpty()){
            node = queue.poll();
            answer.add(node);
            for (int next:subject[node].preSubject){
                inDegrees[next]--;
                if (inDegrees[next] == 0){
                    queue.offer(next);
                }
            }
        }

        System.out.println("answer = " + answer);
    }

    private static void setSubjectGraph(Subject[] subject) {
        subject[1].getPreSubject().add(8);
        subject[2].getPreSubject().add(8);
        subject[3].getPreSubject().add(2);
        subject[4].getPreSubject().add(6);
        subject[5].getPreSubject().add(3);
        subject[6].getPreSubject().add(2);
        subject[7].getPreSubject().add(5);
        subject[7].getPreSubject().add(6);
    }


}

```