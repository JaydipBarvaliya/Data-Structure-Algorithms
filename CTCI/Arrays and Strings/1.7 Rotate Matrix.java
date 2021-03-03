public class Main {
    public static void main(String[] args) {
        
        int matrix1[][] = {
            { 1,2,3,4 },
            { 5,6,7,8 },
            { 9,10,11,12 },
            { 13,14,15,16 }
        };
        int matrix2[][] = {
            { 1,2,3,4 },
            { 5,6,7,8 },
            { 9,10,11,12 },
            { 13,14,15,16 }
        };
        int matrix3[][] = {
            { 1,2,3,4 },
            { 5,6,7,8 },
            { 9,10,11,12 },
            { 13,14,15,16 }
        };
        
        // In above code all the matrix seperated beacuse pass by reference will occur here, therefore
        
        System.out.println(Arrays.deepToString(approach1(matrix1)));
        System.out.println(Arrays.deepToString(approach2(matrix2)));
        System.out.println(Arrays.deepToString(approach3(matrix3)));
        
}        
    static int[][] approach1(int matrix[][]){

        int re[][] = new int[matrix.length][matrix.length];
        
        int k = 0;
        for(int i=0; i<matrix.length; i++){
            k=0;
            for(int j=matrix.length-1; j>=0; j--){
                re[i][k] = matrix[j][i];
                k++;
            }
        }
            return re;
    }
        
    static int[][] approach2(int matrix[][]){
            
        int temp = 0;
        for(int i=0; i<matrix.length; i++){
            for(int j=i+1; j<matrix.length; j++){
                
                if(matrix[i][j] != matrix[j][i]){
                    temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        
        for(int p=0; p<matrix.length; p++){
            for(int q=0; q<matrix.length/2; q++){
                    temp = matrix[p][q];
                    matrix[p][q] = matrix[p][matrix.length-1-q];
                    matrix[p][matrix.length-1-q] = temp;
            }
        }
        return matrix;
    }
    
    static int[][] approach3(int arr[][]){
        
        int n = arr.length;
        for(int layer=0; layer<n/2; layer++){
         
            int first = layer;
            int last =  n-1-layer;
            int offset = last;
            
              for(int i=first; i<last; i++){

                  int top = arr[layer][i];
                  
                  arr[layer][i] = arr[offset][layer];
                  arr[offset][layer] = arr[last][offset];
                  arr[last][offset]  = arr[i][last];
                  arr[i][last] = top;
                  
                  offset--;
             }
        }
        return arr;
    }
}
