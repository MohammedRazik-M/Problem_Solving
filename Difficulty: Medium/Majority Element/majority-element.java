//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.lang.*;
import java.util.*;

class Geeks {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int g = 0; g < t; g++) {
            String[] str = (br.readLine()).trim().split(" ");
            int arr[] = new int[str.length];
            for (int i = 0; i < str.length; i++) arr[i] = Integer.parseInt(str[i]);
            System.out.println(new Solution().majorityElement(arr));
            System.out.println("~");
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    static int majorityElement(int arr[]) {
        Arrays.sort(arr);
        int n = arr.length;
        if(n == 1) return arr[0];
        int count = 1;
        for(int index=1; index<n; index++) {
            if(arr[index] == arr[index-1]) {
                count++;
                if(count > n/2) {
                    return arr[index];
                }
            }
            else {
                count = 1;
            }
        }
        return -1;
    }
}