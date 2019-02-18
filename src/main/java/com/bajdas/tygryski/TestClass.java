package com.bajdas.tygryski;


class TestClass {
    private final Printer printer;

    TestClass(Printer printer) {
        this.printer = printer;
    }

    @Run
    void annotatedMethod() {
        printer.print("First annotated method");
    }

    @Test2
    void simpleMethod() {
        printer.print("Not @Run annotated method.");
    }

    @Run
    void secondAnnotatedMethod() {
        printer.print("Second annotated method.");
    }
}
