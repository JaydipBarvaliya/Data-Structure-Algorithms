class Hanoi {
    
    long count = 0;
    public long toh(int n, int s, int d, int h) {
 	    
 	    count++;
 	    
	    if(n==1){
	        System.out.println("move disk "+n+" from rod "+s+" to rod "+d);
	        return count;
	    }
  
	    toh(n-1, s, h, d);
	    System.out.println("move disk "+n+" from rod "+s+" to rod "+d);
	    toh(n-1, h, d, s);
	    
	    return count;
    }
}
