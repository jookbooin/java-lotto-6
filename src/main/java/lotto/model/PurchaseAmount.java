package lotto.model;

import static lotto.constant.NumberConstant.LOTTO_PRICE;
import static lotto.constant.NumberConstant.ZERO;
import static lotto.message.ErrorMessage.NOT_MULTIPLE_OF_THOUSAND;
import static lotto.message.ErrorMessage.NOT_POSITIVE;
import static lotto.validator.GlobalValidator.validateNumber;

public class PurchaseAmount {
    private final int purchaseAmount;

    public PurchaseAmount(String input) {
        validateNumber(input);
        int purchaseAmount = Integer.parseInt(input);
        validatePositive(purchaseAmount);
        validateMultipleOfThousand(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validatePositive(int convertedCost) {
        if (!isPositive(convertedCost)) {
            throw new IllegalArgumentException(NOT_POSITIVE.getMessage());
        }
    }

    public int calculateLottoCount() {
        return purchaseAmount / LOTTO_PRICE.value();
    }

    private void validateMultipleOfThousand(int convertedCost) {
        if (!isMultipleOfThousand(convertedCost)) {
            throw new IllegalArgumentException(NOT_MULTIPLE_OF_THOUSAND.getMessage());
        }
    }

    private boolean isPositive(int convertedCost) {
        return convertedCost > ZERO.value();
    }

    private boolean isMultipleOfThousand(int number) {
        return number % LOTTO_PRICE.value() == ZERO.value();
    }

}