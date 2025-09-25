class Solution {
    public int maxSubArray(int[] nums) {
        int currSum, maxSum;
        int n = nums.length;

        currSum = maxSum = nums[0];

        for(int index=1; index<n; index++) {
            if(currSum < 0) currSum = 0;
            
            currSum += nums[index];
            
            if(currSum > maxSum) maxSum = currSum;
            
        }

        return maxSum;
    }
}