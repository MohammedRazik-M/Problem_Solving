//{ Driver Code Starts


import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int V = Integer.parseInt(br.readLine().trim());
            int E = Integer.parseInt(br.readLine().trim());
            List<List<int[]>> list = new ArrayList<>();
            for (int i = 0; i < V; i++) list.add(new ArrayList<>());
            for (int i = 0; i < E; i++) {
                String[] s = br.readLine().trim().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                list.get(a).add(new int[] {b, c});
                list.get(b).add(new int[] {a, c});
            }
            ot.println(new Solution().spanningTree(V, E, list));

            ot.println("~");
        }
        ot.close();
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
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        boolean[] visited = new boolean[V];    
        int sum = 0;
        pq.offer(new Pair(0, 0));
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.node;
            int weight = p.weight;
            if(visited[node]) continue;
            visited[node] = true;
            sum += weight;
            for(int i=0; i<adj.get(node).size(); i++) {
                int[] neighbor = adj.get(node).get(i);
                int adjNode = neighbor[0];
                int wt = neighbor[1];
                if(!visited[adjNode]) pq.offer(new Pair(adjNode, wt));
            }
        } 
        return sum;
    }
}