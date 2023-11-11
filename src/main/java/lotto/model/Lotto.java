package lotto.model;

import lotto.constant.StringConstant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.constant.NumberConstant.LOTTO_SIZE;
import static lotto.message.ErrorMessage.EXIST_DUPLICATE;
import static lotto.message.ErrorMessage.INVALID_SIZE;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoSize(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    // TODO: 추가 기능 구현


    public String joinNumbersWithDelimiter(StringConstant stringConstant) {
        return numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(stringConstant.value()));
    }

    public boolean containsBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public int countCommonNumberFromAnotherLotto(Lotto lotto) {
        return lotto.countCommonElementsFromAnotherNumbers(this.numbers);
    }

    public int countCommonElementsFromAnotherNumbers(List<Integer> compareNumbers) {
        return (int) this.numbers.stream()
                .filter(compareNumbers::contains)
                .count();
    }

    private void validateLottoSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE.value()) {
            throw new IllegalArgumentException(INVALID_SIZE.getMessage());
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.size() != LOTTO_SIZE.value()) {
            throw new IllegalArgumentException(EXIST_DUPLICATE.getMessage());
        }
    }

}
