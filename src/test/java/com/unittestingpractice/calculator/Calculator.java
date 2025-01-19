package com.unittestingpractice.calculator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    // add
    @Test
    @DisplayName("Сложение двух положительных чисел")
    void testAddPositiveNumbers() {
        int result = calculator.add(10, 20);
        assertEquals(30, result, "Сложение двух положительных чисел должно быть корректным");
    }

    @Test
    @DisplayName("Сложение двух отрицательных чисел")
    void testAddNegativeNumbers() {
        int result = calculator.add(-10, -20);
        assertEquals(-30, result, "Сложение двух отрицательных чисел должно быть корректным");
    }

    @Test
    @DisplayName("Сложение положительного и отрицательного числа")
    void testAddPositiveAndNegativeNumber() {
        int result = calculator.add(10, -5);
        assertEquals(5, result, "Сложение положительного и отрицательного числа должно быть корректным");
    }

    @Test
    @DisplayName("Сложение числа и нуля")
    void testAddWithZero() {
        int result = calculator.add(10, 0);
        assertEquals(10, result, "Сложение числа и нуля должно возвращать то же число");
    }

    @Test
    @DisplayName("Сложение нуля и числа")
    void testAddZeroAndNumber() {
        int result = calculator.add(0, 10);
        assertEquals(10, result, "Сложение нуля и числа должно возвращать то же число");
    }

    @Test
    @DisplayName("Сложение с максимальным значением Integer")
    void testAddWithIntegerMaxValue() {
        int result = calculator.add(Integer.MAX_VALUE, 0);
        assertEquals(Integer.MAX_VALUE, result, "Сложение с Integer.MAX_VALUE и 0 должно возвращать Integer.MAX_VALUE");
    }

    @Test
    @DisplayName("Сложение с минимальным значением Integer")
    void testAddWithIntegerMinValue() {
        int result = calculator.add(Integer.MIN_VALUE, 0);
        assertEquals(Integer.MIN_VALUE, result, "Сложение с Integer.MIN_VALUE и 0 должно возвращать Integer.MIN_VALUE");
    }

    @Test
    @DisplayName("Сложение с переполнением (превышение максимального значения Integer)")
    void testAddOverflow() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> calculator.add(Integer.MAX_VALUE, 1),
                "Ожидалось выбросить ArithmeticException при переполнении");
        assertEquals("integer overflow", exception.getMessage());
    }

    @Test
    @DisplayName("Сложение с переполнением (превышение минимального значения Integer)")
    void testAddUnderflow() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> calculator.add(Integer.MIN_VALUE, -1),
                "Ожидалось выбросить ArithmeticException при переполнении");
        assertEquals("integer overflow", exception.getMessage());
    }

    // subtract
    @Test
    @DisplayName("Вычитание положительных чисел")
    void testSubtractPositiveNumbers() {
        int result = calculator.subtract(10, 5);
        assertEquals(5, result, "10 - 5 должно быть равно 5");
    }

    @Test
    @DisplayName("Вычитание отрицательных чисел")
    void testSubtractNegativeNumbers() {
        int result = calculator.subtract(-10, -5);
        assertEquals(-5, result, "-10 - (-5) должно быть равно -5");
    }

    @Test
    @DisplayName("Вычитание числа из самого себя")
    void testSubtractNumberFromItself() {
        int result = calculator.subtract(7, 7);
        assertEquals(0, result, "7 - 7 должно быть равно 0");
    }

    @Test
    @DisplayName("Вычитание с результатом в отрицательной области")
    void testSubtractResultingInNegative() {
        int result = calculator.subtract(5, 10);
        assertEquals(-5, result, "5 - 10 должно быть равно -5");
    }

    @Test
    @DisplayName("Граничный случай: переполнение при вычитании (Integer.MAX_VALUE - (-1))")
    void testSubtractOverflow() {
        assertThrows(ArithmeticException.class,
                () -> calculator.subtract(Integer.MAX_VALUE, -1),
                "Ожидалось ArithmeticException при переполнении");
    }

    @Test
    @DisplayName("Граничный случай: переполнение при вычитании (Integer.MIN_VALUE - 1)")
    void testSubtractUnderflow() {
        assertThrows(ArithmeticException.class,
                () -> calculator.subtract(Integer.MIN_VALUE, 1),
                "Ожидалось ArithmeticException при переполнении");
    }

    @Nested
    @DisplayName("Тесты умножения обычных чисел")
    class BasicMultiplicationTests {

        @Test
        @DisplayName("Умножение положительных чисел")
        void testMultiplyPositiveNumbers() {
            int result = calculator.multiply(5, 4);
            assertEquals(20, result, "5 * 4 должно быть равно 20");
        }

        @Test
        @DisplayName("Умножение отрицательных чисел")
        void testMultiplyNegativeNumbers() {
            int result = calculator.multiply(-5, -4);
            assertEquals(20, result, "-5 * -4 должно быть равно 20");
        }

        @Test
        @DisplayName("Умножение положительного числа на отрицательное")
        void testMultiplyPositiveByNegative() {
            int result = calculator.multiply(5, -4);
            assertEquals(-20, result, "5 * -4 должно быть равно -20");
        }

        @Test
        @DisplayName("Умножение на ноль")
        void testMultiplyByZero() {
            int result = calculator.multiply(5, 0);
            assertEquals(0, result, "5 * 0 должно быть равно 0");
        }

        @Test
        @DisplayName("Умножение нуля на любое число")
        void testZeroTimesAnyNumber() {
            int result = calculator.multiply(0, 5);
            assertEquals(0, result, "0 * 5 должно быть равно 0");
        }
    }

    @Nested
    @DisplayName("Граничные случаи для умножения")
    class BoundaryMultiplicationTests {
        @Test
        @DisplayName("Переполнение при умножении (Integer.MAX_VALUE * 2)")
        void testMultiplyOverflow() {
            assertThrows(ArithmeticException.class,
                    () -> calculator.multiply(Integer.MAX_VALUE, 2),
                    "Ожидалось ArithmeticException при переполнении");
        }

        @Test
        @DisplayName("Переполнение при умножении (Integer.MIN_VALUE * -1)")
        void testMultiplyUnderflow() {
            assertThrows(ArithmeticException.class,
                    () -> calculator.multiply(Integer.MIN_VALUE, -1),
                    "Ожидалось ArithmeticException при переполнении");
        }
    }

    @Nested
    @DisplayName("Параметризованные тесты для умножения")
    class ParameterizedMultiplicationTests {

        @TestFactory
        @DisplayName("Динамические тесты для умножения с набором входных данных")
        Stream<DynamicTest> dynamicTestsForMultiply() {
            return Stream.of(
                    new TestCase(2, 3, 6),
                    new TestCase(-2, 3, -6),
                    new TestCase(0, 5, 0),
                    new TestCase(-5, -5, 25)
            ).map(tc -> DynamicTest.dynamicTest(
                    String.format("Test: %d * %d = %d", tc.a, tc.b, tc.expected),
                    () -> assertEquals(tc.expected, calculator.multiply(tc.a, tc.b))
            ));
        }
    }

    @Test
    @DisplayName("Условное выполнение теста (assumeTrue)")
    void testConditionalExecution() {
        int a = 5;
        int b = 10;
        assumeTrue(a > 0 && b > 0, "Пропустить тест, если числа не положительные");
        int result = calculator.multiply(a, b);
        assertEquals(50, result, "5 * 10 должно быть равно 50");
    }

    private static class TestCase {
        final int a;
        final int b;
        final int expected;

        TestCase(int a, int b, int expected) {
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }
}
