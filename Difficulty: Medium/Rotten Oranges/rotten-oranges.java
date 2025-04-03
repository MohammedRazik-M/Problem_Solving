//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int mat[][] = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) mat[i][j] = sc.nextInt();
            }
            Solution obj = new Solution();
            int ans = obj.orangesRotting(mat);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}
// } Driver Code Ends

class Pair {
    int row, col, time;
    Pair(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

class Solution {
    public int orangesRotting(int[][] mat) {
        int R = mat.length;
        int C = mat[0].length;
        Queue<Pair> queue = new LinkedList<>();
        int[][] visited = new int[R][C];
        int freshOranges = 0;
        for(int row=0; row<R; row++) {
            for(int col=0; col<C; col++) {
                if(mat[row][col] == 2) {
                    queue.add(new Pair(row, col, 0));
                    visited[row][col] = 2;
                }
                if(mat[row][col] == 1) freshOranges++;
            }
        }
        int timeTaken = 0, gotRottenCnt = 0;
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        while(!queue.isEmpty()) {
            int row = queue.peek().row;
            int col = queue.peek().col;
            int time = queue.peek().time;
            timeTaken = Math.max(timeTaken, time);
            queue.poll();
            for(int i=0; i<4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && visited[nrow][ncol] != 2 && mat[nrow][ncol] == 1) {
                    queue.add(new Pair(nrow, ncol, time+1));
                    visited[nrow][ncol] = 2;
                    gotRottenCnt++;
                }
            }
        }
        if(freshOranges != gotRottenCnt) return -1;
        return timeTaken;
    }
}