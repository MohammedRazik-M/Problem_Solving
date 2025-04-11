//{ Driver Code Starts
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = Integer.parseInt(sc.nextLine());

        while (t-- > 0) {
            int V = Integer.parseInt(sc.nextLine());
            int E = Integer.parseInt(sc.nextLine());

            List<int[]> edgeList = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                String[] parts = sc.nextLine().split(" ");
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                int w = Integer.parseInt(parts[2]);
                edgeList.add(new int[] {u, v, w});
                edgeList.add(new int[] {v, u, w});
            }

            int[][] edges = new int[edgeList.size()][3];
            for (int i = 0; i < edgeList.size(); i++) {
                edges[i] = edgeList.get(i);
            }

            int src = Integer.parseInt(sc.nextLine());

            Solution obj = new Solution();
            int[] res = obj.dijkstra(V, edges, src);

            for (int val : res) {
                System.out.print(val + " ");
            }
            System.out.println();
            System.out.println("~");
        }

        sc.close();
    }
}

// } Driver Code Ends

class Pair {
    int distance, node;
    public Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        List<List<int[]>> adj = new ArrayList<>();
        for(int i=0; i<V; i++) adj.add(new ArrayList<>());
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adj.get(u).add(new int[]{wt, v});
            adj.get(v).add(new int[]{wt, u});
        }
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.offer(new Pair(0, src));
        while(!pq.isEmpty()) {
            Pair pair = pq.poll();
            int distance = pair.distance;
            int node = pair.node;
            for(int i=0; i<adj.get(node).size(); i++) {
                int[] neighbor = adj.get(node).get(i);
                int adjDist = neighbor[0];
                int adjNode = neighbor[1];
                if(distance + adjDist < dist[adjNode]) {
                    dist[adjNode] = distance + adjDist;
                    pq.offer(new Pair(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    } 
}