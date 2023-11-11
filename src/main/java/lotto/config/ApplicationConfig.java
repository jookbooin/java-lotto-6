package lotto.config;

import lotto.controller.LottoGameController;
import lotto.model.LottoGenerator;
import lotto.model.LottoResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ApplicationConfig {

    public LottoGameController gameController() {
        return new LottoGameController(outputView(), inputView(), lottoGenerator(), lottoResult());
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private InputView inputView() {
        return new InputView();
    }

    private LottoGenerator lottoGenerator() {
        return new LottoGenerator();
    }

    private LottoResult lottoResult() {
        return new LottoResult();
    }

}
