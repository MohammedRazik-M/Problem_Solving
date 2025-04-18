//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u); // Since the graph is undirected
            }

            int src = sc.nextInt();

            Solution obj = new Solution();
            int[] res = obj.shortestPath(adj, src);

            for (int i = 0; i < res.length; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class Solution {
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        queue.add(src);
        dist[src] = 0;
        
        while(!queue.isEmpty()) {
            int node = queue.poll();
            
            for(int it : adj.get(node)) {
                if(dist[it] == Integer.MAX_VALUE) {
                    dist[it] = dist[node] + 1;
                    queue.add(it);
                }
            }
        }
        for(int i=0; i<adj.size(); i++) {
            if(dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }
        return dist;
    }
}
