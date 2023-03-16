package Programmers.Kakao.Blind_Recruitment_2023.Collection_Personal_Info;

import java.util.ArrayList;
import java.util.HashMap;

/*
추가 아이디어
굳이 날짜 형식에 얽매이지 않고 year*365 + month*28 + day 하면 한번에 비교할 수 있다.
 */
class Solution {

    int[] today_date;
    HashMap<Character, Integer> map;

    // 날짜 비교
    boolean compare(int[] date) {

        boolean flag = false;

        for (int i = 0; i < 3; i++) {

            // 오늘보다 이전이다.
            if (date[i] < today_date[i]) {
                flag = true;
                break;
            } else if (date[i] == today_date[i]) {
            } else {
                break;
            }
        }

        // 오늘이거나 이전이다.
        return flag;
    }

    // String date 를 int[] 형태로 변경
    int[] DateToIntArray(String s) {

        int[] ret = new int[3];

        String[] t = s.split("\\.");
        for (int i = 0; i < 3; i++) {
            ret[i] = Integer.parseInt(t[i]);
        }

        return ret;
    }

    // String date, 유형 에서 유효기간 만료날 구하기
    int[] getEndDate(String s) {
        int duration = map.get(s.charAt(s.length() - 1));

        int[] date = DateToIntArray(s.substring(0, s.length() - 2));

        date[2] -= 1;

        if(date[2] < 0) {
            date[2] += 28;
            date[1] -= 1;

            if(date[1] < 0) {
                date[1] += 12;
                date[0] -= 1;
            }
        }

        date[1] += duration;

        if (date[1] > 12) {

            // month 가 12 이상일 때
            if(date[1] % 12 == 0) {
                date[0] += (date[1] / 12 - 1);
                date[1] = 12;
            }

            else {
                date[0] += (date[1] / 12);
                date[1] %= 12;
            }
        }

        return date;
    }

    public int[] solution(String today, String[] terms, String[] privacies) {

        ArrayList<Integer> temp = new ArrayList<>();

        today_date = DateToIntArray(today);

        map = new HashMap<>();
        for (String s : terms) {
            String[] t = s.split(" ");
            map.put(t[0].charAt(0), Integer.parseInt(t[1]));
        }

        for (int i = 0; i < privacies.length; i++) {

            if (compare(getEndDate(privacies[i]))) temp.add(i + 1);
        }

        int[] ans = new int[temp.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = temp.get(i);
        }

        return ans;
    }
}
