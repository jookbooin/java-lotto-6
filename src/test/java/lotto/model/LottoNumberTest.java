package lotto.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    @DisplayName("숫자가 포함되는지 테스트한다.")
    public void LottoNumberEqualsTest() throws Exception{

        // given
        LottoNumber lottoNumber = new LottoNumber("3");
        int compare = 3;
        // when

        boolean flag = lottoNumber.checkEqual(compare);
        // then

        Assertions.assertThat(flag).isTrue();
    }

}