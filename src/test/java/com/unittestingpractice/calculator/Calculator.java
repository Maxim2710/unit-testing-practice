package com.unittestingpractice.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

}
