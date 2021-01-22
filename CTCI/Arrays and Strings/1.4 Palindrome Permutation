public class Main {
    public static void main(String[] args) {
        String phrase = "abbacd";
        System.out.println(approach1(phrase));
    }


    
    
    
    
     static boolean approach1(String phrase){
         
         int oddcount = 0;
         int[] arr = new int[128];

         for (char c: phrase.toCharArray()) {
             arr[c]++;
         }

         for (int i: arr) {
             if (i % 2 != 0 && ++oddcount > 1) {
                 return false;
             }
         }
         return true;
         
     }
    
    
    
    
    
    
    
    
    static boolean approach2(String phrase){
        int vector = 0;
        for (char c: phrase.toCharArray()) {
            int maskIndex = getCharNumber(c);
            vector = toggle(vector, maskIndex);
        }
        if((vector & (vector-1)) == 0){
            return true;
        }
        return false;
    }
    
    static int toggle(int vector, int maskIndex) {

        int mask = 1 << maskIndex;
        
        if ((vector & mask) == 0) {
            vector = vector | mask;
        } else {
            vector = vector & ~mask;
        }
        return vector;
    }
    
    static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);

        if (a <= val && val <= z) {
            return val - 'a';
        }
        return -1;
    }
    
    
    
    
    
    
    
    
}
