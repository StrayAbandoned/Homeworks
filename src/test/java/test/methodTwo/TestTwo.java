package test.methodTwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.lesson3_6.ArrayMethods;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestTwo {
    ArrayMethods arrayMethods;

    @BeforeEach
    public void init() {
        arrayMethods = new ArrayMethods();
    }


    public static Stream<Arguments> dataTwo() {
        List<Arguments> listOne = new ArrayList<>();
        listOne.add(Arguments.arguments(true, new int[]{1, 1, 1, 4, 4, 1, 4, 1, 4}));
        listOne.add(Arguments.arguments(false, new int[]{14, 11, 44, 1}));
        listOne.add(Arguments.arguments(false, new int[]{1, 1, 1}));
        listOne.add(Arguments.arguments(false, new int[]{4, 4, 4, 4}));
        listOne.add(Arguments.arguments(false, new int[]{1, 2, 4, 4}));
        return listOne.stream();

    }

    @ParameterizedTest
    @MethodSource("dataTwo")
    public void paramTestTwo(boolean b, int[] arr) {
        Assertions.assertEquals(b, arrayMethods.methodTwo(arr));
    }
}

