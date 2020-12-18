public class Main {
    public static void main(String[] args) {
        
        int m1[][] = {{1,2,3},{4,5,6}};
        int m2[][] = {{7,8},{9,10},{11,12}};
        int re[][] = new int[m1.length][m2[0].length];
        int sum = 0;
        
        for(int i=0; i<m1.length; i++){
            for(int j=0; j<m1.length; j++){
                for(int k=0; k<m1[0].length; k++){
                 sum = sum + m1[i][k] * m2[k][j];   
                }
                re[i][j] = sum;
                sum = 0;
            }
        }
        
        for (int i = 0; i < re.length; i++) { 
         for (int j = 0; j < re[i].length; j++) { 
            System.out.print(re[i][j] + " ");
         }
         System.out.println();
      }
    }
}
