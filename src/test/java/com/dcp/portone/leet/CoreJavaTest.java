package com.dcp.portone.leet;

import com.dcp.portone.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class CoreJavaTest {

    @InjectMocks
    private CoreJava coreJava;

    @Mock
    private StudentService studentService;

    @Test
    @Description("should be able to transform")
    void transFormAndAddTest(){
        List<Integer> li = Arrays.asList(1,2,3);
        Function<Integer, Integer> ops = (Integer a)->{
            return a*2;
        };

        int result = coreJava.transformAndAdd(li,ops);
        assertEquals(12, result);
    }
}