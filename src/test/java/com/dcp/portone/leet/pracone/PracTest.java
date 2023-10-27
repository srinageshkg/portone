package com.dcp.portone.leet.pracone;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class PracTest {

    @InjectMocks
    Prac prac;

//    @Test
//    public int maxProfit(int[] arr) throws Exception {
//        int[] prices = {7,1,5,3,9,4};
//        int val = maxProfit(prices);
//        assertEquals(5, val);
//        return val;
//    }

    @Test
    public void pracTest(){
        prac.validParen1("12");
    }


}