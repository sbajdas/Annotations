package com.bajdas.tygryski.tester;

import com.bajdas.tygryski.adnotacje.Run;
import com.bajdas.tygryski.program.ConsolePrinter;
import com.bajdas.tygryski.program.TestClass;

class App {
    public static void main(String[] args) {
        TestClass testObject = new TestClass(new ConsolePrinter());

        AnnotationVerifier verifier = new AnnotationVerifier();
        verifier.runMethodsWithAnnotation(testObject, Run.class);
    }
}
