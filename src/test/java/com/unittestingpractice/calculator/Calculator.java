package com.unittestingpractice.calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @BeforeEach
    void setUp() {
        System.out.println("Перед выполнением теста");
    }

    @AfterEach
    void tearDown() {
        System.out.println("После выполнения теста");
    }

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

    // divide
    @Test
    @DisplayName("Деление положительных чисел")
    void testDividePositiveNumbers() {
        int result = calculator.divide(10, 2);
        assertEquals(5, result, "10 / 2 должно быть равно 5");
    }

    @Test
    @DisplayName("Деление отрицательных чисел")
    void testDivideNegativeNumbers() {
        int result = calculator.divide(-10, -2);
        assertEquals(5, result, "-10 / -2 должно быть равно 5");
    }

    @Test
    @DisplayName("Деление положительного числа на отрицательное")
    void testDividePositiveByNegative() {
        int result = calculator.divide(10, -2);
        assertEquals(-5, result, "10 / -2 должно быть равно -5");
    }

    @Test
    @DisplayName("Деление на единицу")
    void testDivideByOne() {
        int result = calculator.divide(10, 1);
        assertEquals(10, result, "10 / 1 должно быть равно 10");
    }

    @Test
    @DisplayName("Исключение при делении на ноль")
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(10, 0),
                "Ожидалось исключение IllegalArgumentException при делении на ноль");
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @RepeatedTest(5)
    @DisplayName("Повторное деление для проверки стабильности")
    void testRepeatedDivide() {
        int result = calculator.divide(20, 4);
        assertEquals(5, result, "20 / 4 должно быть равно 5");
    }

    @Test
    @Tag("performance")
    @Timeout(1)
    @DisplayName("Тест производительности деления")
    void testDividePerformance() {
        assertTimeoutPreemptively(Duration.ofMillis(500), () -> {
            int result = calculator.divide(1000000, 1);
            assertEquals(1000000, result);
        }, "Тест должен выполняться менее чем за 500 мс");
    }

    @Test
    @DisplayName("Групповая проверка деления с assertAll")
    void testDivideWithAssertionsGroup() {
        assertAll("Деление разных комбинаций чисел",
                () -> assertEquals(5, calculator.divide(10, 2), "10 / 2 должно быть равно 5"),
                () -> assertEquals(-5, calculator.divide(-10, 2), "-10 / 2 должно быть равно -5"),
                () -> assertEquals(1, calculator.divide(5, 5), "5 / 5 должно быть равно 1")
        );
    }

    // isEven
    @Test
    @DisplayName("Тест для четного числа")
    void testEvenNumber() {
        assertTrue(calculator.isEven(2), "2 должна быть четной");
    }

    @Test
    @DisplayName("Тест для нечетного числа")
    void testOddNumber() {
        assertFalse(calculator.isEven(3), "3 должна быть нечетной");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    void testEvenNumbers(int number) {
        assertTrue(calculator.isEven(number), number + " должны быть четными");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9})
    void testOddNumbers(int number) {
        assertFalse(calculator.isEven(number), number + " должны быть нечетными");
    }
}
