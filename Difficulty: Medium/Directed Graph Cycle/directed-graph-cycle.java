//{ Driver Code Starts
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
            boolean ans = obj.isCyclic(V, edges);
            System.out.println(ans ? "true" : "false");
        }
        sc.close();
    }
}
// } Driver Code Ends


class Solution {
    private boolean dfs(int node, boolean[] visited, boolean[] pathVisited, ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = true;
        pathVisited[node] = true;
        for(int it : adjList.get(node)) {
            if(!visited[it]) {
                if(dfs(it, visited, pathVisited, adjList)) return true;
            }
            else if(pathVisited[it]) return true;
        }
        pathVisited[node] = false;
        return false;
    }
    public boolean isCyclic(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<V; i++) {
            adjList.add(new ArrayList<>());
        }        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];
        
        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                if(dfs(i, visited, pathVisited, adjList)) return true;
            }
        }
        return false;
    }
}