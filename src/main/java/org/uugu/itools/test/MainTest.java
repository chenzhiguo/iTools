package org.uugu.itools.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Silence on 15/5/12.
 */
public class MainTest {

    public static void main(String[] args){

        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

//        //线程池
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(() -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println("Hello " + threadName);
//        });
//
//        //接收线程执行结果
//        Callable<Integer> task = () -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//                return 123;
//            }
//            catch (InterruptedException e) {
//                throw new IllegalStateException("task interrupted", e);
//            }
//        };
//        ExecutorService executor2 = Executors.newFixedThreadPool(1);
//        Future<Integer> future = executor2.submit(task);
//
//        System.out.println("future done? " + future.isDone());
//
//        Integer result = null;
//        try {
//            result = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("future done? " + future.isDone());
//        System.out.print("result: " + result);
//
//        List list = new ArrayList<>();
//        System.out.println("=========" + list.stream().distinct().count());

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task2 = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(task2, 3, TimeUnit.SECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long remainingDelay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);
        System.out.println();
        scheduledExecutorService.shutdown();

        LocalDate dateOfBirth = LocalDate.of(2010, 01, 14);
        System.out.println("Your Date of birth is : " + dateOfBirth);

    }
}
