import static java.lang.Integer.parseInt;

import java.util.Objects;

import exception.NegativeNumberException;

public class StringCalculator {

    public int add(String numbers){
        if (Objects.equals(numbers, "")) {
            return 0;
        }
        String[] numList = splitNumbersBasedOnDelimiters(numbers);
        return sum(numList);
    }

    private String[] splitNumbersBasedOnDelimiters(String numbers) {
        String delimiter = ",";
        if(numbers.matches("//(.*)\n(.*)")) {
            delimiter = Character.toString(numbers.charAt(2));
            numbers = numbers.substring(4);
        }
        return numbers.split(delimiter + "|\n");
    }

    private int sum(String[] numbers) {
        int total = 0;
        total = checkForNegativeNumbersAndThrowException(numbers, total);
        return total;
    }

    private int checkForNegativeNumbersAndThrowException(String[] numbers, int total) {
        StringBuilder negativeString = new StringBuilder();

        for(String number : numbers) {
            if(parseInt(number) < 0){
                if(negativeString.toString().equals(""))
                    negativeString = new StringBuilder(number);
                else
                    negativeString.append(",").append(number);
            }
            total = includeOnlyNumbersUntilOneThousand(total, number);
        }

        if(!negativeString.toString().equals("")) {
            throw new NegativeNumberException("Negatives not allowed: " + negativeString);
        }
        return total;
    }

    private int includeOnlyNumbersUntilOneThousand(int total, String number) {
        if(parseInt(number) < 1000) {
            total += parseInt(number);
        }
        return total;
    }

}
