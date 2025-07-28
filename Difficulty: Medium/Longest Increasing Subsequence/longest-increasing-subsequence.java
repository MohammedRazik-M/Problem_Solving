class Solution {
    static int f(int index, int prev_index, int[] arr, int n, int[][] dp) {
        
        if(index == n) return 0;
        if(dp[index][prev_index+1] != -1) return dp[index][prev_index+1];
        
        int notTake = f(index+1, prev_index, arr, n, dp);
        
        int take = 0;
        if(prev_index == -1 || arr[prev_index] < arr[index]) {
            take = 1 + f(index+1, index, arr, n, dp);
        }
        
        return dp[index][prev_index+1] = Math.max(take, notTake);
    }
    
    static int lis(int arr[]) {
        int n = arr.length;
        
        int[][] dp = new int[n][n+1];
        
        for(int[] d : dp) Arrays.fill(d, -1);
        
        return f(0, -1, arr, n, dp);
    }
}