//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // Number of test cases
        while (T-- > 0) {
            int n = sc.nextInt(); // Number of accounts
            ArrayList<ArrayList<String>> accounts = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int x = sc.nextInt(); // Number of emails + name
                ArrayList<String> temp = new ArrayList<>();
                for (int j = 0; j < x; j++) {
                    temp.add(sc.next()); // Read name/emails
                }
                accounts.add(temp);
            }

            // Calling the accountsMerge function
            Solution obj = new Solution();
            ArrayList<ArrayList<String>> res = obj.accountsMerge(accounts);

            // Sorting individual accounts' emails
            for (int i = 0; i < res.size(); i++) {
                Collections.sort(res.get(i));
            }

            // Sorting the entire list exactly like C++'s sort(res.begin(), res.end());
            Collections.sort(res, new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> a, ArrayList<String> b) {
                    int al = a.size();
                    int bl = b.size();
                    int min = Math.min(al, bl);
                    for (int i = 0; i < min; i++) {
                        int cmp = a.get(i).compareTo(b.get(i));
                        if (cmp < 0) return -1;
                        if (cmp > 0) return 1;
                    }
                    if (al < bl) return -1;
                    if (al > bl) return 1;
                    return -1; // Exact match with C++ behavior
                }
            });

            // Printing the output in the required format
            System.out.print("[");
            for (int i = 0; i < res.size(); i++) {
                System.out.print("[");
                for (int j = 0; j < res.get(i).size(); j++) {
                    if (j != res.get(i).size() - 1) {
                        System.out.print(res.get(i).get(j) + ", ");
                    } else {
                        System.out.print(res.get(i).get(j));
                    }
                }
                if (i != res.size() - 1) {
                    System.out.println("], ");
                } else {
                    System.out.print("]");
                }
            }
            System.out.println("]");
            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class DisjointSet {
    int[] parent, size;
    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    public int findUParent(int node) {
        if(parent[node] == node) return node;
        return parent[node] = findUParent(parent[node]);
    }
    public void unionBySize(int u, int v) {
        int ulp_u = findUParent(u);
        int ulp_v = findUParent(v);
        if(ulp_u == ulp_v) return;
        if(size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

class Solution {
    static ArrayList<ArrayList<String>> accountsMerge(
        ArrayList<ArrayList<String>> accounts) {
        
        int n = accounts.size();
        
        DisjointSet ds = new DisjointSet(n);
        
        Map<String, Integer> emailMap = new HashMap<>();
        
        for(int i=0; i<n; i++) {
            for(int j=1; j<accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if(!emailMap.containsKey(email)) emailMap.put(email, i);
                else ds.unionBySize(i, emailMap.get(email));
            }
        }

        Map<Integer, List<String>> accMails = new HashMap<>();
        for(Map.Entry<String, Integer> entry : emailMap.entrySet()) {
            String email = entry.getKey();
            int acc_no = ds.findUParent(entry.getValue());
            accMails.computeIfAbsent(acc_no, k -> new ArrayList<>()).add(email);
        }
        
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        
        for(Map.Entry<Integer, List<String>> entry : accMails.entrySet()) {
            String name = accounts.get(entry.getKey()).get(0);
            
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            
            ArrayList<String> temp = new ArrayList<>();
            temp.add(name);
            
            for(String email : emails) temp.add(email);
            
            res.add(temp);
        }
        return res;
    }
}
