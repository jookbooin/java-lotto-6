package lotto.validator;

import lotto.model.Lotto;
import lotto.model.LottoNumber;

import static lotto.constant.NumberConstant.LOTTO_NUMBER_END_INCLUSIVE;
import static lotto.constant.NumberConstant.LOTTO_NUMBER_START_INCLUSIVE;
import static lotto.message.ErrorMessage.*;

public class WinningLottoValidator {

    private static final char COMMA = ',';

    public static void validateCommaAtStartOrEnd(String input) {
        validateStartWithComma(input);
        validateEndWithComma(input);
    }

    private static void validateStartWithComma(String input) {
        if (input.charAt(0) == COMMA) {
            throw new IllegalArgumentException(NOT_START_WITH_COMMA.getMessage());
        }
    }

    private static void validateEndWithComma(String input) {
        if (input.charAt(input.length() - 1) == COMMA) {
            throw new IllegalArgumentException(NOT_END_WITH_COMMA.getMessage());
        }
    }

//    public static void validateBonusNumber(Lotto winningLottos, int bonusNumber) {
//        validateLottoNumberInRange(bonusNumber);
//        validateBonusNumberInWinningLotto(winningLottos, bonusNumber);
//    }

    public static void validateBonusNumberInWinningLotto(Lotto winningLottos, int bonusNumber) {
        if (winningLottos.containsBonusNumber(bonusNumber))
            throw new IllegalArgumentException(EXIST_DUPLICATE.getMessage());
    }

    public static void validateBonusNumberInWinningLotto(Lotto winningLottos, LottoNumber lottoNumber) {
        if (winningLottos.containsLottoNumber(lottoNumber)) {
            throw new IllegalArgumentException(EXIST_DUPLICATE.getMessage());
        }
    }


    public static boolean isLottoNumberInRange(int lottoNumber) {
        return lottoNumber >= LOTTO_NUMBER_START_INCLUSIVE.value() && lottoNumber <= LOTTO_NUMBER_END_INCLUSIVE.value();
    }

}
