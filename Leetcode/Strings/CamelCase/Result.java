package Leetcode.Strings.CamelCase;

class Result {

    public static int camelcase(String s) {

        int ret = 1;

        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) <= 90) ret++;
        }

        return ret;
    }

}