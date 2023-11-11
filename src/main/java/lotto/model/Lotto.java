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
        validate(numbers);
        this.numbers = numbers;
    }

    /** 검증  */
    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private void validateSize(List<Integer> numbers) {
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


    public String joinNumbersWithDelimiter(StringConstant stringConstant) {
        return numbers.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(stringConstant.value()));
    }

    /** 포함 검증 */
    public boolean containsBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public boolean containsLottoNumber(LottoNumber lottoNumber){

        for (Integer number : numbers) {
            if(lottoNumber.checkEqual(number)){
                return true;
            }
        }
        return false;
    }

    public int countCommonNumberFromAnotherLotto(Lotto lotto) {
        return lotto.countCommonElementsFromAnotherNumbers(this.numbers);
    }

    public int countCommonElementsFromAnotherNumbers(List<Integer> compareNumbers) {
        return (int) this.numbers.stream()
                .filter(compareNumbers::contains)
                .count();
    }

    /**
     * winningLotto 와 BonusNumber 비교용
     * */
    public void containsBonusLottoNumber(LottoNumber bonusLottoNumber) {

    }
}
