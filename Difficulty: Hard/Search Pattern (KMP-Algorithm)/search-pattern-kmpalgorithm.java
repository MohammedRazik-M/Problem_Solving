// User function Template for Java

class Solution {

    ArrayList<Integer> search(String pat, String txt) {
        int[] lps = new int[pat.length()];
        LPS(pat, lps);
        
        int N = txt.length();
        
        ArrayList<Integer> res = new ArrayList<>();
        
        int txtIndex = 0, patIndex = 0, matchCount = 0;
        while(txtIndex < N) {
            if(txt.charAt(txtIndex) == pat.charAt(patIndex)) {
                txtIndex++;
                patIndex++;
            }
            if(patIndex == pat.length()) {
                matchCount++;
                res.add(txtIndex - patIndex);
                patIndex = lps[patIndex-1];
            }
            else if(txtIndex < N && txt.charAt(txtIndex) != pat.charAt(patIndex)) {
                if(patIndex != 0) {
                    patIndex = lps[patIndex-1];
                }
                else {
                    txtIndex++;
                }
            }
        }
        return res;
    }
    
    void LPS(String pat, int[] lps) {
        int i = 1, len = 0;
        
        while(i < pat.length()) {
            if(pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if(len != 0) {
                    len = lps[len-1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}