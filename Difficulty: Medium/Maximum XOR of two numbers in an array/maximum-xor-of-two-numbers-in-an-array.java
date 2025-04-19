//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine();
            String[] S = s.split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(S[i]);
            }
            Solution ob = new Solution();
            System.out.println(ob.maxXor(arr));

            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class Node {
    Node links[] = new Node[2];
    
    public Node() {
        
    }
    
    boolean containsKey(int bit) {
        return (links[bit] != null);
    }
    
    void put(int bit, Node node) {
        links[bit] = node;
    }
    
    Node get(int bit) {
        return links[bit];
    }
    
}

class Trie {
    private static Node root;
    
    public Trie() {
        root = new Node();
    }
    
    public void insert(int num) {
        Node node = root;
        for(int i=31; i>=0; i--) {
            int bit = (num >> i) & 1;
            if(!node.containsKey(bit)) {
                node.put(bit, new Node());
            }
            node = node.get(bit);
        }
    }
    
    public int getMax(int num) {
        Node node = root;
        int maxNum = 0;
        for(int i=31; i>=0; i--) {
            int bit = (num >> i) & 1;
            if(node.containsKey(1 - bit)) {
                maxNum |= (1 << i);
                node = node.get(1 - bit);
            }
            else node = node.get(bit);
        }
        return maxNum;
    }
}

class Solution {
    public int maxXor(int[] arr) {
        Trie obj = new Trie();
        for(int num : arr) {
            obj.insert(num);
        }
        int max = Integer.MIN_VALUE;
        for(int num : arr) {
            max = Math.max(max, obj.getMax(num));
        }
        return max;
    }
}