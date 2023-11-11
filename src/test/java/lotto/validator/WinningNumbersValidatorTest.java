package lotto.validator;

import lotto.model.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.message.ErrorMessage.EXIST_DUPLICATE;
import static lotto.message.ErrorMessage.OUT_OF_RANGE;
import static lotto.validator.WinningLottosValidator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottosValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    @DisplayName("입력 lotto 번호와 bonus 번호가 중복되면 예외가 발생한다.")
    public void DuplicateTest(int bonusWinningLottos) throws Exception{

        Lotto lottoWinningLottos = new Lotto(List.of(1,2,3,4,5,6));

        assertThatThrownBy(()-> validateBonusNumberInWinningLottos(lottoWinningLottos,bonusWinningLottos)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(EXIST_DUPLICATE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {46,50,60,70,1000})
    @DisplayName("입력 숫자 1 ~ 45 범위에 존재하는지 확인")
    public void validateLottoNumberInRangeTest(int bonusWinningLottos) throws Exception{

        assertThatThrownBy(()-> validateLottoNumberInRange(bonusWinningLottos))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    @DisplayName("입력 숫자 1 ~ 45 범위에 존재하는지 확인")
    public void isLottoNumberInRangeTest(int bonusWinningLottos) throws Exception{

        assertThat(isLottoNumberInRange(bonusWinningLottos)).isTrue();

    }
}