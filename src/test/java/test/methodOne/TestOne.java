package test.methodOne;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.lesson3_6.ArrayMethods;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestOne {
    ArrayMethods arrayMethods;

    @BeforeEach
    public void init() {
        arrayMethods = new ArrayMethods();
    }

    public static Stream<Arguments> dataOne() {
        List<Arguments> listOne = new ArrayList<>();
        listOne.add(Arguments.arguments(new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
        listOne.add(Arguments.arguments(new int[]{1, 7, 5, 3, 2}, new int[]{1, 2, 4, 1, 7, 5, 3, 2}));
        listOne.add(Arguments.arguments(new int[]{}, new int[]{1, 2, 4}));
        listOne.add(Arguments.arguments(new int[]{}, new int[]{1, 2, 4, 4}));
        return listOne.stream();

    }

    @ParameterizedTest
    @MethodSource("dataOne")
    public void paramTestOne(int[] result, int[] arr) {
        Assertions.assertArrayEquals(result, arrayMethods.methodOne(arr));
    }

    @org.junit.jupiter.api.Test
    public void testOneException(){
        Assertions.assertThrows(RuntimeException.class, ()->arrayMethods.methodOne(new int[]{1, 2, 5, 6}));
    }
}
