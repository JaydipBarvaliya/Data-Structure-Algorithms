class Trie {
    
    Node root;

    public Trie() {
        root = new Node('\0');  
    }
    
    public void insert(String word) {
        
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            
            Character c =  word.charAt(i);
            int index   = c-'a';
            
            if(curr.childrens[index] == null ){
                curr.childrens[index] = new Node(c);
            }
            curr.childrens[index].freq++;
            curr = curr.childrens[index];
        }
        curr.wordCount++;
    }
    
    public int countWordsEqualTo(String word) {
        Node node = getNode(word);
        return node == null ? 0: node.wordCount;
    }
    
    public int countWordsStartingWith(String prefix) {
        Node node = getNode(prefix);
        return node == null ? 0: node.freq;
    }
    
    
    public Node getNode(String word) {
        
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            
            Character c = word.charAt(i);
            int index   = c-'a';
            
            if(curr.childrens[index] == null ){
                return null;
            }
            curr = curr.childrens[index];
        }
        return curr;
    }
    
    
    public void erase(String word) {
        
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            
            Character c = word.charAt(i);
            int index   = c-'a';
            
            Node node  = curr.childrens[index];
            node.freq--;
            curr = node;
        }
        
        curr.wordCount--;
    }
}

class Node{
    
    char c;
    Node[] childrens;
    int wordCount;
    int freq;
    
    public Node(char c){
        this.c =  c;
        childrens = new Node[26];
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
