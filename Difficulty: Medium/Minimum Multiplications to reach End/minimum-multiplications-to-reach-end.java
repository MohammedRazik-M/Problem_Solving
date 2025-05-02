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
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            Solution ob = new Solution();
            int ans = ob.minimumMultiplications(a, start, end);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends

class Pair {
    int steps, node;
    public Pair(int steps, int node) {
        this.steps = steps;
        this.node = node;
    }
}

class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {
        
        if(start == end) return 0;
        
        int mod = 100000;
        
        int[] dist = new int[mod];
        Arrays.fill(dist, (int)1e9);
        
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(0, start));
        dist[start] = 0;
        
        while(!queue.isEmpty()) {
            int steps = queue.peek().steps;
            int node = queue.peek().node;
            queue.poll();
            
            for(int num : arr) {
                int nSteps = steps + 1;
                int number = (num * node) % mod;
                if(number == end) return nSteps;
                if(nSteps < dist[number]) {
                    dist[number] = nSteps;
                    queue.offer(new Pair(nSteps, number));
                }
            }
        }
        return -1;
    }
}
