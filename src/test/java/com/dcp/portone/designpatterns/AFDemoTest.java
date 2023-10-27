package com.dcp.portone.designpatterns;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


// @Test(expected = RuntimeException.class)
// @TestSubject annotation is used to identify class which is going to use the mock object
// @Mock annotation is used to create the mock object to be injected
// @InjectMocks annotation is used to create and inject the mock object
// @RunWith attaches a runner with the test class to initialize the test data
// @RunWith(MockitoJUnitRunner.class) @RunWith(MockitoRunner.class)
@ExtendWith(MockitoExtension.class)
public class AFDemoTest {
    @Mock List mockedList = mock(List.class);
    @Mock List spy = spy(mockedList);
    @Mock private AFAbstractFactory afAbstractFactory;
    @Mock private AFAbstractFactory afrAbstractFactory;
    @Mock private AFShape afShape1, afShape2, afShape3, afShape4;

    List mockSecList = mock(List.class);
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void myTest(){
        doThrow(new RuntimeException()).when(mockedList).clear();
        when(spy.size()).thenReturn(100);
        mockedList.add("One");
        mockedList.add("Two");
        mockedList.add("Two");
        System.out.println(mockedList.get(0));
        //mockedList.clear();
        System.out.println(spy.size());
        mockSecList.add("Three");


        InOrder inOrder = inOrder(mockedList);
        InOrder inOrder2 = inOrder(mockedList, mockSecList);

        //Verofication
        inOrder.verify(mockedList).add("One");
        inOrder.verify(mockedList, times(2)).add("Two");

        when(mockedList.get(0)).thenReturn("Two");
        reset(mockedList);

        //mockedList.clear();
        //verify(mockedList).clear();
        System.out.println(mockedList.get(0));
    }

    @Test public void AFTest() {
        afAbstractFactory = AFFactoryProducer.getFactory(false);
        afShape1 = afAbstractFactory.getShape("RECTANGLE");
        AFShape afs1Spy = spy(afShape1);
        String string1 = "Inside AFRectangle::draw() method.";
        //when(afShape1.draw()).thenReturn(string1);
        //verify(afShape1).draw();
        System.out.println(afs1Spy.draw());
        assertEquals(string1, afs1Spy.draw());

        //@BeforeEach
        //public void setUp(){
        //MockitoAnnotations.openMocks(afAbstractFactory);
        /*        this.afAbstractFactory = AFFactoryProducer.getFactory(false);
        this.afrAbstractFactory = AFFactoryProducer.getFactory(true);
        this.afShape1 = afAbstractFactory.getShape("RECTANGLE");
        this.afShape2 = afAbstractFactory.getShape("SQUARE");
        this.afShape3 = afrAbstractFactory.getShape("ROUNDEDRECTANGLE");
        this.afShape4 = afrAbstractFactory.getShape("ROUNDEDSQUARE");*/
    }

}

/*    @Test
    public void AFDemoFirst() throws Exception {
        this.afAbstractFactory = AFFactoryProducer.getFactory(false);
        this.afrAbstractFactory = AFFactoryProducer.getFactory(true);
        this.afShape1 = afAbstractFactory.getShape("RECTANGLE");
        this.afShape2 = afAbstractFactory.getShape("SQUARE");
        this.afShape3 = afrAbstractFactory.getShape("ROUNDEDRECTANGLE");
        this.afShape4 = afrAbstractFactory.getShape("ROUNDEDSQUARE");
        Mockito.when(afShape1.draw()).thenReturn("Inside AFRectangle::draw() method.");
        //assertEquals("Inside AFRectangle::draw() method.",afShape1.draw());
    }*/

//}