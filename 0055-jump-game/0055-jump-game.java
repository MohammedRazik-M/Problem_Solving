class Solution {
    public boolean canJump(int[] nums) {
        int maxIndex = 0;
        for(int index=0; index<nums.length; index++) {
            
            if(index > maxIndex) return false;

            maxIndex = Math.max(maxIndex, index+nums[index]);

            if(maxIndex >= nums.length-1) return true;

        }

        return false;
    }
}