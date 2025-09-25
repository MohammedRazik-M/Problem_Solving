class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left, right;

        left = right = 0;

        int n = s.length();

        int[] freq = new int[256];
        int maxLen = 0;

        while(right < n) {
            char ch = s.charAt(right);
            freq[ch]++;

            while(freq[ch] > 1) {
                freq[s.charAt(left)]--;
                left++;
            }

            if(right - left + 1 > maxLen) {
                maxLen = right - left + 1;
            }

            right++;
        }

        return maxLen;
    }
}