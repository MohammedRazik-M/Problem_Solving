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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode LeftPre = dummy;
        ListNode curr = head;
        for(int i=0; i<left-1;i++)
        {
            LeftPre = curr;
            curr = curr.next;
        }
        ListNode sublistHead = curr;
        ListNode next, prev;
        prev  =  null;
        for(int i=0;i<=right-left;i++)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        LeftPre.next = prev;
        sublistHead.next = curr;
        return dummy.next;
    }
}