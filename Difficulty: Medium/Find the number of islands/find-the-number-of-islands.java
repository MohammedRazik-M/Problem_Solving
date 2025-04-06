//{ Driver Code Starts
import java.util.*;

class GFG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = scanner.nextInt();
        while (tc-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            char[][] grid = new char[n][m];

            // Read the grid input
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = scanner.next().charAt(0);
                }
            }
            Solution obj = new Solution();
            int ans = obj.countIslands(grid);
            System.out.println(ans);
            System.out.println("~");
        }
        scanner.close();
    }
}

// } Driver Code Ends


class Solution {
    int[] drow = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dcol = {-1, 0, 1, -1, 1, -1, 0, 1};
    private void dfs(char[][] grid, int row, int col, int R, int C, boolean[][] visited) {
        visited[row][col] = true;
        for(int i=0; i<8; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];
            if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && !visited[nrow][ncol] && grid[nrow][ncol] == 'L') {
                dfs(grid, nrow, ncol, R, C, visited);                
            }
        }
    }
    public int countIslands(char[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int islandCount = 0;
        boolean[][] visited = new boolean[R][C];
        for(int row=0; row<R; row++) {
            for(int col=0; col<C; col++) {
                if(!visited[row][col] && grid[row][col] == 'L') {
                    dfs(grid, row, col, R, C, visited);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }
}