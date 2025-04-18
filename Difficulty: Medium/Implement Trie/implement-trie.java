//{ Driver Code Starts
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// } Driver Code Ends

class Node {
    Node links[] = new Node[26];
    boolean flag = false;
    
    public Node() {
        
    }
    
    boolean containsKey(char ch) {
        return (links[ch - 'a'] != null);
    }
    
    void put(char ch, Node node) {
        links[ch - 'a'] = node;
    }
    
    Node get(char ch) {
        return links[ch - 'a'];
    }
    
    void setEnd() {
        flag = true;
    }
    
    boolean isEnd() {
        return flag;
    }
}

class Trie {
    private static Node root;

    public Trie() {
        root = new Node();
    }

    
    public void insert(String word) {
        Node node = root;
        for(int index=0; index<word.length(); index++) {
            if(!node.containsKey(word.charAt(index))) {
                node.put(word.charAt(index), new Node());
            }
            node = node.get(word.charAt(index));
        }
        node.setEnd();
    } 

    
    public boolean search(String word) {
        Node node = root;
        for(int index=0; index<word.length(); index++) {
            if(!node.containsKey(word.charAt(index))) return false;
            node = node.get(word.charAt(index));            
        }
        return node.isEnd();
    }
        
    
    public boolean isPrefix(String word) {
        Node node = root;
        for(int index=0; index<word.length(); index++) {
            if(!node.containsKey(word.charAt(index))) return false;
            node = node.get(word.charAt(index));
        }
        return true;
    }
}


//{ Driver Code Starts.

public class GfG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();

        while (t-- > 0) {
            int q = sc.nextInt();
            sc.nextLine();
            List<Boolean> ans = new ArrayList<>();
            Trie ob = new Trie();

            for (int i = 0; i < q; i++) {
                int x = sc.nextInt();
                String s = sc.next();
                if (i != q - 1) sc.nextLine();

                if (x == 1) {
                    ob.insert(s);
                } else if (x == 2) {
                    ans.add(ob.search(s));
                } else if (x == 3) {
                    ans.add(ob.isPrefix(s));
                }
            }

            for (Boolean result : ans) {
                System.out.print(result + " ");
            }
            System.out.println();
            System.out.println("~");
        }

        sc.close();
    }
}

// } Driver Code Ends