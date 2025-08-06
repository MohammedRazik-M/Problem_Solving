class Solution {
    public int lengthOfLastWord(String s) {
        int n = s.length();

        int index = n-1, len = 0;
        
        for(; index>=0 && s.charAt(index) == ' '; index--) {}

        for(; index>=0 && s.charAt(index) != ' '; index--, len++) {}

        return len; 
    }
}