class Trie {
    
    Node root;

    public Trie() {
         root = new Node(); 
    }
    
    public void insert(String word) {
        
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            
            Character ch = word.charAt(i);
            Node node = curr.children.get(ch);
            
            if(node == null){
                node = new Node();
                curr.children.put(ch, node); 
            }
            curr = node;
        }
        
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        
        Node curr = root;
        
        for(int i=0; i<word.length(); i++){
            
            Character ch = word.charAt(i);
            Node node = curr.children.get(ch);
            
            if(node == null){
                return false;
            }
            curr = node;
        }
        
        return curr.isEnd ==  true;
    }
    
    public boolean startsWith(String prefix) {
        
        Node curr = root;
        
        for(int i=0; i<prefix.length(); i++){
            
            Character ch = prefix.charAt(i);
            Node node = curr.children.get(ch);
            
            if(node == null){
                return false;
            }
            curr = node;
        }
        
        return true;
    }
}


class Node {

    Map<Character, Node> children;
    boolean isEnd;

    public Node(){
        children = new HashMap<>();
        isEnd = false;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
