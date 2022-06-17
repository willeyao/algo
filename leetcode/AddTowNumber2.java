package leetcode;

/**
 * 两数相加 https://leetcode.cn/problems/add-two-numbers/
 */
public class AddTowNumber2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        if (l1 == null && l2 == null) {
            return head;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode cur = head;
        int carry = 0;
        // 遍历两个链表
        while(l1 != null || l2 != null) {
            int l1v = l1 == null ? 0 : l1.val;
            int l2v = l2 == null ? 0 : l2.val;
            int sum = l1v + l2v + carry;
            // 进位
            carry = sum / 10;
            // 余数
            sum = sum % 10;

            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
