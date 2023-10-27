package com.dcp.portone.designpatterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class SingletonTest {
    @InjectMocks Singleton singleton;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBalanceTest() {
        assertEquals(1000, singleton.getBalance());
    }

    @Test
    void getMoneyTest()  {
        //fail("Yet to Test");
        assertEquals(600, singleton.getMoney(400));
        assertEquals(600, singleton.getBalance());
        assertEquals(800, singleton.addMoney(200));
    }

    @Test
    void addMoneyTest() {
        //fail("Yet to Test");
        assertEquals( 1200, singleton.addMoney(200));
    }
}
