import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.NegativeNumberException;

class StringCalculatorTest {

    StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void givenEmptyStringOfNumbers_WhenAdd_ThenReturnZero() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void givenOneNumber_WhenAdd_ThenReturnTheSum() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void givenTwoNumbers_WhenAdd_ThenReturnTheSum() {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void givenThreeNumbers_WhenAdd_ThenReturnTheSum() {
        assertEquals(6, stringCalculator.add("1,2,3"));
    }

    @Test
    public void givenNewLineInStringOfNumbers_WhenAdd_ThenReturnSum(){
        assertEquals(6, stringCalculator.add("1\n2,3"));
    }

    @Test
    public void givenNewDelimiterInStringOfNumbers_WhenAdd_ThenReturnSum(){
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void givenOneNegativeNumberInStringOfNumbers_WhenAdd_ThenThrowException() {
        try {
            stringCalculator.add("-1,2");
        } catch (NegativeNumberException e) {
            assertEquals(e.getMessage(), "Negatives not allowed: -1");
        }
    }

    @Test
    public void givenTwoNegativeNumbersInStringOfNumbers_WhenAdd_ThenThrowException() {
        try {
            stringCalculator.add("2,-4,3,-5");
        } catch (NegativeNumberException e){
            assertEquals(e.getMessage(), "Negatives not allowed: -4,-5");
        }
    }

    @Test
    public void givenNumberOverThousandInTheInputString_WhenAdd_ThenIgnore() {
        assertEquals(2, stringCalculator.add("1001,2"));
    }

}