package Programmers.Kakao.Blind_Recruitment_2021.Menu_Renewal;

import java.util.*;

/*
주요 아이디어
1. bitmask 을 통해 알파벳 조합을 간단하게 표현하여 중복 계산 제외(dp)
2. n개의 조합은 (n-1) + 1 을 통해 순차적으로 조합
3. size 가 0인 것은 미리 삭제
 */
class Solution {

    // course 크기, 알파벳 조합, 손님 번호
    Map<Integer, Map<Integer, HashSet<Integer>>> map;
    ArrayList<String> ans;

    int max;

    public String[] solution(String[] orders, int[] course) {

        map = new HashMap<>();
        ans = new ArrayList<>();
        max = course[course.length - 1];

        for(int i = 1; i <= max; i++) {
            map.put(i, new HashMap<>());
        }

        Map<Integer, HashSet<Integer>> menus = map.get(1);
        for (int i = 0; i < orders.length; i++) {
            for(int j = 0; j < orders[i].length(); j++) {

                int key = stringToInt(orders[i].charAt(j));

                if(!menus.containsKey(key)) menus.put(key, new HashSet<>());
                menus.get(key).add(i + 1);
            }
        }

        for(int i = 1; i < max; i++) {
            for (int key1 : map.get(i).keySet()) {
                for(int key2 : menus.keySet()) {

                    // 첫번쨰 조건: 요리가 1개 + 요리가 1개인 경우를 위해
                    // 두번째 조건: 둘의 집합에서 같은 알파벳이 없는 경우
                    // 세번째 조건: 이미 계산한 경우가 아닌 경우
                    if(key1 != key2 && (key1 & key2) == 0 && !map.get(i + 1).containsKey(key1 + key2)) {
                        map.get(i + 1).put(key1 + key2, new HashSet<>());

                        for (int num : menus.get(key2)) {
                            // 둘의 조합에서 같은 손님 번호가 있는 경우
                            if(map.get(i).get(key1).contains(num)) map.get(i + 1).get(key1 + key2).add(num);
                        }
                    }
                }
            }

            // 다음의 계산을 용이하게 하기위해 size 가 0이면 삭제한다.
            ArrayList<Integer> temp = new ArrayList<>();
            for (int key : map.get(i + 1).keySet()) {
                if(map.get(i + 1).get(key).size() == 0) temp.add(key);
            }

            for (int key : temp) {
                map.get(i + 1).remove(key);
            }
        }

        for(int num : course) {

            // course 는 최소 단품 메뉴 2개 이상을 주문해야 하기 떄문에
            int size = 2;
            ArrayList<String> temp = new ArrayList<>();

            for(int key : map.get(num).keySet()) {
                if(size < map.get(num).get(key).size()) {
                    temp.clear();
                    temp.add(intToSting(key));
                    size = map.get(num).get(key).size();
                }

                else if (size == map.get(num).get(key).size()) temp.add(intToSting(key));
            }

            ans.addAll(temp);
        }

        // 사전순으로 정렬
        Collections.sort(ans);

        return ans.toArray(String[]::new);
    }

    public int stringToInt(char c) {
        return (1 << (c - 'A'));
    }

    public String intToSting(int num) {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i <= 'Z' - 'A'; i++) {
            if((num & (1 << i)) != 0) sb.append((char) ('A' + i));
        }

        return sb.toString();
    }
}