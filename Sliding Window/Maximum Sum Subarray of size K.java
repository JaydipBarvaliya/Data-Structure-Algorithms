public class Main {
    public static void main(String[] args) {
        
        char[] arr = new char[]{1,2,3,4,5,6,7,10};
        int i,j,k,sum,maxsum;
            i=j=sum=maxsum=0;
        int k=3;
        
        while(j<arr.length){
                
                sum = sum + arr[j];    
                if(j-i+1 == k){
                    if(sum>maxsum){
                        maxsum = sum;
                    }
                    sum = sum - arr[i];    
                    i++;
                }
            j++;
        }
        System.out.println(maxsum);
    }
}
