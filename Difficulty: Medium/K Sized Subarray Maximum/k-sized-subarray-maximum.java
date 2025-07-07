class Solution {
    static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        
        ArrayList<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        
        for(int index=0; index<arr.length; index++) {
            if(!deque.isEmpty() && deque.peekFirst() == index - k) {
                deque.pollFirst();
            }
            while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[index]) {
                deque.pollLast();                
            }
            deque.offerLast(index);
            if(index >= k-1) res.add(arr[deque.peekFirst()]);
        }
        
        return res;
    }
}