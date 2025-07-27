class Solution {
    public int countHillValley(int[] nums) {

        int HillValleyCount = 0;
        int n = nums.length;

        for(int index=1; index<n-1; index++) {
            int prev = nums[index-1];
            int curr = nums[index];
            int next = nums[index+1];

            if(prev == curr) continue;

            // int i = index-2;
            // if(i >= 0 && prev == curr) {
            //     while(i >= 0) {
            //         if(nums[i] != curr) {
            //             prev = nums[i];
            //             break;
            //         }
            //         i--;
            //     }
            // }

            int j = index+2;
            if(j <= n-1 && curr == next) {
                while(j <= n-1) {
                    if(nums[j] != curr) {
                        next = nums[j];
                        break;
                    }
                    j++;
                }
            }

            if(prev > curr && curr < next) HillValleyCount++;
            else if(prev < curr && curr > next) HillValleyCount++;

        }

        return HillValleyCount;
    }
}