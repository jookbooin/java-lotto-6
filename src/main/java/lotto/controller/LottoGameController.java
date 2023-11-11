package lotto.controller;

import lotto.model.*;
import lotto.view.InputHandler;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

import static lotto.message.GameMessage.*;
import static lotto.message.LottoMessage.LOTTO_COUNT;
import static lotto.validator.WinningLottoValidator.validateBonusNumberInWinningLotto;

public class LottoGameController {

    private final OutputView output;
    private final InputView input;
    private final LottoGenerator lottoGenerator;
    private final RankResult lottoResult;
    private final InputHandler inputHandler;


    public LottoGameController(OutputView output, InputView input, LottoGenerator lottoGenerator, RankResult lottoResult) {
        this.output = output;
        this.input = input;
        this.lottoGenerator = lottoGenerator;
        this.lottoResult = lottoResult;
        this.inputHandler = new InputHandler(output);
    }

    public void start() {

        /**
         * 1 로또 구매
         *
         * */
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        List<Lotto> puchaseLottos = lottoGenerator.generatePurchaseLotto(purchaseAmount);

        /**
         * 2. 결과 출력
         * */
        printPurchaseLottos(puchaseLottos);

        /**
         * 3. 당첨 로또 번호 입력
         * 4. 보너스 번호 입력
         * */
        Lotto winningLotto = readWinningLotto();
//        int bonusNumber = inputBonusNumber(winningLotto);
//        WinningResult winningResult = createWinningResult(winningLotto, bonusNumber);


        /** service로 뺄 수 있을 것 같음. */
//        calculateLottoWinningRecord(puchaseLottos, winningResult);
//        Map<Rank, Integer> RankResult = getCalculatedWinningResult();
//
//        printWinningResultStatistics(RankResult);
//        printEarningRate(puchaseLottos.size());

    }

    private PurchaseAmount readPurchaseAmount() {
        PurchaseAmount purchaseAmount = inputHandler.repeatBeforeSuccess(() -> new PurchaseAmount(inputPurchaseAmount()));
        return purchaseAmount;
    }

    private String inputPurchaseAmount() {
        output.printMessage(ASK_FOR_PURCHASE_COST);
        return input.purchaseAmount();
    }

    private void printPurchaseLottos(List<Lotto> lottos) {
        printPurchaseLottoCount(lottos);
        printSortedPurchaseLottos(lottos);
    }

    private void printPurchaseLottoCount(List<Lotto> lottos) {
        output.printLottoCount(LOTTO_COUNT, lottos.size());
    }

    private void printSortedPurchaseLottos(List<Lotto> lottos) {
        output.printPurchaseLottos(lottos);
    }

    private Lotto readWinningLotto() {
        Lotto lotto = inputHandler.repeatBeforeSuccess(() -> new Lotto(inputWinningNumbers()));
        return lotto;
    }

    private List<Integer> inputWinningNumbers() {
        String input = inputWinningLotto();
        WinningNumbers winningNumbers = new WinningNumbers(input);
        List<Integer> numbers = winningNumbers.getNumbers().stream()
                .map(LottoNumber::getNumber)
                .toList();

        return numbers;
    }

    private String inputWinningLotto() {
        output.printMessage(ASK_FOR_WINNING_NUMBER);
        return input.winningLotto();
    }


    private int inputBonusNumber(Lotto winningLotto) {
        Integer bonusNumber = inputHandler.repeatBeforeSuccess(() -> convertAndValidateBonusNumber(winningLotto, inputBonusNumber()));
        return bonusNumber;
    }

    private String inputBonusNumber() {
        output.printMessage(ASK_FOR_BONUS_NUMBER);
        return input.bonusWinningLottos();
    }

    private int convertAndValidateBonusNumber(Lotto winningLotto, String number) {
        LottoNumber bonusLottoNumber = new LottoNumber(number);
        validateBonusNumberInWinningLotto(winningLotto, bonusLottoNumber);
        return bonusLottoNumber.getNumber();
    }


    private void calculateLottoWinningRecord(List<Lotto> puchaseLottos, WinningResult winningLottos) {
        lottoResult.calculateWinningRecord(puchaseLottos, winningLottos);
    }

    private Map<Rank, Integer> getCalculatedWinningResult() {
        return lottoResult.getWiningResult();
    }

    private void printWinningResultStatistics(Map<Rank, Integer> winningResult) {
        output.printMessage(WINNING_STATISTICS);
        output.printWinningResultStatistics(winningResult);
    }

    private void printEarningRate(int lottoCount) {
        double earningRate = lottoResult.calculateEarningRate(lottoCount);
        output.printEarningRate(earningRate);
    }

}
