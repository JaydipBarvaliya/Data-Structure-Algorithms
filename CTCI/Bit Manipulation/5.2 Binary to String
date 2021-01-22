public class Main {
    public static void main(String[] args) {

        System.out.println(approach1(0.625));
        System.out.println(approach2(0.625));
    }
    
    
    
    
    
    static String approach1(double num){
        
        if(num>=1 || num<=0){
            return "ERROR";
        }
        
        StringBuilder binary = new StringBuilder();
        binary.append(".");
        
        while(num>0){
            if(binary.length() >= 32){
                return "ERROR";
            }
             double r = num * 2;
            if(r>=1){
                binary.append(1);
                num = r-1;
            }else{
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }
    
    
    
    
    
    
    static String approach2(double num){
        
        if(num>=1 || num<=0){
            return "ERROR";
        }
        
        StringBuilder binary = new StringBuilder();
        binary.append(".");
        
        double frac = 0.5;        
        
        while(num>0){
            if(binary.length() >= 32){
                return "ERROR";
            }
            if(num-frac >=0){
                binary.append(1);
                num = num - frac;
            }else{
                binary.append(0);
            }
            frac = frac / 2;
        }
    return binary.toString();
    }
    
    
    
}
