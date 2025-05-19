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
    private boolean isValid(int row, int col, int n) {
        return (row >= 0 && row < n && col >= 0 && col < n);
    }
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        int maxSize = 0;

        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++) {
                if(grid[row][col] == 1) {
                    for(int i=0; i<4; i++) {
                        int nrow = row + drow[i];
                        int ncol = col + dcol[i];
                        if(isValid(nrow, ncol, n) && grid[nrow][ncol] == 1) {
                            int nodeNo = (row * n) + col;
                            int adjNodeNo = (nrow * n) + ncol;
                            ds.unionBySize(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
        }

        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++) {
                if(grid[row][col] == 0) {

                    Set<Integer> components = new HashSet<>();

                    for(int i=0; i<4; i++) {
                        int nrow = row + drow[i];
                        int ncol = col + dcol[i];
                        if(isValid(nrow, ncol, n) && grid[nrow][ncol] == 1) {
                            int adjNodeNo = (nrow * n) + ncol;
                            components.add(ds.findUParent(adjNodeNo));
                        }
                    }

                    int size = 0;
                    for(int it : components) size += ds.size[it];

                    maxSize = Math.max(maxSize, size + 1);
                }
            }
        }

        for(int cellNo = 0; cellNo < n*n; cellNo++) {
            maxSize = Math.max(maxSize, ds.size[ds.findUParent(cellNo)]);
        }

        return maxSize;
    }
}