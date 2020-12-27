public class Main {
    public static void main(String[] args) {

        
        System.out.println(approach2("pale", "ple"));
        System.out.println(approach2("pales", "pale"));
        System.out.println(approach2("pale", "bale"));
        System.out.println(approach2("pale", "bae"));
        
    }
    
    static int getCharNumber(int val) {

        if (val >= 65 && val <= 90) {
            return val - 'A';
        }
        if (val >= 97 && val <= 122) {
            return val - 'a';
        }
        return -1;
    }

    static boolean approach2(String str1, String str2) {

        if (str1 == str2) {
            return false;
            
        } else if (str1.length() + 1 == str2.length()) {
            return oneEditinsertOrRemove(str1, str2);
            
        } else if (str1.length() - 1 == str2.length()){
            return oneEditinsertOrRemove(str2, str1);
            
        } else {
            return oneEditReplace(str1, str2);
        }
    }

    static boolean oneEditinsertOrRemove(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;

        while ( index1 < s1.length() && index2 < s2.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;

            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    static boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {

                if (foundDifference) {
                    return false;    
                }
                foundDifference = true;
            }
        }
        return true;
    }

}
