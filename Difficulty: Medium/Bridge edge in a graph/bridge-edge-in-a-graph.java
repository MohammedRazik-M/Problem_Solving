//{ Driver Code Starts
import java.util.*;


// } Driver Code Ends

class Pair {
    int src, dest;
    public Pair(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}

class Solution {
    int timer = 1;
    private void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low, List<Pair> bridges, 
             ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = true;    
        tin[node] = low[node] = timer;
        timer++;
        for(int it : adjList.get(node)) {
            if(it == parent) continue;
            if(!visited[it]) {
                dfs(it, node, visited, tin, low, bridges, adjList);
                low[node] = Math.min(low[node], low[it]);
                if(low[it] > tin[node]) bridges.add(new Pair(it, node));
            }
            else low[node] = Math.min(low[node], low[it]);
        }
    }
    public boolean isBridge(int V, int[][] edges, int c, int d) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<V; i++) adjList.add(new ArrayList<>());
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        List<Pair> bridges = new ArrayList<>();
        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                dfs(i, -1, visited, tin, low, bridges, adjList);
            }
        }
        for(Pair pair : bridges) {
            if((pair.src == c && pair.dest == d) || (pair.src == d && pair.dest == c)) return true;
        }
        return false;
    } 
}


//{ Driver Code Starts.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.nextLine().trim());
        while (t-- > 0) {
            // V and E on separate lines
            int V = Integer.parseInt(sc.nextLine().trim());
            int E = Integer.parseInt(sc.nextLine().trim());

            // Using a 2D array to store edges.
            int[][] edges = new int[E][2];
            for (int i = 0; i < E; i++) {
                // Use split("\\s+") to handle one or more whitespace characters
                String[] parts = sc.nextLine().trim().split("\\s+");
                edges[i][0] = Integer.parseInt(parts[0]);
                edges[i][1] = Integer.parseInt(parts[1]);
            }

            // c and d on separate lines
            int c = Integer.parseInt(sc.nextLine().trim());
            int d = Integer.parseInt(sc.nextLine().trim());

            Solution obj = new Solution();
            boolean result = obj.isBridge(V, edges, c, d);
            System.out.println(result ? "true" : "false");
            System.out.println("~");
        }

        sc.close();
    }
}
// } Driver Code Ends