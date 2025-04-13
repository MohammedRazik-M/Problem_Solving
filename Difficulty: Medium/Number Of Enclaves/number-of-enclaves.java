//{ Driver Code Starts
// Initial Template for Java

// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int T = Integer.parseInt(in.readLine());
        while (T-- > 0) {
            String s[] = in.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int a[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                s = in.readLine().trim().split(" ");
                for (int j = 0; j < m; j++) {
                    a[i][j] = Integer.parseInt(s[j]);
                }
            }
            Solution ob = new Solution();
            out.println(ob.numberOfEnclaves(a));
        
out.println("~");
}
        out.close();
    }
}
// } Driver Code Ends

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    int numberOfEnclaves(int[][] grid) {
        int R = grid.length;
        int C = grid[0].length;
        int[][] res = new int[R][C];
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        
        for(int i=0; i<R; i++) {
            System.arraycopy(grid[i], 0, res[i], 0, grid[i].length);
        }
        boolean[][] visited = new boolean[R][C];
        Queue<Pair> queue = new ArrayDeque<>();
        int row, col;
        //Top
        row = 0;
        for(col=0; col<C; col++) {
            if(!visited[row][col] && res[row][col] == 1) {
                queue.offer(new Pair(row, col));
                visited[row][col] = true;
            }
        }
        //Bottom
        row = R-1;
        for(col=0; col<C; col++) {
            if(!visited[row][col] && res[row][col] == 1) {
                queue.offer(new Pair(row, col));
                visited[row][col] = true;
            }
        }
        //Left
        col = 0;
        for(row=0; row<R; row++) {
            if(!visited[row][col] && res[row][col] == 1) {
                queue.offer(new Pair(row, col));
                visited[row][col] = true;
            }
        }
        //Right
        col = C-1;
        for(row=0; row<R; row++) {
            if(!visited[row][col] && res[row][col] == 1) {
                queue.offer(new Pair(row, col));
                visited[row][col] = true;
            }
        }
        
        while(!queue.isEmpty()) {
            int r = queue.peek().row;
            int c = queue.peek().col;
            queue.poll();
            for(int i=0; i<4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];
                if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && 
                        !visited[nrow][ncol] && res[nrow][ncol] == 1) {
                    queue.offer(new Pair(nrow, ncol));
                    visited[nrow][ncol] = true;
                } 
            }
        }
        int count = 0;
        for(row=0; row<R; row++) {
            for(col=0; col<C; col++) {
                if(!visited[row][col] && res[row][col] == 1) count++;
            }
        }
        return count;
    }
}