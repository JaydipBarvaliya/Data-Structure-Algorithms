
public class Main {
    public static void main(String[] args) {
        
        String data = "I am dog     ";
        char[] str = data.toCharArray(); 
        int trueLength = 8;
        int spaceCount = 0;
        
        for(int i=0; i<trueLength; i++){
            if(str[i] == ' '){
                spaceCount++;
            }
        }

        int newLength = trueLength + spaceCount*2; 
        str[trueLength] = '\0';
        for(int i = trueLength-1; i>=0; i--){
            if(str[i] == ' '){
                str[newLength-1] = '0';
                str[newLength-2] = '2';
                str[newLength-3] = '%';
                newLength = newLength - 3;
            }else{
                str[newLength-1] = str[i];
                newLength--;
        }
    }
     System.out.println(str);   
    }
}
 
