package com.dcp.portone.leet;

@FunctionalInterface
public interface Operation<T> {
    T operate(T value1, T value2);
    //T calculate(T x, T y);
}