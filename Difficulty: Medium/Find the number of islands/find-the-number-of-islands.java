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

class Coords {
    int row, col;
    public Coords(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    int[] drow = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dcol = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    public int countIslands(char[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int islandCount = 0;
        boolean[][] visited = new boolean[R][C];
        for(int row=0; row<R; row++) {
            for(int col=0; col<C; col++) {
                if(grid[row][col] == 'L' && !visited[row][col]) {
                    islandCount++;
                    Queue<Coords> queue = new ArrayDeque<>();
                    queue.add(new Coords(row, col));
                    visited[row][col] = true;
                    while(!queue.isEmpty()) {
                        Coords coord = queue.poll();
                        int r = coord.row;
                        int c = coord.col;
                        for(int i=0; i<8; i++) {
                            int nrow = r + drow[i];
                            int ncol = c + dcol[i];
                            if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && !visited[nrow][ncol] && grid[nrow][ncol] == 'L') {
                                queue.add(new Coords(nrow, ncol));
                                visited[nrow][ncol] = true;
                            }   
                        }
                    }
                }
            }
        }
        return islandCount;
    }
}