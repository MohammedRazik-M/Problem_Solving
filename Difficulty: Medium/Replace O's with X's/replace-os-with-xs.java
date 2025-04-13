//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            int m = Integer.parseInt(in.readLine());
            char mat[][] = new char[n][m];
            for (int i = 0; i < n; i++) {
                String S[] = in.readLine().trim().split(" ");
                for (int j = 0; j < m; j++) {
                    mat[i][j] = S[j].charAt(0);
                }
            }

            Solution ob = new Solution();
            char[][] ans = ob.fill(mat);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    static int[] drow = {-1, 1, 0, 0};
    static int[] dcol = {0, 0, -1, 1};
    private static boolean isValid(int row, int col, int R, int C) {
        return (row >= 0 && row < R && col >= 0 && col < C);
    }
    private static void dfs(int row, int col, char[][] grid, int R, int C, boolean[][] visited) {
        visited[row][col] = true;
        for(int i=0; i<4; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];
            if(isValid(nrow, ncol, R, C) && !visited[nrow][ncol] && grid[nrow][ncol] == 'O') {
                dfs(nrow, ncol, grid, R, C, visited);                
            }
        }
    }
    static char[][] fill(char mat[][]) {
        int R = mat.length;
        int C = mat[0].length;
        int row, col;
        boolean[][] visited = new boolean[R][C];
        char[][] grid = new char[R][C];
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                grid[i][j] = mat[i][j];
            }
        }
        //Top
        row = 0;
        for(col=0; col<C; col++) {
            if(mat[row][col] == 'O' && !visited[row][col]) dfs(row, col, grid, R, C, visited);
        }
        //Bottom
        row = R-1;
        for(col=0; col<C; col++) {
            if(mat[row][col] == 'O' && !visited[row][col]) dfs(row, col, grid, R, C, visited);
        }
        //Left
        col = 0;
        for(row=0; row<R; row++) {
            if(mat[row][col] == 'O' && !visited[row][col]) dfs(row, col, grid, R, C, visited); 
        }
        //Right
        col = C-1;
        for(row=0; row<R; row++) {
            if(mat[row][col] == 'O' && !visited[row][col]) dfs(row, col, grid, R, C, visited); 
        }
        
        //Checking for 'O' which is surrounded by 'X' in between
        for(row=1; row<R-1; row++) {
            for(col=1; col<C-1; col++) {
                if(!visited[row][col] && grid[row][col] == 'O') grid[row][col] = 'X';
            }
        }
        return grid;
    } 
}