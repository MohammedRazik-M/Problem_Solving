//{ Driver Code Starts
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int V = Integer.parseInt(read.readLine());
            
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            
            for(int i=0; i<V; i++)
            {
                String S[] = read.readLine().split(" ");
                ArrayList<Integer> temp = new ArrayList<>();
                for(int j=0; j<V; j++)
                    temp.add(Integer.parseInt(S[j]));
                adj.add(temp);
            }

            Solution ob = new Solution();
            System.out.println(ob.numProvinces(adj,V));
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution {
    private static void dfs(int node, boolean[] visited, List<List<Integer>> adjList) {
        visited[node] = true;
        for(int it : adjList.get(node)) {
            if(!visited[it]) dfs(it, visited, adjList);
        }
    }
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V+1];
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<=V; i++) {
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
                if(adj.get(i).get(j) == 1) {
                    adjList.get(i+1).add(j+1);
                    adjList.get(j+1).add(i+1);
                }
            }
        }
        int provincesCount = 0;
        for(int i=1; i<=V; i++) {
            if(!visited[i]) {
                provincesCount++;
                dfs(i, visited, adjList);
            }
        }
        return provincesCount;
    }
};