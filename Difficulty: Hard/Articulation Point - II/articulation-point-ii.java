//{ Driver Code Starts
import java.util.*;

public class Main {
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
            ArrayList<Integer> ans = obj.articulationPoints(V, edges);
            Collections.sort(ans);
            for (int val : ans) {
                System.out.print(val + " ");
            }
            System.out.println();
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    static int timer;

    private static void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low,
                            ArrayList<ArrayList<Integer>> adjList, boolean[] isArticulation) {
        visited[node] = true;
        tin[node] = low[node] = timer++;
        int children = 0;

        for(int it : adjList.get(node)) {
            if(it == parent) continue;
            if(!visited[it]) {
                dfs(it, node, visited, tin, low, adjList, isArticulation);
                low[node] = Math.min(low[node], low[it]);
                if(low[it] >= tin[node] && parent != -1) isArticulation[node] = true;
                children++;
            } 
            else low[node] = Math.min(low[node], tin[it]);
        }
        if(parent == -1 && children > 1) isArticulation[node] = true;
    }

    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) adjList.add(new ArrayList<>());
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] isArticulation = new boolean[V];
        timer = 0;

        for(int i = 0; i < V; i++) {
            if(!visited[i]) {
                dfs(i, -1, visited, tin, low, adjList, isArticulation);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < V; i++) {
            if(isArticulation[i]) result.add(i);
        }
        if(result.isEmpty()) result.add(-1);
        return result;
    }
}
