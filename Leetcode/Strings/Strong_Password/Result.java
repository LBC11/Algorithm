package Leetcode.Strings.Strong_Password;

public class Result {

    static String numbers = "0123456789";
    static String lower_case = "abcdefghijklmnopqrstuvwxyz";
    static String upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String special_characters = "!@#$%^&*()-+";

    public static int minimumNumber(int n, String password) {

        boolean[] array = new boolean[4];

        int lack_length = Math.max(6 - n, 0);

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if(c >= 48 && c <= 57) array[0] = true;
            else if(c >= 65 && c <= 90) array[1] = true;
            else if(c >= 97 && c <= 122) array[2] = true;
            else array[3] = true;
        }

        int lack_types = 0;
        for(int i=0; i<array.length; i++) {
            if(!array[i]) lack_types++;
        }

        return Math.max(lack_types, lack_length);
    }

    public static void main(String[] args) {

        System.out.println((int) '0');
        System.out.println((int) '9');
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');


    }

}
