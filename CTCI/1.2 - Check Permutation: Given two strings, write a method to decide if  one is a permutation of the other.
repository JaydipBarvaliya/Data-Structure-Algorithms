
public class Main {
    public static void main(String[] args) {
        
        System.out.println(permutationChecker1("god", "dogg"));
        
        
    }
    
    static boolean permutationChecker1( String str, String perm){
        int[] arr= new int[128];
        char[] char_arr = str.toCharArray();
        if(str.length() != perm.length()){
            return false;
        }
        for (char c : char_arr) {
            arr[c]++;
        }
        for (int i=0; i<perm.length(); i++){
                arr[perm.charAt(i)]--;
                if(arr[perm.charAt(i)] < 0){
                    return false;
                }
        }
        return true;
    }
    static boolean permutationChecker2( String str, String perm){
        //second approch is to sort both the strings and compare them if they are same then one is permutation of             another
    }
}
