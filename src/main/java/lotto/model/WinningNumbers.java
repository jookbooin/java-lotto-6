package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.constant.StringConstant.COMMA;
import static lotto.utils.LottoUtils.splitStringToList;

public class WinningNumbers {

    private final List<LottoNumber> numbers;

    public WinningNumbers(String input) {
        this.numbers = getLottoNumbers(input);
    }

    private List<LottoNumber> getLottoNumbers(String input) {
        // 1. String 분리
        List<String> strings = splitStringToList(COMMA, input);

        // 2. Lotto
        return  convertToLottoNumbers(strings);
    }

    private List<LottoNumber> convertToLottoNumbers(List<String> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<LottoNumber> getNumbers() {
        return new ArrayList<>(numbers);
    }
}
