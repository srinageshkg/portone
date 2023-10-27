package com.dcp.portone.service;

public interface MyInterface {
    void abstract_func (int x, int y);

    default void default_func() {
        System.out.println("This is a default function");
    }
}
