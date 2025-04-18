//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class GFG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t=scanner.nextInt();
        while(t-- > 0)
        {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
    
            ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                ArrayList<Integer> edge = new ArrayList<>();
                edge.add(u);
                edge.add(v);
                edges.add(edge);
            }
    
            Solution solution = new Solution();
            boolean result = solution.isTree(n, m, edges);
    
            if (result==true) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        
System.out.println("~");
}
    }
}
// } Driver Code Ends

class Solution {
    private boolean isCyclic(int node, int parent, boolean[] visited, List<List<Integer>> adjList) {
        visited[node] = true;
        for(int it : adjList.get(node)) {
            if(!visited[it]) {
                if(isCyclic(it, node, visited, adjList)) {
                    return true;
                }
            }
            else if(it != parent) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTree(int n, int m, ArrayList<ArrayList<Integer>> edges) {
        if(m != n-1) return false;
        
        for(ArrayList<Integer> edge : edges) {
            if(edge.get(0) == edge.get(1)) {
                return false;
            }
        }
        
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adjList.add(new ArrayList<>());
        }

        for(ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            adjList.get(u).add(v); 
            adjList.get(v).add(u);
        }
        
        boolean[] visited = new boolean[n];
        
        if(isCyclic(0, -1, visited, adjList)) {
            return false;
        }
        
        for(int node=0; node<n; node++) {
            if(!visited[node]) {
                return false;
            }
        }
        return true;
    }
}

