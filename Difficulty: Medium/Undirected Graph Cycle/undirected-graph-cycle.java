//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                edges[i][0] = sc.nextInt();
                edges[i][1] = sc.nextInt();
            }

            Solution obj = new Solution();
            boolean ans = obj.isCycle(V, edges);
            System.out.println(ans ? "true" : "false");
            System.out.println("~");
        }
        sc.close();
    }
}

// } Driver Code Ends


class Solution {
    private boolean dfs(int node, int parent, int[] visited, ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = 1;
        for(int it : adjList.get(node)) {
            if(visited[it] == 0) {
                if(dfs(it, node, visited, adjList)) return true;
            }
            else if(it != parent) return true;
        }
        return false;
    }
    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<V; i++) {
            adjList.add(new ArrayList<>());
        }        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        int[] visited = new int[V];
        for(int i=0; i<V; i++) {
            if(visited[i] == 0) {
                if(dfs(i, -1, visited, adjList)) return true;
            }
        }
        return false;
    }
}

