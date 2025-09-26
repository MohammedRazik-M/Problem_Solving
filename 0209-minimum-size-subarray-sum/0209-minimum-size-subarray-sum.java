class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left, right, currSum, minSize;
        int n = nums.length;

        left = right = currSum = 0;
        minSize = Integer.MAX_VALUE;

        while(right < n) {
            currSum += nums[right];

            while(currSum >= target) {
                minSize = Math.min(minSize, right - left + 1);
                currSum -= nums[left];
                left++;
            }

            right++;
        }

        return minSize == Integer.MAX_VALUE ? 0 : minSize;
    }
}