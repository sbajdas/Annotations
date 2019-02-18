package com.bajdas.tygryski.program;


import com.bajdas.tygryski.adnotacje.Run;
import com.bajdas.tygryski.adnotacje.NotUsedAnnotation;

public class TestClass {
    private final Printer printer;

    public TestClass(Printer printer) {
        this.printer = printer;
    }

    @Run
    void annotatedMethod() {
        printer.print("First annotated method");
    }

    @NotUsedAnnotation
    void simpleMethod() {
        printer.print("Not @Run annotated method.");
    }

    @Run
    void secondAnnotatedMethod() {
        printer.print("Second annotated method.");
    }
}
