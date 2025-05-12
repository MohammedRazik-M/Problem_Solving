//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] edge = new int[m][2];
            for (int i = 0; i < m; i++) {
                edge[i][0] = sc.nextInt();
                edge[i][1] = sc.nextInt();
            }

            Solution obj = new Solution();
            int ans = obj.Solve(n, edge);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends

class DisjointSet {
    int[] parent, rank;
    public DisjointSet(int n) {
        parent = new int[n+1];
        rank = new int[n+1];
        for(int i=0; i<=n; i++) {
            parent[i] = i;
        }
    }
    public int findUParent(int node) {
        if(parent[node] == node) return node;
        return parent[node] = findUParent(parent[node]);
    }
    public void unionByRank(int u, int v) {
        int ulp_u = findUParent(u);
        int ulp_v = findUParent(v);
        if(ulp_u == ulp_v) return;
        if(rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        }
        else if(rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        }
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }
}

class Solution {
    public int Solve(int n, int[][] edge) {
        DisjointSet ds = new DisjointSet(n);
        int extraEdgesCnt = 0;
        for(int[] e : edge) {
            int u = e[0];
            int v = e[1];
            if(ds.findUParent(u) == ds.findUParent(v)) extraEdgesCnt++;
            else ds.unionByRank(u, v);
        }
        int ComponentsCnt = 0;
        for(int node=0; node<n; node++) {
            if(ds.parent[node] == node) ComponentsCnt++;
        }
        return (extraEdgesCnt >= ComponentsCnt-1) ? ComponentsCnt-1 : -1;
    }
}