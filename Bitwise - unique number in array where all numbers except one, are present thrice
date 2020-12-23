public class Main {
    public static void main(String[] args) {
        
        int[] arr = new int[]{1,3,2,3,4,2,1,1,3,2};
        int[] res = new int[64];
        int n = 3;
        
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<n; j++){
                if((arr[i] & 1<<j) !=0){
                    res[j]++;
                }
            }
        }
        
        for(int k=0; k<res.length; k++){
            if(res[k]%n !=0){
                System.out.println(1<<k);
                break;
            }
        }
    }
}
