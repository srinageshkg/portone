package com.dcp.portone.polymor;

class TestException extends Exception {
    public TestException() {
        super();
    }

    public TestException(String s) {
        super(s);
    }
}

public class Test {
    public void m1() throws Exception {
        throw new TestException();
    }
}