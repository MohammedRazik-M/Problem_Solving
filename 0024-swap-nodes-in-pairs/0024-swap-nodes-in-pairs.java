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
    public ListNode swapPairs(ListNode head) {
        
        if(head == null) return head;
        ListNode temp = head;
        int len = 0;
        while(temp != null) {
            len++;
            temp = temp.next;
        }
        
        if(len == 1) return head;

        if(len == 2) {
            int data = head.next.val;
            head.next.val = head.val;
            head.val = data;

            return head;
        }

        ListNode curr = head.next;
        ListNode shadow = head;

        while(curr != null && shadow != null) {
            int data = curr.val;
            curr.val = shadow.val;
            shadow.val = data;

            if(shadow.next.next != null && curr.next.next != null) {
                shadow = shadow.next.next;
                curr = curr.next.next;
            }
            else break;
        }
        
        return head;
    }
}