//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;


// } Driver Code Ends

class DisjointSet {
    int[] parent, size;
    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++) {
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

class Solution {
    private boolean isValid(int row, int col, int R, int C) {
        return (row >= 0 && row < R && col >= 0 && col < C);
    }
    public List<Integer> numOfIslands(int R, int C, int[][] operators) {
        
        List<Integer> res = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        
        DisjointSet ds = new DisjointSet(R*C);
        
        int islandCount = 0;
        for(int[] op : operators) {
            int row = op[0];
            int col = op[1];
            
            if(visited[row][col]) {
                res.add(islandCount);
                continue;
            }
            
            visited[row][col] = true;
            islandCount++;
            
            for(int i=0; i<4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                if(isValid(nrow, ncol, R, C) && visited[nrow][ncol]) {
                    int nodeNo = (row * C) + col;
                    int adjNodeNo = (nrow * C) + ncol;
                    if(ds.findUParent(nodeNo) != ds.findUParent(adjNodeNo)) {
                        islandCount--;
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
            res.add(islandCount);
        }
        return res;
    }
}


//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int  k= sc.nextInt();
            int[][] a = new int[k][2];
            for(int i=0;i<k;i++){
            
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
            }
            
            Solution obj = new Solution();
            List<Integer> ans = obj.numOfIslands(n,m,a);
           
            for(int i:ans){
                System.out.print(i+" ");
            }
            System.out.println();
        
System.out.println("~");
}
    }
}

// } Driver Code Ends