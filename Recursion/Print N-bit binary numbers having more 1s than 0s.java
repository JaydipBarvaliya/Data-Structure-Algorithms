class Solution {
    
    ArrayList<String> list = new ArrayList<String>();
    
    ArrayList<String> NBitBinary(int n) {
     solve(n, 0, 0, new StringBuilder());
     return list;
    }
    
    void solve(int n, int zeroth, int oneth, StringBuilder output){
        
        if(n == 0){
            if(oneth >= zeroth){
                list.add(output.toString());
            }
            return;
        }
        
        solve(n-1, zeroth, oneth+1, output.append("1"));
        output.setLength(output.length() - 1);
        
        //Here we are eliminating entire second branch of tree as it will generate
        //subsets which has 0 as a prefix... and question doesn't demand that.
        if(oneth > zeroth){
            solve(n-1, zeroth+1, oneth, output.append("0"));
            output.setLength(output.length() - 1);
        }
        
    }
}
