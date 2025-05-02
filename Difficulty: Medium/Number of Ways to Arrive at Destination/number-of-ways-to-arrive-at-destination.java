//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
// Position this line where user code will be pasted.

class GFG {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<Integer>> adj = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    int x = sc.nextInt();
                    temp.add(x);
                }
                adj.add(temp);
            }

            Solution obj = new Solution();
            System.out.println(obj.countPaths(n, adj));
        
System.out.println("~");
}
    }
}
// } Driver Code Ends

class Pair {
    long time;
    int node;
    public Pair(long time, int node) {
        this.time = time;
        this.node = node;
    }
}

class Solution {
    static int countPaths(int n, List<List<Integer>> roads) {
        
        int mod = (int)(1e9 + 7);
        
        List<List<int[]>> adjList = new ArrayList<>();
        
        for(int i=0; i<n; i++) adjList.add(new ArrayList<>());
        
        for(List<Integer> road : roads) {
            int u = road.get(0);
            int v = road.get(1);
            int time = road.get(2);
            adjList.get(u).add(new int[]{v, time});
            adjList.get(v).add(new int[]{u, time});
        }
        
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        int[] ways = new int[n];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.time , b.time));
        pq.offer(new Pair(0, 0));
        dist[0] = 0;
        ways[0] = 1;
        
        while(!pq.isEmpty()) {
            long time = pq.peek().time;
            int node = pq.peek().node;
            pq.poll();
            
            for(int[] it : adjList.get(node)) {
                int adjNode = it[0];
                long adjCost = it[1];
                long newCost = time + adjCost;
                
                if(newCost < dist[adjNode]) {
                    dist[adjNode] = newCost;
                    pq.offer(new Pair(newCost, adjNode));
                    ways[adjNode] = ways[node] % mod;
                }
                else if(newCost == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }
        return ways[n-1] % mod;
    }
}
