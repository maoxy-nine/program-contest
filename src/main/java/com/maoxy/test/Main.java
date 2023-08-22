package com.maoxy.test;

import com.maoxy.model.Customer;
import com.maoxy.task.MessageScheduler;

/**
 * @author maoxy
 * @date 2023/08/18 10:50
 */
public class Main {
    public static void main(String[] args) {
        MessageScheduler scheduler = new MessageScheduler();

        // Schedule messages with different priorities and expiration times
        scheduler.scheduleMessage(new Customer("email1@example.com", "1234567890"), 2, System.currentTimeMillis() + 5000);
        scheduler.scheduleMessage(new Customer("email2@example.com", "9876543210"), 1, System.currentTimeMillis() + 10000);
        scheduler.scheduleMessage(new Customer("email3@example.com", "5555555555"), 3, System.currentTimeMillis() + 3000);

        // Process messages in batches of size 2
        scheduler.processMessages(2);
    }
}
