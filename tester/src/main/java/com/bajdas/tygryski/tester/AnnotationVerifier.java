package com.bajdas.tygryski.tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class AnnotationVerifier {
    void runMethodsWithAnnotation(Object testObject, Class<? extends java.lang.annotation.Annotation> annotationType) {
        Method[] declaredMethods = testObject.getClass().getDeclaredMethods();
        Arrays.stream(declaredMethods).filter(method-> method.isAnnotationPresent(annotationType))
            .forEach(method -> invokeAnnotatedMethod(testObject,method));
    }

    private void invokeAnnotatedMethod(Object testObject, Method method) {
        try {
            method.setAccessible(true);
            method.invoke(testObject);
        } catch (IllegalAccessException e) {
            System.err.println("No access to the method: " + method.getName());
        } catch (InvocationTargetException e) {
            System.err.println("Invocation error: " + method.getName());
        }
    }
}
