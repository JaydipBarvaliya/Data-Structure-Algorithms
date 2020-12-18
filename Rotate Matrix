public class Main {
    public static void main(String[] args) {
        
        int matrix[][] = {{1,2,3},{4,5,6},{7,8,9}};
        int re[][] = new int[matrix.length][matrix.length];
        
        int k = 0;
        for(int i=0; i<matrix.length; i++){
            k=0;
            for(int j=matrix.length-1; j>=0; j--){
                re[i][k] = matrix[j][i];
                k++;
            }
        }
        System.out.println(Arrays.deepToString(re));
    }
}
