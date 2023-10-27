package com.dcp.portone.corejava.file;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(Parameterized.class)
public class StringUtilitiesTestOld {
    // assertEquals()  uses equals method
    // assertTrue()
    // assertFalse()
    // assertNotTrue()
    // assertNull()
    // assertNotNull()
    // assertArrayEquals() compares length of arrays, and every element same in the same order
    // assertSame() assertNotSame() - exact same instances  compares object references only
    // assertThat Matcher of JUnit - compares actual value against a range of values
    //@Test (expected = IllegalArgumentException.class)

    private StringUtilities su;
    private StringBuilder sb;
    private static int count = 0;

    @org.junit.jupiter.api.BeforeAll
    public static void beforeClass() {
        System.out.println("@BeforeAll This exes before any test case. Count = " + count++);
    }

    @BeforeEach
    public void setup() {
        su = new StringUtilities();
        sb = new StringBuilder();
        System.out.println("@BeforeEach Running a test... Count = " + count++);
    }

    @Parameterized.Parameters
    public static Collection<Object []> testConditions() {
        return Arrays.asList( new Object[][] {
                {'a', true, "a"},
                {'b', true, "b"},
                {'c', true, "c"},
                {'d', true, "d"},
                {'e', true, "e"}
                });
    }

    @Test
    public void addChar() {
        su.addChar(sb, 'a');
        assertEquals("a", sb.toString());
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass This exes after all test cases. Count = " + count++);
    }

    @After
    public void teardown() {
        System.out.println("@After teardown Count = " + count++);
    }

    @AfterAll
    public static void teardownAll() {
        System.out.println("@AfterAll teardownAll Count = " + count++);
    }
}