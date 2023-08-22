package com.maoxy.task;

import com.maoxy.model.Customer;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author maoxy
 * @date 2023/08/18 10:48
 */
public class MessageScheduler {
    private final Queue<MessageTask> taskQueue = new PriorityQueue<>();
    private final ExecutorService executorService;
    private final Logger LOGGER = Logger.getLogger(MessageScheduler.class.getName());
    public MessageScheduler(int maxThreads) {
        // Create a thread pool with a fixed size and daemon threads
        int corePoolSize = maxThreads / 2;
        long keepAliveTime = 60L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        executorService = new ThreadPoolExecutor(
                corePoolSize,
                maxThreads,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    public void scheduleMessage(Customer customer, int priority, long expirationTime) {
        MessageTask task = new MessageTask(customer, priority, expirationTime);
        taskQueue.offer(task);
    }

    public void processMessages(int batchSize, long timeout) {
        int tasksToProcess = Math.min(batchSize, taskQueue.size());
        for (int i = 0; i < tasksToProcess; i++) {
            MessageTask task = taskQueue.poll();
            if (task != null) {
                executorService.submit(() -> sendMessage(task.getCustomer()));
            }
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Thread pool termination interrupted", e);
            executorService.shutdownNow();
        }
    }

    private void sendMessage(Customer customer) {
        // Simulate sending email or SMS
        LOGGER.info("Sending message to: " + customer.getEmail() + " or " + customer.getPhoneNumber());
        //System.out.println("Sending message to: " + customer.getEmail() + " or " + customer.getPhoneNumber());
    }
}
