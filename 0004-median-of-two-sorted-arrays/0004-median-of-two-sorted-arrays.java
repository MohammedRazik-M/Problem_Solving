class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int num : nums1) pq.offer(num);

        for(int num : nums2) pq.offer(num);


        int[] res_arr = new int[pq.size()];
        int k = 0;

        while(!pq.isEmpty()) res_arr[k++] = pq.poll();

        double result;

        if(k%2 == 0) result = (res_arr[k/2-1] + res_arr[k/2])/2.0;
        else result = res_arr[k/2]/1.0;

        return result;
    }
}