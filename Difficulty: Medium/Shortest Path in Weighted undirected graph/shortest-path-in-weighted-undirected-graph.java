//{ Driver Code Starts
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static PrintWriter ot;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        ot = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String s[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
            int edges[][] = new int[m][3];
            for (int i = 0; i < m; i++) {
                s = br.readLine().trim().split(" ");
                edges[i][0] = Integer.parseInt(s[0]);
                edges[i][1] = Integer.parseInt(s[1]);
                edges[i][2] = Integer.parseInt(s[2]);
            }

            List<Integer> list = new Solution().shortestPath(n, m, edges);

            ot.println(list.get(0));
            if (list.get(0) != -1 && !check(list, edges)) ot.println(-1);
        }
        ot.close();
    }

    static boolean check(List<Integer> list, int edges[][]) {
        Set<Integer> hs = new HashSet<>();
        Map<Integer, Map<Integer, Integer>> hm = new HashMap<>();
        for (int i = 1; i < list.size(); i++) hs.add(list.get(i));
        for (int x[] : edges) {
            if (hs.contains(x[0]) || hs.contains(x[1])) {
                if (!hm.containsKey(x[0])) hm.put(x[0], new HashMap<>());
                if (!hm.containsKey(x[1])) hm.put(x[1], new HashMap<>());
                hm.get(x[0]).put(x[1], x[2]);
                hm.get(x[1]).put(x[0], x[2]);
            }
        }
        int sum = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            if (!hm.containsKey(list.get(i)) ||
                !hm.get(list.get(i)).containsKey(list.get(i + 1)))
                return false;
            sum += hm.get(list.get(i)).get(list.get(i + 1));
        }
        return sum == list.get(0);
    }
}

// } Driver Code Ends

class Pair {
    int node, weight;
    public Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        List<Integer> path = new ArrayList<>();        
        List<List<Pair>> adjList = new ArrayList<>();
        for(int i=0; i<=n; i++) adjList.add(new ArrayList<>());
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            adjList.get(u).add(new Pair(v, wt));
            adjList.get(v).add(new Pair(u, wt));
        }
        
        int[] dist = new int[n+1];
        int[] parent = new int[n+1];
        for(int node=1; node<=n; node++) {
            parent[node] = node;
            dist[node] = (int)1e9;
        }
        dist[1] = 0;
         
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(new Pair(1, 0));
        
        while(!pq.isEmpty()) {
            int weight = pq.peek().weight;
            int node = pq.peek().node;
            pq.poll();
            
            for(Pair pair : adjList.get(node)) {
                int adjNode = pair.node;
                int adjWt = pair.weight;
                int newWt = weight + adjWt;
                if(newWt < dist[adjNode]) {
                    dist[adjNode] = newWt;
                    parent[adjNode] = node;
                    pq.offer(new Pair(adjNode, newWt));
                } 
            }
        }
        if(dist[n] == (int)1e9) {
            path.add(-1);
            return path;
        }
        Stack<Integer> stack = new Stack<>();
        int node = n;
        while(parent[node] != node) {
            stack.push(node);
            node = parent[node];
        }
        stack.push(node);
        while(!stack.isEmpty()) {
            path.add(stack.pop());
        }
        path.add(0, dist[n]);
        return path;
    }
}