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
            int m = Integer.parseInt(br.readLine().trim());
            int[][] image = new int[n][m];
            for (int i = 0; i < n; i++) {
                String[] S2 = br.readLine().trim().split(" ");
                for (int j = 0; j < m; j++) image[i][j] = Integer.parseInt(S2[j]);
            }
            int sr = Integer.parseInt(br.readLine().trim());
            int sc = Integer.parseInt(br.readLine().trim());
            int newColor = Integer.parseInt(br.readLine().trim());
            Solution obj = new Solution();
            int[][] ans = obj.floodFill(image, sr, sc, newColor);
            for (int i = 0; i < ans.length; i++) {
                for (int j = 0; j < ans[i].length; j++)
                    System.out.print(ans[i][j] + " ");
                System.out.println();
            }

            System.out.println("~");
        }
    }
}

// } Driver Code Ends


class Solution {
    int[] drow = {-1, 1, 0, 0};
    int[] dcol = {0, 0, -1, 1};
    private void dfs(int row, int col, int R, int C, int[][] resultant_image, boolean[][] visited, int newColor, int baseColor) {
        visited[row][col] = true;
        resultant_image[row][col] = newColor;
        for(int i=0; i<4; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];
            if(nrow >= 0 && nrow < R && ncol >= 0 && ncol < C && !visited[nrow][ncol] && resultant_image[nrow][ncol] == baseColor) {
                dfs(nrow, ncol, R, C, resultant_image, visited, newColor, resultant_image[nrow][ncol]);                
            }
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int R = image.length;
        int C = image[0].length;
        int[][] resultant_image = new int[R][C];
        for(int row=0; row<R; row++) {
            for(int col=0; col<C; col++) {
                resultant_image[row][col] = image[row][col];
            }
        }
        boolean[][] visited = new boolean[R][C];
        int baseColor = resultant_image[sr][sc];
        dfs(sr, sc, R, C, resultant_image, visited, newColor, baseColor);
        return resultant_image;
    }
}