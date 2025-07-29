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
class Solution 
{
    public ListNode rotateRight(ListNode head, int k) 
    {
        ListNode tptr, shadow;
        if(head == null)
        {
            return null;
        }
        shadow = null;
        int l = 0;
        for(tptr = head; tptr!=null; l++, tptr = tptr.next);
        k%=l;
        while(k!=0)
        {
            for(tptr=head; tptr.next!=null; shadow = tptr, tptr = tptr.next);
            shadow.next = null;
            tptr.next = head;
            head = tptr;
            k--;
        }
        return head;
    }
}