
# ๋ฌธ์  ๊ตฌ์ฑ ๐
> ์ฝ๋ฉํ์คํธ ์ฌ์ดํธ : ํ๋ก๊ทธ๋๋จธ์ค 
> ๋์ด๋ : 2๋จ๊ณ    
> ํ์ด ๋ ์ง : 2022.09.05  
> ์ฌ์ฉํ ํ์ด ๋ฐฉ๋ฒ : ์กฐํฉ, ์์ ํ์, HashMap  
> ์์์๊ฐ : 1์๊ฐ 10๋ถ 
## ๋ฌธ์ ๋งํฌ
https://school.programmers.co.kr/learn/courses/30/lessons/72411

<br></br>
<br></br>


## ์ฝ์ง์ฝ๋ฉ
> ์ญ์ ์นด์นด์ค ๋ฌธ์ ..๊ฐ์ 2๋จ๊ณ๋ผ ํ ์ง๋ผ๋, ์ฒด๊ฐ๋์ด๋๋ ํจ์ฌ ๋์๋ค. 
> ์ผ๋จ ๊ฐ์ฅ๋จผ์ , ์๋๋ค์ ๋ฐ์ดํฐ๋ฅผ ์ด๋ป๊ฒ ๋ณด๊ดํด์ผ๋ ์ง ๋ถํฐ ์๊ฐํด๋ดค๋ค. 
### ํ์ด
 - ์๋ ์ฃผ๋ฌธ๋ฐ์ดํฐ๋ฅผ ๋ฉ๋ด๋ณ๋ก ๋๋  ๋ณด๊ดํ ๊น ํ์ง๋ง, ์๋ฏธ๊ฐ ์์๊ณ , A~Z๊น์ง๊ฐ ๋ฉ๋ด๋๊น, ๋ฐฐ์ด์ ๋ง๋ค์ด ๋ด์๋ณผ๊น ํด๋ ์คํ๋ ค ๋ณต์กํด์ง๋ ๋ก์ง์ด๋ค. 
 - ๋ฌธ๋ ์กฐํฉ์ ์๊ฐํด๋๊ณ , ๋ฏธ๋ฆฌ ์กฐํฉ๋ ๋ด์ฉ์ ๊ฐ์ง๊ณ  HashMap์ ์ ์ฅํจ์ผ๋ก์จ, ๊ฐฏ์๋ ์ธ๋ฉด ์ข๊ฒ ๋ค๋ ์๊ฐ์ ํ๋ค. 

#### ์์
 - Course์ ์กฐํฉํ  ๋ฉ๋ด์์ผ๋ก ํ๋์ฉ ์งํํ๋ค.(์ต๋๊ธธ์ด 10)
 - ์กฐํฉํ  ๋ฉ๋ด ๊ฐฏ์์ ๋ง์ถฐ 1๋ฒ ์๋๋ถํฐ ์กฐํฉ์ ์งํํ๋ค.(์ต๋ 20์๋)
   - ์กฐํฉ์ ๋์จ ๊ฒฐ๊ณผ๊ฐ์ ๋ง๋ค์ด ๋์ HashMap์ ์ง์ด ๋ฃ๋๋ค. 
   - ๋ชจ๋  ์๋์ ์งํํ๋ค๋ฉด, ๊ฐ์ฅ ํฐ์๋ฅผ ๋ฝ์, ๋ต๋ณ ๋ฐฐ์ด์ ์ถ๊ฐํ๋ค. 
 - ๋ต๋ณ๋ฐฐ์ด์, ๊ธธ์ด์ ์๊ด์์ด ์ค๋ฆ์ฐจ์์ผ๋ก ์ ๋ ฌํ๋ค.

### ์ฝ๋
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

### ๊ฒฐ๊ณผ
<img src="https://user-images.githubusercontent.com/104331549/188357777-77128cc7-cc5f-4b1d-a442-b8eeadd22332.png">

- ์์ ๋ ๋ค๋ฅด๊ฒ, ์ฃผ๋ฌธ๋ ์ฝ์ค์๋ฆฌ ์กฐํฉ์ ํ์๊ฐ 1๋ฒ๋ง ๋ถ๋ฆฐ๊ฒ, ์ต๋๊ฐ์ด๋ฉด ์์๊ฐ์ด ์ถ๋ ฅ๋์ด, ๋ฒ์๋ ์ ํด์คฌ๋๋,

### ์ถ๊ฐ๋ ์ฝ๋ 

```java
if(max <= entry.getValue() && entry.getValue() > 1){ // 1๋ณด๋ค ํด ๊ฒฝ์ฐ๋ง
    answer.add(entry.getKey());
    max = entry.getValue();
}
```

<img src="https://user-images.githubusercontent.com/104331549/188358014-f662756e-dba8-4bf8-993a-49563aed392b.png">

- ์์ 3๋ฒ ๋ต์ด ์ด์ํด์ ๋ค์ ํ๋ฒ ํ์ธํด ๋ณด๋
<img src= "https://user-images.githubusercontent.com/104331549/188358117-ca4f8bed-7c9b-4bad-b4a5-016b848143f3.png">

- ์ฃผ๋ฌธํ ์์์ ๊ด๊ณ์์ด ์ถ๋ ฅ๋จ์ ๊ณ ๋ คํด์ผ๋์, ์ด์ฐจํผ ๋ต์ ์ํ๋ฒณ ์ค๋ฆ์ฐจ์์ผ๋ก ์ ๋ ฌํด์ผ๋๊ธฐ์, 
- ์ ๋ ฌ ๋ฉ์๋๋ฅผ ํ๋๋ ์ถ๊ฐํด์คฌ๋ค.
- ํ์ง๋ง, ์ด๋ฒ์๋ ๊ณ์ ์๋์ ๊ฐ์ด ๊ฒฐ๊ณผ๋ฌผ์ด ๋์ค๋ ๊ฒ์ด๋ค. (`["XY"]` ๊ฐ ์ฌ๋ผ์ง)

<img src ="https://user-images.githubusercontent.com/104331549/188360734-675f584d-fedc-4f95-b2d3-6b80498eeece.png">


### ๋ฌธ์  ํด๊ฒฐ
 - ์ ๋ ฌ์์ ๋ฌธ์ ๊ฐ ์์๋ ๊ฒ์ด๋ค. 
 - ์กฐํฉ๋ ์ฌ์ฉํ๋ ์๋ฃ๊ตฌ์กฐ๋ ๊ทธ๋๋ก ๋๊ณ , HashMap์ ์ถ๊ฐ๋  ๋ฌธ์์ด์ ์ ๋ ฌํ์ฌ ํด๊ฒฐํ์๋ค.
### ์ฝ๋ 
```java
public void combi(String s, int n, int r, int start, Stack<Character> stack, HashMap<String, Integer> menu){
    if(r ==0){ // ์์ ๋ ๋ก์ง
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
- ์๊ฐ๋๋๋ฐ๋ก ์ ๋ค๋ณด๋, Combi ๋ฉ์๋์ธ์๊ฐ ๋๋ฌด ๋๋ฌ์์ก๋ค. 
- ๋ง์ฝ ์๊ฐ์ด ๋จ๋๋ค๋ฉด, menu๋ ,stack๊ฐ์ ๋ณ์๋ ์ ์ญ๋ณ์๋ก ๋ง๋ค์ด ๋ณด๋ค ๊น๋ํ ์ฝ๋๋ฅผ ์ฌ์ฉํ  ์ ์์ ๊ฒ ๊ฐ๋ค.

### ์ ์ถํ ์ฝ๋
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