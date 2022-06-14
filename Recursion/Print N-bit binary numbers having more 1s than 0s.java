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
        
        
        //Below condition has 2 solid grounds.
        //1) we never want to generate any subset which has 0 as a starting character....because when we generate all it's prefix then we'll left with one subset which has only 0 and it can count as a prefix with height 0 than....so by using this condition we are eliminating entire right subtree.
        //i.e. 001111 --00111 0011 0011 00 0....these are all the prefix ...and we can see that there are two subset at last which has 0's are heigher then 1's .....problem is not that 0 must not be begin...as per the question if we generate all the prefixi subset then it will cause the problem that is why without taking single risk we are not going beyond where 0 can come as a prefix/begin character of any string/subset 
        
        //2) The another thing is that.... for subset.... 10....we have 1th=1 and 0th=1...which means that if we try to add 1 more 0 in this ...then it will become "100"....so might be "100" will not our final subset...but when it become prefix of corrosponding subset then it will create more 0's then number of 1.
        
        //and to handle both of the above condition we can write below...condition...which even not accepting the scenario where oneth == zeroth...because then we'll add one more 0 ..and it will increase the count of zero...
        if(oneth > zeroth){
            
            
            solve(n-1, zeroth+1, oneth, output.append("0"));
            output.setLength(output.length() - 1);
        }
        
    }
}
