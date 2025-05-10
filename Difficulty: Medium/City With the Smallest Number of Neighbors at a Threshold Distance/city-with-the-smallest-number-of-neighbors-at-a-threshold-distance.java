//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {

            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] adj = new int[m][3];

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < 3; j++) {
                    adj[i][j] = sc.nextInt();
                }
            }

            int dist = sc.nextInt();
            Solution obj = new Solution();
            int ans = obj.findCity(n, m, adj, dist);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


// User function Template for Java

class Solution {
    int findCity(int n, int m, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                dist[i][j] = (int)1e9;
            }
        }
        
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int distance = edge[2];
            dist[u][v] = distance;
            dist[v][u] = distance;
        }
        
        for(int i=0; i<n; i++) dist[i][i] = 0;
        
        for(int k=0; k<n; k++) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(dist[i][k] != (int)1e9 && dist[k][j] != (int)1e9) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        
        int cityNode = -1;
        int cityMin = distanceThreshold + 1;
        for(int i=0; i<n; i++) {
            int neighborCityCount = 0;
            for(int j=0; j<n; j++) {
                if(dist[i][j] <= distanceThreshold) {
                    neighborCityCount++;
                }
            }
            if(neighborCityCount <= cityMin) {
                cityMin = neighborCityCount;
                cityNode = i;
            }
        }
        return cityNode;
    }
}
