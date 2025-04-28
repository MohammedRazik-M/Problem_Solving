//{ Driver Code Starts
// Initial Template for Java

import java.math.*;
import java.util.*;

class Multiply {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            String a = sc.next();
            String b = sc.next();
            Solution g = new Solution();
            System.out.println(g.multiplyStrings(a, b));

            System.out.println("~");
        }
    }
}
// } Driver Code Ends

class Solution {
    public String multiplyStrings(String s1, String s2) {
        boolean negative = false;
        if(s1.charAt(0) == '-' && s2.charAt(0) != '-') {
            negative = true;
            s1 = s1.substring(1);
        } 
        else if(s2.charAt(0) == '-' && s1.charAt(0) != '-') {
            negative = true;
            s2 = s2.substring(1);
        } 
        else if(s1.charAt(0) == '-' && s2.charAt(0) == '-') {
            s1 = s1.substring(1);
            s2 = s2.substring(1);
        }
        
        if (isZero(s1) || isZero(s2)) {
            return "0";
        }
        
        int n = s1.length();
        int m = s2.length();
        int[] result = new int[n + m];
        
        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                int mul = (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
                int sum = mul + result[i + j + 1];
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < result.length && result[i] == 0) {
            i++;
        }
        for(; i < result.length; i++) {
            sb.append(result[i]);
        }
        if(negative) {
            sb.insert(0, '-');
        }
        return sb.toString();
    }
    private boolean isZero(String s) {
        for(char c : s.toCharArray()) {
            if(c != '0') {
                return false;
            }
        }
        return true;
    }
}