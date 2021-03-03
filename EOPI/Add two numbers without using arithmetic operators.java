public class Main {
    public static void main(String[] args) {

        
        int a = 55;
        int b = 101;
        int sum = 0;
        
        System.out.println(approach1(a,b,sum));
        System.out.println(approach2(a,b));
        System.out.println(approach3(a,b));
        
    }
    
     static int approach1(int a, int b, int sum){
        
       while(b!=0){
            sum = a ^ b;
            b   = (a & b) <<1;
            a   = sum;
        }
         return sum;
    }
    
    
    static int approach2(int a, int b){
        
        if(a == 0){
            return b;
        }
        
        if(b == 0){
            return a;
        }
        
        return approach2((a & b) <<1, a ^ b);
    }
    
    static int approach3(int a, int b){

        while(b!=0){
            a++;
            b--;
        }
        return a;
    }
}
