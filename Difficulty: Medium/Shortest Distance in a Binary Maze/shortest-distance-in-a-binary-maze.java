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
            int[] source = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                source[i] = x;
            }
            int[] dest = new int[2];
            for (int i = 0; i < 2; i++) {
                int x = sc.nextInt();
                dest[i] = x;
            }
            Solution ob = new Solution();
            int ans = ob.shortestPath(grid, source, dest);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends

class Pair {
    int cost, row, col;
    public Pair(int cost, int row, int col) {
        this.cost = cost;
        this.row = row;
        this.col = col;
    }
}

class Solution {
    
    private boolean isValid(int n, int m, int row, int col, int[][] grid) {
        return (row >= 0 && row < n && col >= 0 && col < m && grid[row][col] != 0);
    }
    
    private boolean isDestination(int row, int col, int[] destination) {
        return (row == destination[0] && col == destination[1]);
    }
    
    int shortestPath(int[][] grid, int[] source, int[] destination) {
        
        if(source[0] == destination[0] && source[1] == destination[1]) return 0;
        if(grid[destination[0]][destination[1]] != 1 || grid[source[0]][source[1]] != 1) return -1;
        
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        
        int n = grid.length;
        int m = grid[0].length;
        
        int[][] distance = new int[n][m];
        for(int[] _1d : distance) {
            Arrays.fill(_1d, (int)1e9);
        }
        
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(0, source[0], source[1]));
        distance[source[0]][source[1]] = 0;
        
        while(!queue.isEmpty()) {
            int cost = queue.peek().cost;
            int row = queue.peek().row;
            int col = queue.peek().col;
            queue.poll();
            
            //Navigating all 4 directions
            for(int i=0; i<4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                //Checking if they're valid and cost is minimum.
                if(isValid(n, m, nrow, ncol, grid) && cost + 1 < distance[nrow][ncol]) {
                    distance[nrow][ncol] = cost + 1;
                    //If current cell is destination, return the distance computed.
                    if(isDestination(nrow, ncol, destination)) return distance[nrow][ncol];
                    queue.offer(new Pair(distance[nrow][ncol], nrow, ncol));                    
                }
            }
        }
        //Destination is unreachable.
        return -1;
    }
}
