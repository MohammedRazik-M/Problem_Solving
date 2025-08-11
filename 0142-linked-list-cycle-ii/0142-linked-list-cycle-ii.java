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
    public ListNode detectCycle(ListNode head) {
        ListNode rabbit, tortoise;

       rabbit = tortoise = head;

       while(rabbit != null && rabbit.next != null) {
            rabbit = rabbit.next.next;
            tortoise = tortoise.next;

            if(rabbit == tortoise) {
                rabbit = head;

                while(rabbit != tortoise) {
                    rabbit = rabbit.next;
                    tortoise = tortoise.next;
                }
                return rabbit;
            }
       }

       return null;
    }
}