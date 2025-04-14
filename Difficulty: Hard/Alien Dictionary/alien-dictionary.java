//{ Driver Code Starts
// Initial Template for Java
import java.util.*;


// } Driver Code Ends

class Solution {
    private void topoSort(List<List<Integer>> adjList, int[] indegree, 
        List<Integer> topo) {
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<26; i++) {
            if(indegree[i] == 0) queue.add(i);
        }
        while(!queue.isEmpty()) {
            int node = queue.poll();
            topo.add(node);
            for(int it : adjList.get(node)) {
                indegree[it]--;
                if(indegree[it] == 0) queue.add(it);
            }
        }
    }
    public String findOrder(String[] words) {
        int N = words.length;
        
        List<Integer> topo = new ArrayList<>();
        int[] indegree = new int[26];
        boolean[] present = new boolean[26];
        for(String word : words) {
            for(char ch : word.toCharArray()) {
                present[ch - 'a'] = true;
            }
        }
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<26; i++) adjList.add(new ArrayList<>());
        
        
        for(int i=0; i<N-1; i++) {
            String s1 = words[i];
            String s2 = words[i+1];
            int len = Math.min(s1.length(), s2.length());
            
            if(s1.length() > s2.length() && s1.startsWith(s2)) return "";
            
            for(int j=0; j<len; j++) {
                char ch1 = s1.charAt(j);
                char ch2 = s2.charAt(j);
                if(ch1 != ch2) {
                    int u = ch1 - 'a';
                    int v = ch2 - 'a';
                    adjList.get(u).add(v);
                    indegree[v]++;
                    break;
                }
            }
        }
        topoSort(adjList, indegree, topo);
        StringBuilder ans = new StringBuilder();
        for(int val : topo) {
            if(present[val]) ans.append((char)(val + 'a'));
        }
        int count = 0;
        for(int i=0; i<26; i++) {
            if(present[i]) count++;
        }
        if(ans.length() < count) return "";
        return ans.toString();
    }
}


//{ Driver Code Starts.

public class GFG {
    private static boolean validate(String[] original, String order) {
        Map<Character, Integer> mp = new HashMap<>();
        for (String word : original) {
            for (char ch : word.toCharArray()) {
                mp.put(ch, 1);
            }
        }
        for (char ch : order.toCharArray()) {
            if (!mp.containsKey(ch)) {
                return false;
            }
            mp.remove(ch);
        }
        if (!mp.isEmpty()) {
            return false;
        }

        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            indexMap.put(order.charAt(i), i);
        }

        for (int i = 0; i < original.length - 1; i++) {
            String a = original[i];
            String b = original[i + 1];
            int k = 0, n = a.length(), m = b.length();

            while (k < n && k < m && a.charAt(k) == b.charAt(k)) {
                k++;
            }

            if (k < n && k < m &&
                indexMap.get(a.charAt(k)) > indexMap.get(b.charAt(k))) {
                return false;
            }
            if (k != n && k == m) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine()); // Number of test cases

        while (t-- > 0) {
            String[] words = sc.nextLine().split(" ");
            String[] original = Arrays.copyOf(words, words.length);

            Solution ob = new Solution();
            String order = ob.findOrder(words);

            if (order.isEmpty()) {
                System.out.println("\"\"");
            } else {
                System.out.println(validate(original, order) ? "true" : "false");
            }
            System.out.println("~");
        }

        sc.close();
    }
}

// } Driver Code Ends