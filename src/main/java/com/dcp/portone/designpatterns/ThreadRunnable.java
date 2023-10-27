package com.dcp.portone.designpatterns;


class ThreadRunnable implements Runnable {
    public void run(){
        System.out.println("RUN: Nagesh Runnable Thread runs...");
    }
    //Thread rt = new Thread(new ThreadRunnable());
    //rt.start();
    // Life cycle
    // New, Active (Runnable/Running), Blocked/Waiting, Timed Waiting, and Terminated

    public static void main(String[] args) throws InterruptedException {
        //ThreadRunnable tr = new ThreadRunnable();
        Thread t = new Thread(new ThreadRunnable(), "Invoking through a Thread constructor...");
        System.out.println("STATE : " + t.getState() + " and " + t.getName());
        t.start();
        System.out.println("STATE : " + t.getState() + " and " + t.getName() + " id is: " + t.getId() + " Priority: " + t.getPriority());
        t.sleep(1000);
        System.out.println("STATE : " + t.getState() + " and " + t.getName() + " id is: " + t.getId() + " Priority: " + t.getPriority());
        t.join(1000);
        System.out.println("STATE : " + t.getState() + " and " + t.getName() + " id is: " + t.getId() + " Priority: " + t.getPriority());

    }
}
