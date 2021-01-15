public class Main {
    public static void main(String[] args) {

        // First approach will not work if all the bits are one like 7 = 111
        System.out.println(approach1(92));
        System.out.println(approach2(92));
        System.out.println(approach3(92));
       
         }
    
    static int approach1(int org){
        
        int temp = org;
        int i = 0; 
        int diff = 0; 
        int finalNumber = 0; 
        
         while(temp!=0){
            
          int num  = org & (org-1);
          int mask = 1<<i;
          int checker  = num & mask;
            
            if(checker==0){
                num = num | mask;
            
                
                 if(num!=org){
                    if (diff == 0){
                        diff  = Math.abs(org-num);
                        finalNumber = num;
                    }else{
                        if( Math.abs(org-num) < diff ){
                            diff  = Math.abs(org-num);
                            finalNumber = num;
                        }
                    }
                 }
            }
            i++;
            temp = temp>>1;
        }
        if(finalNumber == 0){
            return 50000000;
        }
        return finalNumber;
    }
    
    static int approach2(int x){
        
        for(int i=0; i<64-1; i++){
            if( (x>>i & 1) != (x>>(i+1) & 1) ){
                x= x^ (1<<i | 1<<i+1);
                return x;
            }
        }
    return 500;
}

    static int approach3(int org){
         int num = 0;
         int weight = 0;
         int orgWeight = countWeight(org);
        for(int i=1; i<=1000; i++){
            num  = org + i;
            weight = countWeight(num);
            
            if(weight == orgWeight){
                return num;
            }
            
            num  = org - i;
            weight = countWeight(num);
            
            if(weight == orgWeight){
                return num;
            }
        }
         return 50000;
    }
    
     static int countWeight(int num){
        
        int i  = 0;
        int count  = 0;
        while(num!=0){
            if((num>>>i & 1) != 0 ){
                count++;
            }
            num  = num>>>1;
        }
       return count; 
    }
}
