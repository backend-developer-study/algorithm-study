package 백준.단계별문제풀이.투포인터.문제2003;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public int solution(int N, int M, int[] arr) {
        int sum = 0;    // 합을 담을 변수
        int startPoint = 0;   // 왼쪽 포인터 역할
        int endPoint = 0;  // 오른쪽 포인터 역할
        int count = 0;  // 횟수를 증가시킬 변수
        int len = arr.length;
        while(true) {

            // sum이 M보다 큰 경우 목표값보다 값이 크므로 현재 startpoint를 인덱스로 가지는 원솟값을 합에서 뺀 후 인덱스를 증가시킨다.
           if(sum >= M) {
               sum = sum - arr[startPoint++];
           }
           else if(endPoint >= len) {
               break;
           }
           else {   // M보다 sum이 작은 경우
               sum = sum + arr[endPoint++];
           }

           if (sum == M) {
               count++;
           }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Main T = new Main();
        System.out.println(T.solution(N, M, arr));
    }
}
