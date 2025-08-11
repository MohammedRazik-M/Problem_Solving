/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
       // Rabbit tortise 
       ListNode rabbit, tortoise;

       rabbit = tortoise = head;

       while(rabbit != null && rabbit.next != null) {
            rabbit = rabbit.next.next;
            tortoise = tortoise.next;

            if(rabbit == tortoise) return true;
       }

       return false;
    }
}