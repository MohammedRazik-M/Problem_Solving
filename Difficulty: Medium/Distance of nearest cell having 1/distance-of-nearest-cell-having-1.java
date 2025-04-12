//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int[][] grid = new int[n][m];
            for(int i = 0; i < n; i++){
                String[] S = br.readLine().trim().split(" ");
                for(int j = 0; j < m; j++){
                    grid[i][j] = Integer.parseInt(S[j]);
                }
            }
            Solution obj = new Solution();
            int[][] ans = obj.nearest(grid);
            for(int i = 0; i < ans.length; i++){
                for(int j = 0; j < ans[i].length; j++){
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
        
System.out.println("~");
}
    }
}
// } Driver Code Ends

class Pair {
    int row, col, distance;
    public Pair(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}

class Solution
{
    public int[][] nearest(int[][] grid)
    {
        int R = grid.length;
        int C = grid[0].length;
        int[][] res = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        Queue<Pair> queue = new ArrayDeque<>();
        for(int row=0; row<R; row++) {
            for(int col=0; col<C; col++) {
                if(grid[row][col] == 1) {
                    queue.add(new Pair(row, col, 0));
                    visited[row][col] = true;
                }
            }
        }
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            int row = queue.peek().row;
            int col = queue.peek().col;
            int distance = queue.peek().distance;
            queue.poll();
            res[row][col] = distance;
            for(int i=0; i<4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && !visited[nrow][ncol]) {
                    queue.add(new Pair(nrow, ncol, distance + 1));
                    visited[nrow][ncol] = true;
                }
            }
        }
        return res;
    }
}