//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            String temp[] = sc.nextLine().trim().split(" ");
            int n = temp.length;
            int exits[] = new int[n];
            for (int i = 0; i < n; i++) exits[i] = Integer.parseInt(temp[i]);
            Solution sln = new Solution();
            System.out.println(sln.maxWeightCell(exits));
            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class Solution {
    public int maxWeightCell(int[] exits) {
        int n = exits.length;
        int[] weight = new int[n];
        for(int node=0; node<n; node++) {
            int to = exits[node];
            if(to != -1) {
                weight[to] += node;
            }
        }
        int maxWeight = -1;
        int maxNode = -1;
        for(int node=0; node<n; node++) {
            if(weight[node] >= maxWeight) {
                maxWeight = weight[node];
                maxNode = node;
            }
        }
        return maxNode;
    }
}