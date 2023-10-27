package com.dcp.portone.designpatterns;

import org.aspectj.lang.annotation.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AFDemoTestPer {
    @InjectMocks
    AFDemoTestPer manager;
    @Mock
    AFShape dao;
    @Before("")
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void demo(){
        AFAbstractFactory afAbstractFactory = AFFactoryProducer.getFactory(false);

        AFShape afShape1 = afAbstractFactory.getShape("RECTANGLE");
        afShape1.draw();
        AFShape afShape2 = afAbstractFactory.getShape("SQUARE");
        afShape2.draw();

        AFAbstractFactory afrAbstractFactory = AFFactoryProducer.getFactory(true);

        AFShape afShape3 = afrAbstractFactory.getShape("ROUNDEDRECTANGLE");
        afShape3.draw();
        AFShape afShape4 = afrAbstractFactory.getShape("ROUNDEDSQUARE");
        afShape4.draw();
    }
}
