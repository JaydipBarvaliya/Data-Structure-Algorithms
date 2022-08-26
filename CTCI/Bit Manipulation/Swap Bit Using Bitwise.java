public class Main {
    public static void main(String[] args) {

        
        System.out.println(bruteForce(20, 2, 3));
        System.out.println(optimal(20, 2, 3));
        System.out.println(eightBit(45));
        
        //Here L is added at the end of the number to initialize number as a Long
        System.out.println(thirtyTwoBit(4294967293L));
        
        }
    
     static int bruteForce(int num, int i, int j){
        
          int bit1 = num & 1<<i;
          int bit2 = num & 1<<j;
        
        if(bit1 != 0){
            bit1 = 1;
        }
        
        if(bit2 != 0){
            bit2 = 1;
        }
            
        if(bit1 != bit2){
            
            if(bit1 == 0){
                num = num & ~(1<<j);
            }else{
                num = num | (1<<j);
            }

            if(bit2 == 0){
                num = num & ~(1<<i);
            }else{
                num = num | (1<<i);
            }
        }
         return num;
      }
    
    
    
    
     static int optimal(int num, int i, int j){
         
         if( ((num>>i)&1) != ((num>>j)&1)){
             int bitMask = 1<<i | 1<<j;
             num = num ^ bitMask;
         }
    return num;
}
    
    
    
    static int eightBit(int n){

            n = (n & 0xf0) >>4 | (n & 0x0f) <<4; 
            n = (n & 0xcc) >>2 | (n & 0x33) <<2;
            n = (n & 0xaa) >>1 | (n & 0x55) <<1;
            return n;
    }
    
    
    static long thirtyTwoBit(long n){

            n = (n & 0xffff0000) >>>16 | (n & 0x0000ffff) <<16; 
            n = (n & 0xff00ff00) >>>8 | (n & 0x00ff00ff)  <<8;
            n = (n & 0xf0f0f0f0) >>>4 | (n & 0x0f0f0f0f)  <<4;
            n = (n & 0xcccccccc) >>>2 | (n & 0x33333333)  <<2;
            n = (n & 0xaaaaaaaa) >>>1 | (n & 0x55555555)  <<1;
            return n;
    }
    
}
    
   
