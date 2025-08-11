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
    public boolean isPalindrome(ListNode head) {

        if(head.next == null) return true;

        ListNode fast, slow;

        Stack<Integer> stack = new Stack<>();

        fast = slow = head;

        while(fast != null && fast.next != null) {
            stack.add(slow.val);
            fast = fast.next.next;
            slow = slow.next;
        }

        if(fast != null) slow = slow.next;
        
        ListNode middleNode = slow;
        System.out.print(middleNode.val);

        while(middleNode != null) {
            if(stack.isEmpty() || stack.peek() != middleNode.val) return false;

            stack.pop();
            middleNode = middleNode.next;
        }

        return true;
    }    
        
}