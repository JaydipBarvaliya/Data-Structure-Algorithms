public class Main {
    public static void main(String[] args) {
        

        System.out.println(approach1("aabbcc"));
        System.out.println(approach1("aabbccc"));
        
        //we can add one optimization by adding 
        //one extra function which calculate and
        //finalize that either we are going to 
        //compress the string or not ...
        //this will be more optimal in cases where 
        //we don't have a large number of repeating chars.
        
    }
    
    static String approach1(String str){
        
        char temp = str.charAt(0);
        int count = 1;
        StringBuilder compressed =  new  StringBuilder(); 
            
            for(int i=1; i<str.length(); i++){
                if(str.charAt(i) != temp){
                    compressed.append(temp).append(count);
                    temp = str.charAt(i);
                    count = 1;
                }else{
                    count++;
                }
            }
        
        compressed.append(temp).append(count);
        
        return compressed.length() < str.length() ? compressed.toString() : str;
    }
    
}
