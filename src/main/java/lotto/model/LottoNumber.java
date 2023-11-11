package lotto.model;

import static lotto.message.ErrorMessage.OUT_OF_RANGE;
import static lotto.utils.LottoUtils.convertStringToInteger;
import static lotto.validator.GlobalValidator.validateNumber;

public class LottoNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private final int number;

    public LottoNumber(String input) {
        validateNumber(input);
        int number = convertStringToInteger(input);
        validateInRage(number);
        this.number = number;
    }

    private void validateInRage(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_RANGE.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }

    public boolean checkEqual(int number){
        return this.number == number;
    }

}
