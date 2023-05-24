package Programmers.Kakao.Blind_Recruitment_2022.Receiving_Report_Results;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
