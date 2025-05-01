//{ Driver Code Starts
import java.io.*;
import java.util.*;

class IntMatrix {
    public static int[][] input(BufferedReader br, int n, int m) throws IOException {
        int[][] mat = new int[n][];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().trim().split(" ");
            mat[i] = new int[s.length];
            for (int j = 0; j < s.length; j++) mat[i][j] = Integer.parseInt(s[j]);
        }

        return mat;
    }

    public static void print(int[][] m) {
        for (var a : m) {
            for (int e : a) System.out.print(e + " ");
            System.out.println();
        }
    }

    public static void print(ArrayList<ArrayList<Integer>> m) {
        for (var a : m) {
            for (int e : a) System.out.print(e + " ");
            System.out.println();
        }
    }
}

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            int rows;
            rows = Integer.parseInt(br.readLine());

            int columns;
            columns = Integer.parseInt(br.readLine());

            int[][] heights = IntMatrix.input(br, rows, columns);

            Solution obj = new Solution();
            int res = obj.MinimumEffort(rows, columns, heights);

            System.out.println(res);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends

class Pair {
    int distance, row, col;
    public Pair(int distance, int row, int col) {
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
} 


class Solution {
    public static int MinimumEffort(int rows, int columns, int[][] heights) {
        
        int[] drow = {-1, 1, 0, 0};
        int[] dcol = {0, 0, -1, 1};
        
        int[][] dist = new int[rows][columns];
        for(int[] d : dist) Arrays.fill(d, (int)1e9);
        
        dist[0][0] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.offer(new Pair(0, 0, 0));
        
        while(!pq.isEmpty()) {
            int diff = pq.peek().distance;
            int row = pq.peek().row;
            int col = pq.peek().col;
            pq.poll();
            
            if(row == rows-1 && col == columns-1) return diff;
            
            for(int i=0; i<4; i++) {
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                
                if(nrow >= 0 && nrow < rows && ncol >= 0 && ncol < columns) {
                    int newEffort = Math.max(Math.abs(heights[row][col] - heights[nrow][ncol]), diff); 
                    if(newEffort < dist[nrow][ncol]) {
                        dist[nrow][ncol] = newEffort;
                        pq.offer(new Pair(newEffort, nrow, ncol));
                    }
                }
            }
        }
        return 0;
    }
}
