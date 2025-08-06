class Node {
    Node[] links;
    int wordCount;
    boolean isEnd;

    public Node() {
        links = new Node[26];
        wordCount = 0;
        isEnd = false;
    }

    public boolean containsKey(char ch) {
        return links[ch-'a'] != null;
    }

    public void put(char ch, Node node) {
        links[ch-'a'] = node;
    }

    public Node get(char ch) {
        return links[ch-'a'];
    }

}

class Solution {
    private void insert(Node root, String word) {
        Node node = root;
        for(char ch : word.toCharArray()) {
            if(!node.containsKey(ch)) {
                node.put(ch, new Node());
                node.wordCount++;
            }
            node = node.get(ch);
        }
        node.isEnd = true;
    }

    private String walkTrie(Node root, String word) {
        Node node = root;
        StringBuilder res = new StringBuilder();

        for(char ch : word.toCharArray()) {
            if(node.wordCount == 1 && !node.isEnd) {
                res.append(ch);
                node = node.get(ch);
            }
            else break;
        }
        return res.toString();
    }

    public String longestCommonPrefix(String[] strs) {

        int n = strs.length;
        
        if(strs == null || n == 0) return "";

        Node root = new Node();

        for(String str : strs) {
            if(str.length() == 0) return "";
            insert(root, str);
        }

        return walkTrie(root, strs[0]);
    }
}