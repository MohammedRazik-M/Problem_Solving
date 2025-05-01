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
            int N = Integer.parseInt(br.readLine().trim());
            String[] S1 = br.readLine().trim().split(" ");
            String[] S2 = br.readLine().trim().split(" ");
            int[] KnightPos = new int[2];
            int[] TargetPos = new int[2];
            for(int i = 0; i < 2; i++){
                KnightPos[i] = Integer.parseInt(S1[i]);
                TargetPos[i] = Integer.parseInt(S2[i]);
            }
            Solution obj = new Solution();
            int ans = obj.minStepToReachTarget(KnightPos, TargetPos, N);
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
    private boolean isValid(int row, int col, int N) {
        return (row >= 0 && row < N && col >= 0 && col < N);
    }
    
    public int minStepToReachTarget(int KnightPos[], int TargetPos[], int N) {
       
       if(KnightPos[0] == TargetPos[0] && KnightPos[1] == TargetPos[1]) return 0;
       
       int[] drow = {-2, -2, -1, -1, 2, 2, 1, 1};
       int[] dcol = {-1, 1, -2, 2, -1, 1, -2, 2};
       
       int[][] board = new int[N][N];
       
       int knightRow = KnightPos[0] - 1;
       int knightCol = KnightPos[1] - 1;
       int targetRow = TargetPos[0] - 1;
       int targetCol = TargetPos[1] - 1;
       
       board[knightRow][knightCol] = 1;
       board[targetRow][targetCol] = 2;
       
       int minSteps = (int)1e9;
       
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
               if(isValid(nrow, ncol, N) && board[nrow][ncol] != 3) {
                   int nSteps = steps + 1;
                   if(board[nrow][ncol] == 2) {
                       minSteps = Math.min(minSteps, nSteps);
                       continue;
                   }
                   queue.offer(new Pair(nrow, ncol, nSteps));
                   board[nrow][ncol] = 3;
               }
           }
       }
       return (minSteps == (int)1e9) ? -1 : minSteps;
    }
}