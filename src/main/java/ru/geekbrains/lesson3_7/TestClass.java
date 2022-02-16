package ru.geekbrains.lesson3_7;

public class TestClass {
    @Test(value = 9)
    private void method1(){
        System.out.println("Метод с приоритетом 9");
    }
    @Test(value = 7)
    private void method2(){
        System.out.println("Метод с приоритетом 7");
    }
    @Test
    private void method3(){
        System.out.println("Метод с дефолтным приоритетом");
    }
    @Test(value = 3)
    private void method4(){
        System.out.println("Метод с приоритетом 3");
    }@Test(value = 1)
    private void method5(){
        System.out.println("Метод с приоритетом 1");
    }
    @BeforeSuite
    private void init(){
        System.out.println("Надеюсь, это заработает)))");
    }

    @AfterSuite
    private void destroy1(){
        System.out.println("Просто куча методов,");
    }
    @AfterSuite
    private void destroy2(){
        System.out.println("которые не должны");
    }
    @AfterSuite
    private void destroy3(){
        System.out.println("срабатывать при тесте ");
    }

    private void method6(){
        System.out.println("Этот метод без аннотаций в тесте я бы видеть не хотела");
    }

}
