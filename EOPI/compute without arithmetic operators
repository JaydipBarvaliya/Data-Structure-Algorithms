public class Main {
    public static void main(String[] args) {

        int a = 7;
        int b = 50;
        System.out.println(approach1(a,b));
    }
    
     static int approach1(int a, int b){
         int i   = 0;
         int sum = 0;
         
        while(b!=0){
            
            if((b & 1) != 0){
                sum = addition(sum, a<<i);
            }
            b = b>>1;
            i = addition(i, 1);
        }
         return sum;
    }
    
      static int addition(int a, int b){
          
          int sum = 0;
          while(b!=0){
              sum = a^b;
              b = (a & b)<<1;
              a = sum;
          }
          return sum;
      }
}
