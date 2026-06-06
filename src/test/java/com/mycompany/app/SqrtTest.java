package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SqrtTest {

    private Sqrt sqrt4;
    private Sqrt sqrt9;
    private Sqrt sqrt2;

    @BeforeEach
    public void setUp() {
        sqrt4 = new Sqrt(4.0);
        sqrt9 = new Sqrt(9.0);
        sqrt2 = new Sqrt(2.0);
    }

    /// --- Тесты метода calc() ---

    @Test
    public void testCalcPerfectSquare4() {
        assertEquals(2.0, sqrt4.calc(), 1e-6,
                "Квадратный корень из 4 должен быть 2.0");
    }

    @Test
    public void testCalcPerfectSquare9() {
        assertEquals(3.0, sqrt9.calc(), 1e-6,
                "Квадратный корень из 9 должен быть 3.0");
    }

    @Test
    public void testCalcPerfectSquare1() {
        Sqrt sqrt1 = new Sqrt(1.0);
        assertEquals(1.0, sqrt1.calc(), 1e-6,
                "Квадратный корень из 1 должен быть 1.0");
    }

    @Test
    public void testCalcIrrational() {
        assertEquals(Math.sqrt(2.0), sqrt2.calc(), 1e-6,
                "Квадратный корень из 2 должен совпадать с Math.sqrt(2)");
    }

    @Test
    public void testCalcLargeNumber() {
        Sqrt sqrtLarge = new Sqrt(1000000.0);
        assertEquals(1000.0, sqrtLarge.calc(), 1e-4,
                "Квадратный корень из 1000000 должен быть 1000.0");
    }

    @Test
    public void testCalcSmallNumber() {
        Sqrt sqrtSmall = new Sqrt(0.25);
        assertEquals(0.5, sqrtSmall.calc(), 1e-6,
                "Квадратный корень из 0.25 должен быть 0.5");
    }

    /// --- Тесты метода average() ---

    @Test
    public void testAverageOfTwoNumbers() {
        assertEquals(3.0, sqrt4.average(2.0, 4.0), 1e-9,
                "Среднее 2.0 и 4.0 должно быть 3.0");
    }

    @Test
    public void testAverageSymmetry() {
        assertEquals(sqrt4.average(1.0, 5.0), sqrt4.average(5.0, 1.0), 1e-9,
                "average(a,b) должно быть равно average(b,a)");
    }

    @Test
    public void testAverageEqualNumbers() {
        assertEquals(7.0, sqrt4.average(7.0, 7.0), 1e-9,
                "Среднее одинаковых чисел должно быть равно им самим");
    }

    /// --- Тесты метода good() ---

    @Test
    public void testGoodWhenGuessIsExact() {
        assertTrue(sqrt4.good(2.0, 4.0),
                "good(2.0, 4.0) должен вернуть true");
    }

    @Test
    public void testGoodWhenGuessFar() {
        assertFalse(sqrt4.good(10.0, 4.0),
                "good(10.0, 4.0) должен вернуть false");
    }

    @Test
    public void testGoodBoundaryNearDelta() {
        assertTrue(sqrt9.good(3.0, 9.0),
                "good(3.0, 9.0) должен вернуть true");
    }

    /// --- Тесты метода improve() ---

    @Test
    public void testImproveConvergesTowardsRoot() {
        double improved = sqrt4.improve(1.0, 4.0);
        assertEquals(2.5, improved, 1e-9,
                "improve(1.0, 4.0) должен вернуть 2.5");
    }

    @Test
    public void testImproveOnExactRoot() {
        double improved = sqrt4.improve(2.0, 4.0);
        assertEquals(2.0, improved, 1e-9,
                "improve(2.0, 4.0) должен вернуть 2.0 (уже точный корень)");
    }

    /// --- Тесты метода iter() ---

    @Test
    public void testIterFromGoodGuess() {
        double result = sqrt4.iter(2.0, 4.0);
        assertEquals(2.0, result, 1e-6,
                "iter(2.0, 4.0) должен вернуть 2.0");
    }

    @Test
    public void testIterFromBadGuess() {
        double result = sqrt9.iter(1.0, 9.0);
        assertEquals(3.0, result, 1e-6,
                "iter(1.0, 9.0) должен сойтись к 3.0");
    }
}