//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[][] edge = new int[n][2];
            for (int i = 0; i < n; i++) {
                edge[i][0] = sc.nextInt();
                edge[i][1] = sc.nextInt();
            }
            Solution obj = new Solution();
            int res = obj.minCost(edge);

            System.out.println(res);
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
    private int spanningTree(int V, List<List<int[]>> adj) {
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
    public int minCost(int[][] houses) {
        int n = houses.length;
        List<List<int[]>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) adj.add(new ArrayList<>());
        for(int i=0; i<n-1; i++) {
            for(int j=i+1; j<n; j++) {
                int distance = Math.abs(houses[i][0] - houses[j][0]) + Math.abs(houses[i][1] - houses[j][1]);
                adj.get(i).add(new int[]{j, distance});
                adj.get(j).add(new int[]{i, distance});
            }
        }
        return spanningTree(n, adj);
    } 
}
