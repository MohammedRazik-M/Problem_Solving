class Solution {
    int timer;
    private void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low, List<List<Integer>> adj, List<List<Integer>> bridges) {
        visited[node] = true;
        tin[node] = low[node] = timer;
        timer++;

        for(int it : adj.get(node)) {
            if(it == parent) continue;

            if(!visited[it]) {
                dfs(it, node, visited, tin, low, adj, bridges);
                low[node] = Math.min(low[node], low[it]);
                if(low[it] > tin[node]) {
                    List<Integer> bridge = new ArrayList<>();
                    bridge.add(Math.min(it, node));
                    bridge.add(Math.max(it, node));
                    bridges.add(bridge);
                }
            }
            else low[node] = Math.min(low[node], low[it]);
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        timer = 0;
        List<List<Integer>> adj = new ArrayList<>();

        for(int node=0; node<n; node++) adj.add(new ArrayList<>());

        for(List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        List<List<Integer>> bridges = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        for(int node=0; node<n; node++) {
            if(!visited[node]) dfs(node, -1, visited, tin, low, adj, bridges);
        }
        return bridges;
    }
}