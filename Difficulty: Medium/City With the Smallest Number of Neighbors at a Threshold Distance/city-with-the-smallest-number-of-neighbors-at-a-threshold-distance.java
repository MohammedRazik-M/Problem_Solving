//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {

            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] adj = new int[m][3];

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < 3; j++) {
                    adj[i][j] = sc.nextInt();
                }
            }

            int dist = sc.nextInt();
            Solution obj = new Solution();
            int ans = obj.findCity(n, m, adj, dist);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends

class Pair {
    int dist, node;
    public Pair(int dist, int node) {
        this.dist = dist;
        this.node = node;
    }
}

class Solution {
    private void dijkstra(PriorityQueue<Pair> pq, int[] dist, List<List<int[]>> adjList) {
        while(!pq.isEmpty()) {
            int distance = pq.peek().dist;
            int node = pq.peek().node;
            pq.poll();
            
            for(int[] it : adjList.get(node)) {
                int adjNode = it[0];
                int adjDist = it[1];
                int neighborDist = distance + adjDist;
                if(neighborDist < dist[adjNode]) {
                    dist[adjNode] = neighborDist;
                    pq.offer(new Pair(neighborDist, adjNode));
                }
            }
        }
    }
    int findCity(int n, int m, int[][] edges, int distanceThreshold) {
        
        int cityMin = distanceThreshold + 1;
        int cityNode = -1;
        
        List<List<int[]>> adjList = new ArrayList<>();
        
        for(int i=0; i<n; i++) adjList.add(new ArrayList<>());
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int dist = edge[2];
            adjList.get(u).add(new int[]{v, dist});
            adjList.get(v).add(new int[]{u, dist});
        }
        
        for(int city = 0; city < n; city++) {
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
            
            int[] dist = new int[n];
            Arrays.fill(dist, (int)1e9);
            
            pq.offer(new Pair(0, city));
            dist[city] = 0;
            
            dijkstra(pq, dist, adjList);
            
            int thresholdCount = 0;
            for(int d : dist) {
                if(d <= distanceThreshold) {
                    thresholdCount++;
                }
            }
            if(thresholdCount <=  cityMin) {
                cityMin = thresholdCount;
                cityNode = city;
            }
        }
        return cityNode;
    }
}
