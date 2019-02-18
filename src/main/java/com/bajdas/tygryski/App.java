package com.bajdas.tygryski;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class App {
    public static void main(String[] args) {
        TestClass testClass = new TestClass(new ConsolePrinter());

        System.out.println("===================");
        Method[] declaredMethods = testClass.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Run.class)) {
                try {
                    method.invoke(testClass);
                } catch (IllegalAccessException e) {
                    System.out.println("No access to the method: " + method.getName());
                } catch (InvocationTargetException e) {
                    System.out.println("Invocation error: " + method.getName());
                }
            }
        }
    }
}
