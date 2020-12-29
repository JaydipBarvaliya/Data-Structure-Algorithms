public class Main {
    public static void main(String[] args) {
        
        int totalXOR = 0;
        int[] arr = new int[]{1,9,2,4,6,4,6,2};
        
        for(int i=0; i<arr.length; i++){
            totalXOR = totalXOR ^ arr[i];
        }
        
        int index = 0;
        int num = totalXOR;
        while((num & 1) != 1){
            index++;
            num = num >>1;
        }
        
        int finaleXOR = 0;
        for(int j=0; j< arr.length; j++){
            if( (arr[j] & 1<<index) != 0){
                finaleXOR = finaleXOR ^ arr[j];
            }
        }
        
        System.out.println(finaleXOR ^ totalXOR);
        System.out.println(finaleXOR);
    }
}
