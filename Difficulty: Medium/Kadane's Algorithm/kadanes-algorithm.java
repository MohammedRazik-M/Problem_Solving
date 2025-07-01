class Solution {
    int maxSubarraySum(int[] arr) {
        int maxSum = arr[0];
        
        int currSum = (arr[0] > 0 ? arr[0] : 0);
        
        int N = arr.length;
        
        for(int index=1; index<N; index++) {
            currSum += arr[index];
            
            if(currSum > maxSum) maxSum = currSum;
            
            if(currSum < 0) currSum = 0;
        }
        return maxSum;
    }
}
