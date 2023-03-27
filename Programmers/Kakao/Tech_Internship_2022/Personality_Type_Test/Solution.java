package Programmers.Kakao.Tech_Internship_2022.Personality_Type_Test;

import java.util.HashMap;

class Solution {

    // 각 유형별 char
    char[][] chars = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};

    // 각 성격별 점수를 저장할 장소
    HashMap<Character, Integer> map;

    public String solution(String[] survey, int[] choices) {

        // map init
        init();

        // calculate the point of each type
        calPoint(survey, choices);

        // return the result
        return getPersonality();
    }

    void init() {

        map = new HashMap<>();

        for (char[] cs : chars) {
            for (char c : cs) {
                map.put(c, 0);
            }
        }
    }

    private void calPoint(String[] survey, int[] choices) {

        for(int i=0; i<choices.length; i++) {

            int point = choices[i] - 4;

            char key;
            if(point <= 0) key = survey[i].charAt(0);
            else key = survey[i].charAt(1);

            map.put(key, map.get(key) + Math.abs(point));
        }
    }


    private String getPersonality() {

        StringBuilder sb = new StringBuilder();

        for (char[] c : chars) {
            if(map.get(c[0]) >= map.get(c[1])) sb.append(c[0]);
            else sb.append(c[1]);
        }

        return sb.toString();
    }

}
