package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.IntStream;

import static lotto.constant.NumberConstant.*;

public class LottoGenerator {

    public List<Lotto> generatePurchaseLotto(PurchaseAmount purchaseAmount) {
        int lottoCount = purchaseAmount.calculateLottoCount();
        return generateLottoByCount(lottoCount);
    }

    public List<Lotto> generateLottoByCount(int lottoCount) {
        return IntStream.range(0, lottoCount)
                .mapToObj(i -> createLotto())
                .toList();
    }

    public Lotto createLotto() {
        List<Integer> numbers = generateNumberInRange();
        return new Lotto(numbers);
    }

    private  List<Integer> generateNumberInRange() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_START_INCLUSIVE.value(), LOTTO_NUMBER_END_INCLUSIVE.value(), LOTTO_SIZE.value());
    }

}
