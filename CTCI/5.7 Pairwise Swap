public class Main {
    public static void main(String[] args) {
        
        // for the sack of practice.. Binary swapping is done by this  
        // approach
        System.out.println(binarySwap(9));
        
        //solution is as per question given in CTCI
        System.out.println(optimal(9));
        
    }
    
    static int binarySwap(int num){
        
        int i = 0;
        int temp = num;
        temp = temp >> 1;
        
        while(temp != 0){
            
            if( (num>>i & 1)  !=  (num>>i+1 & 1) ){
                num = num ^ (1<<i | 1<<(i+1));
            }
            temp = temp >> 1;
            i++;
        }
    return num;        
    }
    
    static int optimal(int x){
        
        // Get all even bits of x
         int even_bits = x & 0xAAAAAAAA; 
 
        // Get all odd bits of x
         int odd_bits  = x & 0x55555555; 
 
        even_bits >>= 1;  // Right shift even bits
        odd_bits <<= 1;   // Left shift odd bits
 
        // Combine even and odd bits
        return (even_bits | odd_bits); 
        
    }
}
