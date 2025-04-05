//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] S = br.readLine().trim().split(" ");
            int V = Integer.parseInt(S[0]);
            S = br.readLine().trim().split(" ");
            int E = Integer.parseInt(S[0]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < E; i++) {
                String[] s = br.readLine().trim().split(" ");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            boolean ans = obj.isBipartite(adj);
            if (ans)
                System.out.println("true");
            else
                System.out.println("false");

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


class Solution {
    private boolean check(int start, int[] colors, ArrayList<ArrayList<Integer>> adj) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        colors[start] = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(int it : adj.get(node)) {
                if(colors[it] == -1) {
                    colors[it] = (colors[node] == 0 ? 1 : 0);
                    queue.add(it);
                }
                else if(colors[it] == colors[node]) return false;
            }
        }
        return true;
    }
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        int[] colors = new int[V];
        Arrays.fill(colors, -1);
        for(int i=0; i<V; i++) {
            if(colors[i] == -1) {
                if(check(i, colors, adj) == false) return false;
            }
        }
        return true;
    }
}