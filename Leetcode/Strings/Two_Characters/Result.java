package Leetcode.Strings.Two_Characters;

import java.util.*;

public class Result {

    static Set<Character> chars;

    public static int alternate(String s) {

        chars = new HashSet<>();

        for(int i=0; i<s.length(); i++) {
            chars.add(s.charAt(i));
        }

        int idx = 0;
        char[] array = new char[chars.size()];
        for(char c : chars) {
            array[idx++] = c;
        }

        int max = 0;

        for (int i = 0; i<array.length; i++) {
            for(int j = i + 1; j<array.length; j++) {

                Stack<Character> stack = new Stack<>();

                boolean flag = true;
                for(int k = 0; k < s.length(); k++) {
                    char temp = s.charAt(k);
                    if(temp == array[i] || temp == array[j]) {
                        if(stack.isEmpty()) {
                            stack.add(temp);
                        }

                        else if(stack.peek() != temp) {
                            stack.add(temp);
                        }

                        else {
                            flag = false;
                            break;
                        }
                    }
                }

                if(flag) max = Math.max(max, stack.size());
            }
        }

        return max;
    }
}
