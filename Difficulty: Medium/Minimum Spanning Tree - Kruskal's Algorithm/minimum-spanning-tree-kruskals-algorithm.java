//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int V = Integer.parseInt(br.readLine().trim());
            int E = Integer.parseInt(br.readLine().trim());
            int[][] edges = new int[E][3];
            for (int i = 0; i < E; i++) {
                String[] s = br.readLine().trim().split(" ");
                edges[i][0] = Integer.parseInt(s[0]);
                edges[i][1] = Integer.parseInt(s[1]);
                edges[i][2] = Integer.parseInt(s[2]);
            }
            ot.println(new Solution().kruskalsMST(V, edges));
            ot.println("~");
        }
        ot.close();
    }
}
// } Driver Code Ends

class DisjointSet {
    int[] parent, size;
    public DisjointSet(int n) {
        parent = new int[n+1];
        size = new int[n+1];
        for(int i=0; i<=n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public int findUParent(int node) {
        if(parent[node] == node) return node;
        return parent[node] = findUParent(parent[node]);
    }
    public void unionBySize(int u, int v) {
        int ulp_u = findUParent(u);
        int ulp_v = findUParent(v);
        if(ulp_u == ulp_v) return;
        if(size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class Solution {
    static int kruskalsMST(int V, int[][] edge) {
        List<Edge> edges = new ArrayList<>();
        for(int[] e : edge) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            edges.add(new Edge(u, v, wt));
        }
        DisjointSet ds = new DisjointSet(V);
        Collections.sort(edges);
        int mstWt = 0;
        for(Edge e : edges) {
            int node = e.src;
            int adjNode = e.dest;
            int wt = e.weight;
            
            if(ds.findUParent(node) != ds.findUParent(adjNode)) {
                mstWt += wt;
                ds.unionBySize(node, adjNode);
            }
        }
        return mstWt;
    }
}
