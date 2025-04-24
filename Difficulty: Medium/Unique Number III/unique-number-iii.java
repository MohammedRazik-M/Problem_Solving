//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        while (t-- > 0) {
            String[] arr1Str = sc.nextLine().split(" ");
            int[] arr = Arrays.stream(arr1Str).mapToInt(Integer::parseInt).toArray();
            Solution ob = new Solution();
            int ans = ob.getSingle(arr);
            System.out.println(ans);

            System.out.println("~");
        }
    }
}

// } Driver Code Ends

class Solution {
    public int getSingle(int[] arr) {
        int res = 0;
        for(int i=0; i<32; i++) {
            int count = 0;
            for(int num : arr) {
                if(((num >> i) & 1) == 1) count++;
            }
            if(count%3 != 0) res |= (1 << i);
        }
        return res;
    }
}