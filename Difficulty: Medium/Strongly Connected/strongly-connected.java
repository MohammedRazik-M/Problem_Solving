

class Solution {
    private void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        visited[node] = true;
        for(int it : adj.get(node)) {
            if(!visited[it]) dfs(it, visited, adj, stack);
        }
        stack.push(node);
    }
    private void dfs1(int node, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        for(int it : adj.get(node)) {
            if(!visited[it]) dfs1(it, visited, adj);
        }
    }
    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        
        // Step1: Sort all the nodes based on finishing time
        for(int node=0; node<V; node++) {
            if(!visited[node]) dfs(node, visited, adj, stack);
        }
        
        List<List<Integer>> adjRev = new ArrayList<>();
        
        for(int node=0; node<V; node++) adjRev.add(new ArrayList<>());
        
        // Step2: Reverse the graph
        for(int node=0; node<V; node++) {
            visited[node] = false;
            
            for(int it : adj.get(node)) {
                adjRev.get(it).add(node);
            }
        }
        
        int sccCount = 0;
        
        // Step3: Apply DFS on the nodes based on finishing order
        while(!stack.isEmpty()) {
            int node = stack.pop();
            
            if(!visited[node]) {
                sccCount++;
                dfs1(node, visited, adjRev);
            }
        }
        
        return sccCount;
    }
}