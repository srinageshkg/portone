package com.dcp.portone.corejava.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringUtilitiesTest {

    private StringUtilities su;
    private StringBuilder sb;
    private char[] str = {'h', 'e', 'l', 'l', 'o'};
    private String myStr = "hello";

    @BeforeEach
    void setUp() {
        su = new StringUtilities();
        sb = new StringBuilder();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addChar() {
        su.addChar(sb, 'a');
        assertEquals("a", sb.toString());
    }

    @Test
    void everyNthChar() {
        char[] output = su.everyNthChar(new char[] {'h', 'e', 'l', 'l', 'o'}, 2);
        assertArrayEquals(new char[] {'e', 'l'}, output);
        assertArrayEquals("el".toCharArray(), su.everyNthChar(myStr.toCharArray(), 2));
        assertArrayEquals("hello".toCharArray(), su.everyNthChar(myStr.toCharArray(), 6));
        assertArrayEquals("o".toCharArray(), su.everyNthChar(myStr.toCharArray(), 5));
    }

    @Test
    void removePairs() {
        assertEquals("ABCDEF", su.removePairs("AABBCDEFF"));
        assertEquals("ABABCDEF", su.removePairs("ABBABCCDEEFF"));
        assertNull(su.removePairs(null));
        assertEquals("A", su.removePairs("A"));
        assertEquals("", su.removePairs(""));
    }

    @Test
    void converter() {
        try {
            assertEquals(300, su.converter(10, 5));
        } catch (ArithmeticException e) {
            System.out.println("Divisible by zero");
        }
    }

    @Test
    void converter_byZero() {
        try {
            assertEquals(300, su.converter(10, 0));
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException Divide by zero");
        }
    }

    @Test
    void nullIfOddLength() {
        assertEquals("AB", su.nullIfOddLength("AB"));
        assertNull(su.nullIfOddLength("odd"));
    }
}