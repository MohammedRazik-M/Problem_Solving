class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        // Left Scan
        for(int index=1; index<n; index++) {
            if(ratings[index] > ratings[index-1]) dp[index] = 1 + dp[index-1];
        }

        int minCandies = dp[n-1];

        // Right Scan
        for(int index=n-1; index>=1; index--) {
            if(ratings[index-1] > ratings[index]) {
                dp[index-1] = Math.max(dp[index-1], 1 + dp[index]);
            }
            minCandies += dp[index-1];
        }

        return minCandies;
    }
}