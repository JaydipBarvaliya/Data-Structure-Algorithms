public class Main {
    public static void main(String[] args) {

        // bothSideBrute(13948);        
        
        //This both approches are not accurate in some case i.e 13948
        // System.out.println(nextLargest(13948));
        // System.out.println(preSmallest(13948));
        
        
        // System.out.println(optimalForLargest(13948));
        // System.out.println(optimalForSmallest(13948));
      
    }
    
    
    static void bothSideBrute(int num){
        
        int orgBits = totalNumberOfBits(num);
        
        for(int i=num+1 ; i<=65537; i++){
            if( orgBits == totalNumberOfBits(i)){
                System.out.println(i);
                break;
            }
        }
        
        for(int j = num-1; j>=0; j--){
            if( orgBits == totalNumberOfBits(j)){
                System.out.println(j);
                break;
            }
        }
    }
    
    static int totalNumberOfBits(int x){
        int count = 0;
        while(x!=0){
            x = x & (~(x & ~(x-1)));
            count++;
        }
        return count;
    }
    
    static int nextLargest(int x){
        int temp = x;
        int i = 0;
        
        while(temp!=0){
            if( ((x & (1<<i)) != 0)   &&  (x & (1<<(i+1))) == 0  ){
            x = x ^ ((1<<i) | (1 << (i+1)) );
            return x;
        }
         temp = temp>>>1;
         i++;
      }
        return 50000;
    }
    
    static int preSmallest(int x){
        int temp = x;
        int i = 0;
        
        while(temp!=0){
            if( ((x & (1<<i)) == 0)   &&  (x & (1<<(i+1))) != 0  ){
            x = x ^ ((1<<i) | (1 << (i+1)) );
            return x;
        }
         temp = temp>>>1;
         i++;
      }
        return 50000;
    }
    
    static int optimalForLargest(int num){
        
        int temp = num;
        int zeroCount = 0;
        int oneCount = 0;
        int i = 0;
        
        while(temp!=0){
            if( (temp & 1) != 0){
                oneCount++;
            }
            if( (temp & 1) == 0){
                zeroCount++;
            }
            
            if( ((num & (1<<i)) != 0)   &&  (num & (1<<(i+1))) == 0  ){
                
                num = num | (1 << (i+1));
                num = num & (-1 << i+1);
                
                // below step are the clear bifurcation of FS
                // a  = 1 << (c1  -1)       // 0s  with a  1   at  position c1 - 1 
                // b  =  a - 1		 	     // 0S  with ls  at  positions 0  through  c1 - 1
                // n  =  n | b     		 // inserts ls  at  positions 0  through c1 - 1
                    
                // FS   
                num = num | ((1<<(oneCount-1)) - 1);
                return num;
            }
            temp = temp >>> 1;
            i++;
        }
            return 50000;
    }
    
     static int optimalForSmallest(int num){
        
        int temp = num;
        int zeroCount = 0;
        int oneCount = 0;
        int i = 0;
        
        while(temp!=0){
            if( (temp & 1) != 0){
                oneCount++;
            }
            if( (temp & 1) == 0){
                zeroCount++;
            }
            
            if( ((num & (1<<i)) == 0)   &&  (num & (1<<(i+1))) != 0  ){
                
                
                num = num &  ~(1<<(i+1));
                num = num & (-1 << (i+1));
                
                //below step are the clear bifurcation of FS
                // int  a = 1 <<  (c1 + 1);  //  0s with 1  at  position (c1+  1) 
                // int  b  =  a  -1;              // 0s  followed by  c1+ 1  ones 
                // int  c  =  b << (c0 - 1);  // cl+l ones followed by c0-1  zeros.
                // n = n | c;
                
                //FS
                num = num |  ( ((1<<(oneCount+1))-1) << (zeroCount-1) );
                return num;
                
                
                
                
            }
            temp = temp >>> 1;
            i++;
        }
            return 50000;
    }
    
}
