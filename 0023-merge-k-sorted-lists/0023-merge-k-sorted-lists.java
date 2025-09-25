/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(ListNode list : lists) {
            ListNode temp = list;

            while(temp!=null) {
                minHeap.offer(temp.val);
                temp = temp.next;
            }
        }

        ListNode head, curr, prev;

        head = curr = prev = null;

        if(!minHeap.isEmpty()) head = new ListNode(minHeap.poll());
        prev = head;
        
        while(!minHeap.isEmpty()) {
            curr = new ListNode(minHeap.poll());
            prev.next = curr;
            prev = curr;
        }

        return head;
    }
}