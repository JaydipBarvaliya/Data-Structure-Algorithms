public class Main {
    public static void main(String[] args) {

        System.out.println(brute(1024,19,2,6));
        System.out.println(optimal(1024,19,2,6));
    }
    
    static int brute(int n, int m, int p, int q){
        
        int bit = 0;
        
        for(int i=p; i<=q; i++){
            
            // find the first bit from m
            bit = m & 1; 
            
            //clear the bit at given position
            n = n & ~(1<<i);
            
            //update the bit at given position
            n = n | (bit<<i);
            
            //remove LSB from n 
            m = m >>> 1;
            
        }
        return n;
        
    }
    static int optimal(int n, int m, int i, int j){
        
        int allOnes = ~0;

        int left  = allOnes << (j+1);
        int right = (1<<i)-1;
        int mask  = left | right;
        
        n = n & mask;
        n = n | (m<<i);
        
        return n;
    }
}
