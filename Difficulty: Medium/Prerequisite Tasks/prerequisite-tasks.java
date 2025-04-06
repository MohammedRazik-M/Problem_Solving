//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
	public static void main(String args[]) throws IOException
	{
	    Scanner sc = new Scanner(System.in);
	    int t = sc.nextInt();
		while(t>0)
		{
		    int N = sc.nextInt();
		    int P = sc.nextInt();
		    int prerequisites[][] = new int[P][2];
		    for(int i=0;i<P;i++)
		    {
		        for(int j=0;j<2;j++)
		        {
		            prerequisites[i][j] = sc.nextInt();
		        }
		    }
			Solution ob = new Solution();
			if(ob.isPossible(N,P,prerequisites))
			{
			    System.out.println("Yes");
			}
			else{
			    System.out.println("No");
			}
			t--;
		
System.out.println("~");
}
	}
	
}

// } Driver Code Ends


class Solution {
    public boolean isPossible(int N, int P, int[][] prerequisites)
    {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<N; i++) adjList.add(new ArrayList<>());        
        int[] indegree = new int[N];
        for(int[] prerequisite : prerequisites) {
            int u = prerequisite[1];
            int v = prerequisite[0];
            adjList.get(u).add(v);
            indegree[v]++;
        }        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<N; i++) {
            if(indegree[i] == 0) queue.add(i);
        }
        int count = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            for(int it : adjList.get(node)) {
                indegree[it]--;
                if(indegree[it] == 0) queue.add(it);
            }
        }
        return (count == N);
    }
}