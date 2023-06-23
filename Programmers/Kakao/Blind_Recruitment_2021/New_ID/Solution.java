package Programmers.Kakao.Blind_Recruitment_2021.New_ID;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    public String solution(String new_id) {

        // 1
        String answer = new_id.toLowerCase();

        // 2
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i < answer.length(); i++) {
            if(!check(answer.charAt(i))) list.add(i);
        }

        list.sort(Collections.reverseOrder());

        StringBuilder sb = new StringBuilder(answer);

        for(int i = 0; i < list.size(); i++) {
            sb.deleteCharAt(list.get(i));
        }

        answer = sb.toString();

        // 3
        boolean flag = true;
        while(flag) {
            if(answer.contains("..")) answer = answer.replace("..", ".");
            else flag = false;
        }

        // 4
        if(String.valueOf(answer.charAt(answer.length() - 1)).equals(".")) answer = answer.substring(0, answer.length() - 1);
        if(answer.length() > 0 && String.valueOf(answer.charAt(0)).equals(".")) answer = answer.substring(1);

        // 5
        if(answer.length() == 0) answer = "a";

        //6
        if(answer.length() >= 16) answer = answer.substring(0, 15);
        if(String.valueOf(answer.charAt(answer.length() - 1)).equals(".")) answer = answer.substring(0, answer.length() - 1);

        // 7
        if(answer.length() <= 2) answer = (answer + new String(new char[3 - answer.length()]).replace('\0', answer.charAt(answer.length() - 1)));

        return answer;
    }

    public boolean check(char c) {

        if(c >= 'a' && c <= 'z') return true;
        if(c >= '0' && c <= '9') return true;

        String s = String.valueOf(c);
        if(s.equals("-") || s.equals("_") || s.equals(".")) return true;

        return false;
    }
}
