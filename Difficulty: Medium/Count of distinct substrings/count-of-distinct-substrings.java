
class Node {
    Node[] links = new Node[26];
    
    public Node() {
        
    }
    
    boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }
    
    void put(char ch, Node node) {
        links[ch - 'a'] = node;    
    }
    
    Node get(char ch) {
        return links[ch - 'a'];
    }
}

class GfG {
    public static int countDistinctSubstring(String st) {
        
        Node root = new Node();
        
        int n = st.length();
        int count = 0;
        
        char[] str = st.toCharArray();
        
        for(int i=0; i<n; i++) {
            
            Node node = root;
            
            for(int j=i; j<n; j++) {
                if(!node.containsKey(str[j])) {
                    node.put(str[j], new Node());
                    count++;
                }
                node = node.get(str[j]);
            }
            
        }
        
        return count+1;
    }
}