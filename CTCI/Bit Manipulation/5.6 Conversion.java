public class Main {
    public static void main(String[] args) {

        System.out.println(optimal(1,4));
    }
    
    static int optimal(int num1, int num2){
        
        int count = 0;
        int mask = num1 ^ num2;
        
        while(mask!=0){
            mask= mask & (mask-1);
            count++;
        }
        return count;
    }
}
