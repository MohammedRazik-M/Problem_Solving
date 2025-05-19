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
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                arr[i][0] = sc.nextInt();
                arr[i][1] = sc.nextInt();
            }
            Solution obj = new Solution();
            int ans = obj.maxRemove(arr, n);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
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
    int maxRemove(int[][] stones, int n) {
        
        int maxRow = 0, maxCol = 0;
        
        for(int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }
        
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);
        Set<Integer> set = new HashSet<>();
        
        for(int[] stone : stones) {
            int stoneRow = stone[0];
            int stoneCol = stone[1] + maxRow + 1;
            ds.unionBySize(stoneRow, stoneCol);
            set.add(stoneRow);
            set.add(stoneCol);
        }
        int componentsCount = 0;
        for(int it : set) {
            if(ds.findUParent(it) == it) componentsCount++;
        }
        return n - componentsCount;
    }
};
