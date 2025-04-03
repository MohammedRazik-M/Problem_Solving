class Solution {
    private void dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        visited[node] = 1;
        for(Integer it : adjList.get(node)) {
            if(visited[it] == 0) {
                dfs(it, adjList, visited);
            }
        }
    }
    public int findCircleNum(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        int R = isConnected.length;
        int C = isConnected[0].length;
        for(int row=0; row<R; row++) {
            adjList.add(new ArrayList<Integer>());
        }
        for(int row=0; row<R; row++) {
            for(int col=0; col<C; col++) {
                if(isConnected[row][col] == 1) {
                   adjList.get(row).add(col);
                   adjList.get(col).add(row); 
                }
            }
        }
        int[] visited = new int[R];
        int count = 0;
        for(int i=0; i<R; i++) {
            if(visited[i] == 0) {
                count++;
                dfs(i, adjList, visited);
            }
        }
        return count;
    }
}