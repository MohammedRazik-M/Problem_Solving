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

            Solution obj = new Solution();
            int res = obj.findMinCycle(V, edges);

            System.out.println(res);
        }

        sc.close();
    }
}

// } Driver Code Ends

class Solution {
    public int findMinCycle(int V, int[][] edges) {
        List<List<int[]>> adjList = new ArrayList<>();
        for(int i = 0; i < V; i++) adjList.add(new ArrayList<>());

        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            adjList.get(u).add(new int[]{v, w});
            adjList.get(v).add(new int[]{u, w});
        }

        int minCycleCost = Integer.MAX_VALUE;

        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            removeEdge(adjList, u, v, w);
            int distance = getDistance(adjList, u, v, V);
            if (distance != Integer.MAX_VALUE)
                minCycleCost = Math.min(minCycleCost, distance + w);
            adjList.get(u).add(new int[]{v, w});
            adjList.get(v).add(new int[]{u, w});
        }
        return (minCycleCost == Integer.MAX_VALUE) ? -1 : minCycleCost;
    }

    private int getDistance(List<List<int[]>> adjList, int src, int dest, int V) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();

        dist[src] = 0;
        q.offer(src);

        while(!q.isEmpty()) {
            int node = q.poll();

            for(int[] neighbor : adjList.get(node)) {
                int next = neighbor[0], weight = neighbor[1];
                if(dist[next] > dist[node] + weight) {
                    dist[next] = dist[node] + weight;
                    q.offer(next);
                }
            }
        }
        return dist[dest];
    }

    private void removeEdge(List<List<int[]>> adjList, int u, int v, int w) {
        adjList.get(u).removeIf(pair -> pair[0] == v && pair[1] == w);
        adjList.get(v).removeIf(pair -> pair[0] == u && pair[1] == w);
    }
}



