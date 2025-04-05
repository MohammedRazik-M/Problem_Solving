class Solution {
    private void dfs(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adjList) {
        visited[node] = true;
        for(int it : adjList.get(node)) {
            if(!visited[it]) {
                dfs(it, visited, adjList);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int V = isConnected.length;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<V; i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {    
                if(isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }
        int count = 0;
        boolean[] visited = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!visited[i]) {
                count++;
                dfs(i, visited, adjList);
            }
        }
        return count;
    } 
}