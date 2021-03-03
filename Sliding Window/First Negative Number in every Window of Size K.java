public class Main {
    public static void main(String[] args) {
        
        int[] arr = new int[]{12, -1, -7, 8, -15, 30, 16, 28};
        ArrayList<Integer> nagArr = new ArrayList();
        ArrayList<Integer> output = new ArrayList();
        int i,j,k;
            i=j=0;
            k=3;
        
        while(j<arr.length){

            if(arr[j]<0){
                nagArr.add(arr[j]);
            }
            
            if(j-i+1 == k){
                if(nagArr.size() == 0){
                 output.add(0);
                }else{
                 output.add(nagArr.get(0));
                    if(arr[i] == nagArr.get(0)){
                        nagArr.remove(0);
                    }
                }
                i++;
            }
            j++;
        }
        System.out.println(output);
    }
}
