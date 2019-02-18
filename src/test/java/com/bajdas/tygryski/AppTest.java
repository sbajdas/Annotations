package com.bajdas.tygryski;

import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.testng.Assert.assertEquals;

@Test
public class AppTest {

    private TestClass testClass;

    public void classHasThreeMethods() {
        // given
        // when
        Method[] declaredMethods = testClass.getClass().getDeclaredMethods();

        // then
        assertEquals(declaredMethods.length, 3, "Not enough methods found in class");
    }

    public void classHasTwoRunAnnotatedMethods() {
        // given
        int counter;
        // when
        counter = countRunAnnotatedMethods(testClass.getClass());
        // then
        assertEquals(counter, 2);
    }

    private int countRunAnnotatedMethods(Class testClass) {
        int counter = 0;
        Method[] declaredMethods = testClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Run.class)) {
                counter++;
            }
        }
        return counter;
    }

    public void executeOnlyAnnotatedMethods() {
        // given
        FakePrinter mockPrinter = Mockito.mock(FakePrinter.class);
        TestClass testClassSpied = new TestClass(mockPrinter);
        int numberOfAnnotatedMethods = countRunAnnotatedMethods(testClassSpied.getClass());
        Method[] declaredMethods = testClassSpied.getClass().getDeclaredMethods();
        // when
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Run.class)) {
                try {
                    method.invoke(testClassSpied);
                } catch (IllegalAccessException e) {
                    System.err.println("No access to the method: " + method.getName());
                } catch (InvocationTargetException e) {
                    System.err.println("Invocation error: " + method.getName());
                }
            }
        }
        //then
        Mockito.verify(mockPrinter, times(numberOfAnnotatedMethods)).print(anyString());
    }

    @BeforeMethod
    public void setUp() {
        FakePrinter printer = new FakePrinter();
        testClass = new TestClass(printer);
    }
}
