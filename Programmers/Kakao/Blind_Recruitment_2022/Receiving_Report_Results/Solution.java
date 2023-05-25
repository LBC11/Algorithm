package Programmers.Kakao.Blind_Recruitment_2022.Receiving_Report_Results;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
1. 아이디어
    간단한 구현 문제
    But, 항상 어떠한 자료구조가 좋을지 고민하느라 시간을 많이 소비하는데
    좀 더 빠르게 구현하는 연습을 들이자!!!
 */
class Solution {

    HashMap<String, Set<String>> map;
    HashMap<String, Integer> idx;

    public int[] solution(String[] id_list, String[] report, int k) {

        int[] answer = new int[id_list.length];
        map = new HashMap<>();
        idx = new HashMap<>();

        for(int i = 0; i< id_list.length; i++) {
            map.put(id_list[i], new HashSet<>());
            idx.put(id_list[i], i);
        }

        for (String value : report) {
            String[] content = value.split(" ");

            map.get(content[1]).add(content[0]);
        }

        for (String s : map.keySet()) {
            if(map.get(s).size() >= k) {

                for (String s1 : map.get(s)) {
                    answer[idx.get(s1)]++;
                }
            }
        }

        return answer;
    }
}
