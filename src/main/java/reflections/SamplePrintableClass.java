package reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@MyAwesomeClassAdnotation(isThisPrintable = true)
public class SamplePrintableClass {

    class AnnotationProcessing {
        public void processAdnotation(Object myObject, String methodName, Object... params) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
            if (myObject.getClass().isAnnotationPresent(MyAwesomeClassAdnotation.class)) {
                MyAwesomeClassAdnotation annotation = myObject.getClass().getAnnotation(MyAwesomeClassAdnotation.class);
                if (annotation.isThisPrintable()) {
                    Method myMethod = myObject.getClass().getDeclaredMethod(methodName, String.class);
                    MyAwesomeMethodAdnotation ann = myMethod.getAnnotation(MyAwesomeMethodAdnotation.class);
                    for (int i = 0; i < ann.printMeNumbersOfTime(); i++) {
                        myMethod.invoke(myObject, params);
                    }
                }
            }
        }
    }

    @MyAwesomeMethodAdnotation(printMeNumbersOfTime = 5)
    public void maybePrintMe(String printable) {
        System.out.println("Text: " + printable);
    }

    public static void main(String... args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        SamplePrintableClass samplePrintableClass = new SamplePrintableClass();
        AnnotationProcessing annotationProcessing = samplePrintableClass.new AnnotationProcessing();
        System.out.println("Normal call:");
        samplePrintableClass.maybePrintMe("Print or not to print");
        System.out.println("Call with annotations:\n");
        annotationProcessing.processAdnotation(samplePrintableClass, "maybePrintMe", "Print or not to print with annotations");
    }


}
