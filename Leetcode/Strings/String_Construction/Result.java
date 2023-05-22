package Leetcode.Strings.String_Construction;

public class Result {

    static int[] lowercase = new int[26];

    public static int stringConstruction(String s) {

        for (int i = 0; i < s.length(); i++) {
            lowercase[s.charAt(i) - 'a']++;
        }

        int ret = 0;
        for (int i = 0; i < lowercase.length; i++) {
            if (lowercase[i] != 0) {
                lowercase[i] = 0;
                ret++;
            }
        }

        return ret;
    }
}
