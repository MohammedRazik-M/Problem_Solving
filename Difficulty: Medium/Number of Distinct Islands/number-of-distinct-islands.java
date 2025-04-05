//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            Solution ob = new Solution();
            int ans = ob.countDistinctIslands(grid);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


class Pair {
    int row, col;
    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override 
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Pair)) return false;
        Pair other = (Pair)obj;
        return this.row == other.row && this.col == other.col;
    }
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

class Solution {
    int[] drow = {-1, 1, 0, 0};
    int[] dcol = {0, 0, -1, 1};
    private void dfs(int row, int col, boolean[][] visited, int[][] grid, int R, int C, ArrayList<Pair> coords, int brow, int bcol) {
        visited[row][col] = true;
        coords.add(new Pair(row - brow, col - bcol));
        for(int i=0; i<4; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];
            if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && !visited[nrow][ncol] && grid[nrow][ncol] == 1) {
                dfs(nrow, ncol, visited, grid, R, C, coords, brow, bcol);                
            }
        }
    }
    int countDistinctIslands(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        boolean[][] visited = new boolean[R][C];
        Set<ArrayList<Pair>> set = new HashSet<>();
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    ArrayList<Pair> coords = new ArrayList<>();
                    dfs(i, j, visited, grid, R, C, coords, i, j);
                    set.add(coords);
                }
            }
        }
        return set.size();
    }
}
