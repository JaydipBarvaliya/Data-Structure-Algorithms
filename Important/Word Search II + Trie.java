class TrieNode {
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  String word = null;
  public TrieNode() {}
}


class Solution {
    
    
  char[][] board = null;
    
  ArrayList<String> result = new ArrayList<String>();
    
  public List<String> findWords(char[][] board, String[] words) {
      
     //asign here to use it globally 
     this.board = board;
      
    // Step 1). Construct the 
    TrieNode root = new TrieNode();
      
      
      
      

    for(String word : words){

      TrieNode node = root;
        
      for (Character letter : word.toCharArray()) {

            if (node.children.containsKey(letter)) {
              
               //this assginment defined that we traverse 1 level down in trie 
              node = node.children.get(letter);
            } else {
              TrieNode newNode = new TrieNode();
              node.children.put(letter, newNode);
              node = newNode;
            }
      }

      node.word = word;  // store words in Trie
    }

      
      
    
      
      
    // Step 2). Backtracking starting for each cell in the board
    for (int row = 0; row < board.length; ++row) {
      for (int col = 0; col < board[0].length; ++col) {
          
        if (root.children.containsKey(board[row][col])) {
          trieExplorer(row, col, root);
        }
      }
    }

    return this.result;
  }
    
    
public void trieExplorer(int i, int j, TrieNode parent){
    
        if(i<0 || j<0 || i==this.board.length || j==this.board[0].length) return;
    
        Character letter = this.board[i][j];
        TrieNode currNode = parent.children.get(letter);
        
        if (currNode.word != null) {
          this.result.add(currNode.word);
          currNode.word = null;
        }


        this.board[i][j] = '#';
    
    
        if (j+1 != this.board[0].length && currNode.children.containsKey(this.board[i][j+1])) {
            trieExplorer(i,     j+1,   currNode);
        }

        if (i+1 != this.board.length && currNode.children.containsKey(this.board[i+1][j])) {
            trieExplorer(i+1,   j,     currNode);
        }

        if (j-1 != -1 && currNode.children.containsKey(this.board[i][j-1])) {
            trieExplorer(i,     j-1,   currNode);
        }

        if (i-1 != -1 && currNode.children.containsKey(this.board[i-1][j])) {
            trieExplorer(i-1,   j,     currNode);
        }
        
        this.board[i][j] = letter;
    
        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
          parent.children.remove(letter);
        }
  }
}
