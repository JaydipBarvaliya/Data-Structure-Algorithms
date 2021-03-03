public class Main {
    public static void main(String[] args) {
        int n=3;
        for(int num=0; num<1<<n; num++){
            for(int i=n-1; i>=0; i--){
                if((num & 1<<i) == 0){
                }else{
                    if(i==2){
                        System.out.print("a");
                    }else if(i==1){
                        System.out.print("b");
                    }else{
                        System.out.print("c");
                    }
                }
            }
            System.out.println();
        }
    }
}
