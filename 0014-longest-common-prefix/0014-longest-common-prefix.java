class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        int len = Integer.MAX_VALUE;

        if(strs == null || strs.length == 0) return "";

        if(n == 1) return strs[0];

        for(String str : strs) len = Math.min(len, str.length());

        StringBuilder result = new StringBuilder();
        for(int index=0; index<len; index++) {
            char ch = strs[0].charAt(index);
            for(int k=1; k<n; k++) {
                if(strs[k].charAt(index) != ch) {
                    return result.toString();
                }
            }
            result.append(ch);
        }
        return result.toString();
    }
}