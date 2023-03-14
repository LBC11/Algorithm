package Programmers.Kakao.boat;

import java.util.HashMap;

public class Solution {

    public int solution(int[] people, int limit) {
        int answer = 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int person : people) {
            map.put(person, map.getOrDefault(person, 0) + 1);
        }

        // limit 에 해당하는 크기는
        for(int i=limit - 10; i > limit / 2; i -= 10) {

            int num = map.get(i);

            int temp = 0;
            while(num > temp) {
                temp += map.get(limit - i);
            }
        }

        return answer;
    }
}
