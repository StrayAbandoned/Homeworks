package ru.geekbrains.lesson3_7;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void start(Class testClass) throws Exception{
        Constructor constructor = testClass.getConstructor();
        Object t = constructor.newInstance();

        Method[] m1 = testClass.getDeclaredMethods();
        List<Method> listBefore = new ArrayList<>();
        List<Method> listAfter = new ArrayList<>();
        List<Method> listMain = new ArrayList<>();


        for (Method m : m1) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                listBefore.add(m);
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                listAfter.add(m);
            }
            if (m.isAnnotationPresent(Test.class)) {
                listMain.add(m);
            }
        }

        if (listBefore.size() == 1) {
            listBefore.get(0).setAccessible(true);
            listBefore.get(0).invoke(t);
        } else if (listBefore.size() > 1){
            throw new RuntimeException();
        }

        for (int i = 1; i < 11; i++) {
            for (Method m : listMain) {
                if (m.getAnnotation(Test.class).value() == i) {
                    m.setAccessible(true);
                    m.invoke(t);
                }

            }
        }

        if (listAfter.size() == 1) {
            listAfter.get(0).setAccessible(true);
            listAfter.get(0).invoke(t);
        } else if (listAfter.size() > 1){
            throw new RuntimeException();
        }
    }


}