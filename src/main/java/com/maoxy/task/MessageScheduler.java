package com.maoxy.task;

import com.maoxy.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author maoxy
 * @date 2023/08/18 10:48
 */
public class MessageScheduler {
    private final Queue<MessageTask> taskQueue = new PriorityQueue<>((task1, task2) -> {
        if (task1.getPriority() != task2.getPriority()) {
            return Integer.compare(task1.getPriority(), task2.getPriority());
        }
        return Long.compare(task1.getExpirationTime(), task2.getExpirationTime());
    });

    public void scheduleMessage(Customer customer, int priority, long expirationTime) {
        MessageTask task = new MessageTask(customer, priority, expirationTime);
        taskQueue.offer(task);
    }

    public void processMessages(int batchSize) {
        List<MessageTask> batch = new ArrayList<>();
        while (!taskQueue.isEmpty() && batch.size() < batchSize) {
            batch.add(taskQueue.poll());
        }

        for (MessageTask task : batch) {
            sendMessage(task.getCustomer());
        }
    }

    private void sendMessage(Customer customer) {
        // Simulate sending email or SMS
        System.out.println("Sending message to: " + customer.getEmail() + " or " + customer.getPhoneNumber());
    }
}
