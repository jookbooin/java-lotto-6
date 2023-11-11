package lotto.generator;

import lotto.model.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoGeneratorTest {

    private LottoGenerator lottoGenerator;

    @BeforeEach
    public void setup() {
        lottoGenerator = new LottoGenerator();
    }

    @ParameterizedTest
    @ValueSource(ints = {8})
    @DisplayName("lottoCount 만큼 Lotto가 만들어지는지 확인")
    public void generateLottoByCountTest(int lottoCount) throws Exception {

//        List<Lotto> lottos = lottoGenerator.generateLottoByCount(lottoCount);
//        assertThat(lottos.size()).isEqualTo(lottoCount);

    }

}
