//{ Driver Code Starts
//Initial template for JAVA

import java.util.*;
import java.io.*;
import java.lang.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int n = Integer.parseInt(st[0]);
            int m = Integer.parseInt(st[1]);

            for (int i = 0; i < n; i++)
                list.add(i, new ArrayList<Integer>());

            ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
            for (int i = 1; i <= m; i++) {
                String s[] = read.readLine().trim().split("\\s+");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                list.get(v).add(u);
                ArrayList<Integer> pair = new ArrayList<>();
                pair.add(u);
                pair.add(v);
                prerequisites.add(pair);
            }

            int[] res = new Solution().findOrder(n, m, prerequisites);
            
            if(res.length==0)
                System.out.println("No Ordering Possible");
            else
            {
                if (check(list, n, res) == true)
                    System.out.println("1");
                else
                    System.out.println("0");
            }
        
System.out.println("~");
}
    }
    static boolean check(ArrayList<ArrayList<Integer>> list, int V, int[] res) {
        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res[i]] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : list.get(i)) {
                if (map[i] > map[v]) return false;
            }
        }
        return true;
    }
}

// } Driver Code Ends


class Solution
{
    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) 
    {
        ArrayList<Integer> validOrdering = new ArrayList<>();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<n; i++) adjList.add(new ArrayList<>());
        int[] indegree = new int[n];
        for(ArrayList<Integer> prerequisite : prerequisites) {
            int u = prerequisite.get(1);
            int v = prerequisite.get(0);
            adjList.get(u).add(v);
            indegree[v]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            if(indegree[i] == 0) queue.add(i);
        }
        while(!queue.isEmpty()) {
            int node = queue.poll();
            validOrdering.add(node);
            for(int it : adjList.get(node)) {
                indegree[it]--;
                if(indegree[it] == 0) queue.add(it);
            }
        }
        if(validOrdering.size() == n) {
            int N = validOrdering.size();
            int[] res = new int[N];
            for(int i=0; i<N; i++) {
                res[i] = validOrdering.get(i);
            }
            return res;
        }
        
        return new int[]{};        
    }
}