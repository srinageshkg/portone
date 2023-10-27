package com.dcp.portone.practice;

public class ThreadTest {
    public void createThread() {
    new Thread(new Runnable(){
        @Override
        public void run(){
            System.out.println("New Thread Created!");
        }
    }).start();
    }
}
