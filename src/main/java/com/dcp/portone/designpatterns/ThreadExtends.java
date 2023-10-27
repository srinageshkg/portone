package com.dcp.portone.designpatterns;

public class ThreadExtends extends Thread {
    public void run(){
        System.out.println("Nagesh Extend Thread running..." + this.getName());
        if (Thread.currentThread().isDaemon()){
            System.out.println(" Daemon is running... ");
        } //else {
//            System.out.println(" User Thread work... " + Thread.currentThread().getName());
//        }
        for (int i=0; i< 3; i++) {
            try{
                System.out.println(0.2*3 == 0.6);
                System.out.println(0.2*2 == 0.4);
                System.out.println(Double.MIN_VALUE);
                System.out.println(Float.MIN_VALUE);
                System.out.println(10.0/0);
                System.out.println(Math.min(Double.MIN_VALUE, 0.0d));
                System.out.print("Thread " + this.getName() + " is Sleeping...");
                Thread.sleep(500);
            }catch (InterruptedException ie){
                System.out.println(ie);
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Tread :" + this.getName() + " ID " + this.getId() + " Count: " + i);
        }
    }

    //ThreadExtends te = new ThreadExtends();
    //te.start();

    public static void main(String[] args) throws InterruptedException {
        ThreadExtends t = new ThreadExtends();
        ThreadExtends t2 = new ThreadExtends();
        ThreadExtends t3 = new ThreadExtends();

        Runnable r = new ThreadExtends();

        //Thread tt = Thread.currentThread(r);
        Thread tt = new Thread(r, "adsfd");
        System.out.println("tt Thread.currentThread()= " + tt);
        tt.start();

        t.setName("T1");
        t2.setName("T2");
        t3.setName("T3");
        System.out.println("STATE : " + t.getState() + " of Thread " + t.getName());
        t.start();
        System.out.println("STATE : " + t.getState() + " of Thread  " + t.getName() + " id is: " + t.getId() + " Priority: " + t.getPriority());
        try {
            t.join(1500);
        }catch (InterruptedException ie){
            System.out.println(ie);
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        t2.start();
        System.out.println("STATE : " + t2.getState() + " of Thread  " + t2.getName() + " id is: " + t2.getId() + " Priority: " + t2.getPriority());

        t3.start();
        t3.setPriority(8);
        System.out.println("STATE : " + t3.getState() + " of Thread  " + t3.getName() + " id is: " + t3.getId() + " Priority: " + t3.getPriority());

        //Thread.sleep(1000);
        t2.interrupt();
        t2.join();
        System.out.println("STATE : " + t.getState() + " of Thread  " + t.getName() + " id is: " + t.getId() + " Priority: " + t.getPriority());

        ThreadSafeSingletonEnum.SINGLETON_INSTANCE.display();

        ThreadSafeSingletonClass threadSafeSingletonClass = ThreadSafeSingletonClass.getInstance();
        threadSafeSingletonClass.display();

        ThreadSafeSynchronizedSingletonClass threadSafeSynchronizedSingletonClass =
                ThreadSafeSynchronizedSingletonClass.getInstance();
        threadSafeSynchronizedSingletonClass.display();

        ThreadsafeDoubleCheckSingletonClass threadsafeDoubleCheckSingletonClass =
                ThreadsafeDoubleCheckSingletonClass.getInstance();
        threadsafeDoubleCheckSingletonClass.display();


    }
}
// Using Enums
enum ThreadSafeSingletonEnum {
    SINGLETON_INSTANCE;
    public void display() {
        System.out.println("Thread Safe Singleton Enum Display!!!");
    }
}

// Using Static Field Initialization
class ThreadSafeSingletonClass {
    private static ThreadSafeSingletonClass INSTANCE = null; //  new ThreadSafeSingletonClass();

    private ThreadSafeSingletonClass() {}

    public static ThreadSafeSingletonClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadSafeSingletonClass();
        }
        return INSTANCE;
    }
    public void display() {
        System.out.println("Thread Safe Singleton Class Display");
    }
}

class ThreadSafeSynchronizedSingletonClass {
    private static ThreadSafeSynchronizedSingletonClass instance = null; //  new ThreadSafeSingletonClass();

    private ThreadSafeSynchronizedSingletonClass() {}

    synchronized public static ThreadSafeSynchronizedSingletonClass getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSynchronizedSingletonClass();
        }
        return instance;
    }
    public void display() {
        System.out.println("Thread Safe Synchronized Singleton Class Display");
    }
}

class ThreadsafeDoubleCheckSingletonClass {
    private static ThreadsafeDoubleCheckSingletonClass instance = null;

    private ThreadsafeDoubleCheckSingletonClass() {}
    public static ThreadsafeDoubleCheckSingletonClass getInstance() {
        if (instance == null) {
            //synchonize block of code
            synchronized (ThreadsafeDoubleCheckSingletonClass.class) {
                if (instance == null) {
                    instance = new ThreadsafeDoubleCheckSingletonClass();
                }
            }
        }
        return instance;
    }

    public void display() {
        System.out.println("Thread Safe Double Check Synchronized Singleton Class Display");
    }
}