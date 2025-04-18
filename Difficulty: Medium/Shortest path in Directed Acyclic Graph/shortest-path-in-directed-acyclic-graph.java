//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] edge = new int[m][3];
            for (int i = 0; i < m; i++) {
                edge[i][0] = sc.nextInt();
                edge[i][1] = sc.nextInt();
                edge[i][2] = sc.nextInt();
            }
            Solution obj = new Solution();
            int res[] = obj.shortestPath(n, m, edge);
            for (int i = 0; i < n; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();

            System.out.println("~");
        }
    }
}
// } Driver Code Ends

class Pair {
    int node, weight;
    public Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Solution {
    private void dfs(int node, List<List<Pair>> adjList, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for(int i=0; i<adjList.get(node).size(); i++) {
            int it = adjList.get(node).get(i).node;
            if(!visited[it]) dfs(it, adjList, visited, stack);
        }
        stack.push(node);
    }
    public int[] shortestPath(int V, int E, int[][] edges) {
        List<List<Pair>> adjList = new ArrayList<>();
        for(int i=0; i<V; i++) adjList.add(new ArrayList<>());
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adjList.get(u).add(new Pair(v, weight));
        }
        
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                dfs(i, adjList, visited, stack);
            }
        }
        
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        int src = 0;
        dist[src] = 0;
        
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(dist[node] != Integer.MAX_VALUE) {
                for(Pair pair : adjList.get(node)) {
                    int v = pair.node;
                    int wt = pair.weight;
                    
                    if(dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }    
            }
        }
        for(int i=0; i<V; i++) {
            if(dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }
}