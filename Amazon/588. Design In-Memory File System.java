
// Here we start every loop from 1 because when we split 'path' that time it will give as output as array and in which our first element will be blank

//i.e --- /a/b  ==> ['','a','b']  
//i.e --- a/b  ==> ['a','b']

//So here most of the path will start by '/', so definitely, every time we get a space as a prefix in the path array


class Dir {
    HashMap<String, String> files = new HashMap<>();
    HashMap<String, Dir> direc = new HashMap<>();
 }

class FileSystem {
    
    Dir root;

    public FileSystem() {
        root = new Dir();
    }
    
    
    
    public List<String> ls(String path) {
        
        
        Dir curr = root;
        List<String> files = new ArrayList<>();
        
        if (!path.equals("/")) {
        
            String[] pathArr = path.split("/");
            int len = pathArr.length;
            String lastElem = pathArr[len-1];


            for(int i=1; i<len-1; i++){
                curr = curr.direc.get(pathArr[i]);
            }

            if(curr.files.containsKey(lastElem)){  //last element is file or not
                files.add(lastElem);
                return files;

            }else{ // //last element is dir or not
                curr = curr.direc.get(lastElem);
            }
        }
        
        files.addAll(new ArrayList<>(curr.direc.keySet()));
        files.addAll(new ArrayList<>(curr.files.keySet()));

        Collections.sort(files);
        return files;
        
    }
    
    public void mkdir(String path) {
        
        Dir curr = root;
        
        String[] pathArr = path.split("/");
        
        for(String a : pathArr) System.out.println("hello " + a);
    
        int len = pathArr.length;
        
        for(int i=1; i<len; i++){
            if(!curr.direc.containsKey(pathArr[i])){
                curr.direc.put(pathArr[i], new Dir());
            }
            curr = curr.direc.get(pathArr[i]);
        }
    }
    
    public void addContentToFile(String filePath, String newContent) {
        
        
        Dir curr = root;
        // System.out.println(curr.files.isEmpty());
        // System.out.println(curr.direc.keySet());
        String[] pathArr = filePath.split("/");
        int len = pathArr.length;
        String fileName = pathArr[len-1];
        
        for(int i=1; i<len-1; i++){
            curr = curr.direc.get(pathArr[i]);
        }
        
        curr.files.put(fileName, curr.files.getOrDefault(fileName, "") + newContent);
        
        
    }
    
    public String readContentFromFile(String filePath) {
        
        Dir curr = root;
        
        String[] pathArr = filePath.split("/");
        
        int len = pathArr.length;
        String fileName = pathArr[len-1];
        
        for(int i=1; i<len-1; i++){
            curr = curr.direc.get(pathArr[i]);
        }
        
        return curr.files.get(fileName);
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
