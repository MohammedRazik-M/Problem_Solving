class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int index=0; index<nums.length; index++) {
            int num = nums[index];
            int complement = target - num;  

            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), index};
            }
            map.put(num, index);
        }

        return new int[]{};
    }
}