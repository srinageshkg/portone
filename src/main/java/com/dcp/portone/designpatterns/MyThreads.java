package com.dcp.portone.designpatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.dcp.portone.designpatterns.MyThreads.EOF;

public class MyThreads {
    public static final String EOF = "EOF";
    // Primitive types don't have intrinsic locks but Objects can have
    // intrinsic lock
    // ReentrantLock
    // race condition
    // Thread Starvation
    // FairLocks and LiveLocks Slipped Condition
    // atomic action, compareAndSet()
    // ShareResource
    // volatile public volatile int counter, atomic classes : boolean, integer, integer array, long, long array, obj ref, double with set() and get(), compareAndSet() expectedValue and new Value
    // private AtomicInteger counter = new AtomicInteger(0); counter.incrementAndGet()

    // Thread LifeCycle
    // New – When the instance of the thread is created and the start() method has not been invoked
    // Runnable – 1) Once the start() method is invoked, before the run() method is called by JVM 2) from wait(), sleep() states
    // Running – When the run() method has been invoked and the thread starts its execution
    // Non-Runnable (Blocked (Issue I/O request); Waiting join() or wait() -> notify() / notifyAll() ; timed_waiting sleep(sleep_interval))
    // Terminated – Once the run() method execution is completed or stop()
    // instance - NEW start() RUNNABLE, run()RUNNING, yield()(RUNNABLE), join()/wait()WAITING, notify()/notifyAll() RUNNABLE
    // Issues DB Read/I/O Request BLOCKED - request completion RUNNABLE sleep() TIMED_WAITING - RUNNABLE
    // stop() after run() executes - TERMINATED

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<String>();
        //ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_YELLOW, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);

        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "I'm being printed for the callable");
                return "This is the callable result";
            }
        });

        try {
            System.out.println("future = " + future.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong");
        } catch (InterruptedException e) {
            System.out.println("Thread running the callable task was interrupted");
        }
        executorService.shutdown();

        //new Thread(producer).start();
        //new Thread(consumer1).start();
        //new Thread(consumer2).start();

        //=======================================================
/*        Message message = new Message();

        (new Thread(new Reader(message))).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }

        (new Thread(new Writer(message))).start();*/

        //========================================================

/*        CountDown countDown = new CountDown();

        CountDownThread t1 = new CountDownThread(countDown);
        t1.setName("Thread 1");
        CountDownThread t2 = new CountDownThread(countDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();*/
        //========================================================
    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num: nums) {
            try {
                System.out.println(color + "Adding... " + num);
                /*synchronized(buffer) {
                    buffer.add(num);
                }*/
                bufferLock.lock();
                //  buffer.put(num);
                try {
                    buffer.add(num);
                } finally {
                    bufferLock.unlock();
                }
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println("Adding EOF and exiting...");
        /*synchronized(buffer) {
            buffer.add("EOF");
        }*/
        bufferLock.lock();
        try {
            buffer.add("EOF");
        } finally {
            bufferLock.unlock();
        }

    }
}

class MyConsumer implements Runnable {
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() {
        while (true) {
            //synchronized (buffer) {
            bufferLock.lock();
            try {
                if (buffer.isEmpty()) {
                    //bufferLock.unlock();
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    //bufferLock.unlock();
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            } finally {
                bufferLock.unlock();
            }
            //}
        }
    }
}
class Message {
    private String message;
    private boolean empty = true;
    public synchronized String read() {
        while (empty) {
            try {
                System.out.println(" Calling Wait... from message.read");
                wait();

            } catch (InterruptedException e) {

            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void write(String message) {
        while (!empty) {
            try {
                System.out.println(" Calling Wait... from message.write");
                wait();
            } catch (InterruptedException e) {

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Reader implements Runnable {
    private Message message;
    public Reader(Message message) {
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage + " from Reader Thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}
class Writer implements Runnable {
    private Message message;
    public Writer(Message message) {
        this.message = message;
    }

    public void run() {
        String messages[] = {
                "This is line one",
                "This is line two",
                "This is line three",
                "This is line four"
        };

        Random random = new Random();

        for (int i=0; i<messages.length; i++) {
            System.out.println("Writing message to Message Class - " + messages[i]);
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
        message.write("Finished");
    }
}
class CountDown {
    private int i;
    public void doCountDown() {
        String color;
        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }


    }
}

class CountDownThread extends Thread {
    private CountDown threadCountDown;

    public CountDownThread(CountDown countDown) {
        threadCountDown = countDown;
    }

    public void run() {
        threadCountDown.doCountDown();
    }
}
class ThreadColor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
