package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    private NumberWorker nw;

    @BeforeEach
    void init() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {113, 127, 131, 137, 139, 149, 151})
    void isPrimeForPrimes(int number) {
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {114, 128, 132, 138, 140, 150, 152})
    void isPrimeForNotPrimes(int number) {
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, -2 , -3, -4, 0, 1})
    void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalNumberException.class, ()-> { nw.isPrime(number); });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", delimiter = ',')
    void checkDigitsSum(int input, int expected) {
        Assertions.assertEquals(expected, nw.digitsSum(input));
    }
}
