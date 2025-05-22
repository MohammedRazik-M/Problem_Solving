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
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        DisjointSet ds = new DisjointSet(V+1);
        for(int node=0; node<V; node++) {
            for(int it : adj.get(node)) {
                if(it > node) {
                    if(ds.findUParent(node) == ds.findUParent(it)) return 1;
                    ds.unionBySize(node, it);
                }
            }
        }
        return 0;
    }
}