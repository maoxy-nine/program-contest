package com.maoxy.task;

import com.maoxy.model.Customer;

/**
 * @author maoxy
 * @date 2023/08/18 10:43
 */
public class MessageTask implements Comparable<MessageTask> {

    private final Customer customer;
    private final int priority;
    private final long expirationTime;

    public MessageTask(Customer customer, int priority, long expirationTime) {
        this.customer = customer;
        this.priority = priority;
        this.expirationTime = expirationTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getPriority() {
        return priority;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    @Override
    public int compareTo(MessageTask o) {
        if (this.priority != o.priority) {
            return Integer.compare(this.priority, o.priority);
        }
        return Long.compare(this.expirationTime, o.expirationTime);
    }
}
