package com.example.FetchRewards;

public class ListNode {
    ListNode next;
    ListNode prev;
    Transactions transactions;
    int updatedValue = 0;

    public ListNode() {
    }

    public ListNode(Transactions transactions) {
        this.transactions = transactions;
    }
}
