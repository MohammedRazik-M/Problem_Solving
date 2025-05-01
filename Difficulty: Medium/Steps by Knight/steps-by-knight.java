//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] S1 = br.readLine().trim().split(" ");
            String[] S2 = br.readLine().trim().split(" ");
            int[] knightPos = new int[2];
            int[] targetPos = new int[2];
            for (int i = 0; i < 2; i++) {
                knightPos[i] = Integer.parseInt(S1[i]);
                targetPos[i] = Integer.parseInt(S2[i]);
            }
            Solution obj = new Solution();
            int ans = obj.minStepToReachTarget(knightPos, targetPos, n);
            System.out.println(ans);
            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class Pair {
    int row, col, steps;
    public Pair(int row, int col, int steps) {
        this.row = row;
        this.col = col;
        this.steps = steps;
    }
}

class Solution {
    private boolean isValid(int n, int row, int col) {
        return (row >= 0 && row < n && col >= 0 && col < n);
    }
    public int minStepToReachTarget(int knightPos[], int targetPos[], int n) {
        
        if(knightPos[0] == targetPos[0] && knightPos[1] == targetPos[1]) return 0;
        
        int[] drow = {-2, -2, -1, -1, 2, 2, 1, 1};
        int[] dcol = {-1, 1, -2, 2, -1, 1, -2, 2};
        
        int[][] board = new int[n][n];
        
        int knightRow = knightPos[0] - 1;
        int knightCol = knightPos[1] - 1;
        int targetRow = targetPos[0] - 1;
        int targetCol = targetPos[1] - 1;
        
        board[knightRow][knightCol] = 1;
        board[targetRow][targetCol] = 2;
        
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(knightRow, knightCol, 0));
        board[knightRow][knightCol] = 3; 
        
        while(!queue.isEmpty()) {
            int row = queue.peek().row;
            int col = queue.peek().col;
            int steps = queue.peek().steps;
            queue.poll();
            
            for(int i=0; i<8; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                if(isValid(n, nrow, ncol) && board[nrow][ncol] != 3) {
                    int nSteps = steps + 1;
                    if(board[nrow][ncol] == 2) return nSteps;
                    queue.offer(new Pair(nrow, ncol, nSteps));
                    board[nrow][ncol] = 3;
                }
            }
        }
        return -1;
    }
}