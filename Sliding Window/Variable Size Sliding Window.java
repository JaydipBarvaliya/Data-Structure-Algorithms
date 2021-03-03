public class Main {
    public static void main(String[] args) {
        
        int[] arr = new int[]{1,1,1,1,2,3,1,4,4,2,2,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        
        int sum=0;
        int longestLength=0;
        int i=0;
        int j=0;
        int k=10;
        
        while (j < arr.length) {

            sum = sum + arr[j];

            if (sum == k) {
                if (j-i+1 > longestLength) {
                    longestLength = j-i+1;
                }
                j++;
            } else if (sum<k) {
                j++;
            } else {
                (sum>k) {
                    while (sum>k) {
                        sum = sum-arr[i];
                        i++;
                    }
                    j++;
                }
            }
}
        System.out.println(longestLength);
    }
}
