package lotto.controller;

import lotto.model.*;
import lotto.utils.LottoUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.constant.StringConstant.COMMA;
import static lotto.message.GameMessage.*;
import static lotto.message.LottoMessage.LOTTO_COUNT;
import static lotto.model.WinningLottos.createWinningLottos;
import static lotto.utils.LottoUtils.convertStringToInteger;
import static lotto.utils.LottoUtils.splitStringToList;
import static lotto.validator.WinningLottosValidator.validateBonusNumber;

public class LottoGameController {

    private final OutputView output;
    private final InputView input;
    private final LottoGenerator lottoGenerator;
    private final LottoResult lottoResult;


    public LottoGameController(OutputView output, InputView input, LottoGenerator lottoGenerator, LottoResult lottoResult) {
        this.output = output;
        this.input = input;
        this.lottoGenerator = lottoGenerator;
        this.lottoResult = lottoResult;
    }

    public void start() {

        /**
         * 1 로또 구매
         *
         * */
        PurchaseAmount purchaseAmount = inputLottoPurchaseAmount();
        List<Lotto> puchaseLottos = lottoGenerator.generatePurchaseLotto(purchaseAmount);

        /**
         * 2. 결과 출력
         * */
        printPurchaseLottos(puchaseLottos);


        /**
         * 3. 당첨 금액 입력
         * */
        Lotto lottoWinningLottos = inputLottoWinningLottos();

        /**4.*/
        int bonusWinningNumber = inputBonusWinningNumber(lottoWinningLottos);
        WinningLottos winningLottos = createWinningLottos(lottoWinningLottos, bonusWinningNumber);

        calculateLottoWinningRecord(puchaseLottos, winningLottos);
        Map<Rank, Integer> winningResult = getCalculatedWinningResult();

        printWinningResultStatistics(winningResult);
        printEarningRate(puchaseLottos.size());

    }

    private PurchaseAmount inputLottoPurchaseAmount() {

        while (true) {
            try {
                String money = inputPurchaseAmount();
                return new PurchaseAmount(money);
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }
    }

    private String inputPurchaseAmount() {
        output.printMessage(ASK_FOR_PURCHASE_COST);
        return input.purchaseAmount();
    }

    private void printPurchaseLottos(List<Lotto> lottos) {
        printPurchaseLottoCount(lottos);
        printSortedPurchaseLottos(lottos);
    }

    private void printSortedPurchaseLottos(List<Lotto> lottos) {
        output.printPurchaseLottos(lottos);
    }

    private void printPurchaseLottoCount(List<Lotto> lottos) {
        output.printLottoCount(LOTTO_COUNT, lottos.size());
    }

    private Lotto inputLottoWinningLottos() {

        while (true) {
            try {
                String numbers = inputWinningLottos();
                return createLottoWinningLottos(numbers);
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }

    }

    private String inputWinningLottos() {
        output.printMessage(ASK_FOR_WINNING_NUMBER);
        return input.lottoWinningLottos();
    }

    private Lotto createLottoWinningLottos(String numbers) {
        List<Integer> winningLottos = convertToWinningLottos(numbers);
        return new Lotto(winningLottos);
    }

    private List<Integer> convertToWinningLottos(String input) {
        List<String> numbers = splitStringToList(COMMA, input);
        return convertToNumbers(numbers);
    }

    private List<Integer> convertToNumbers(List<String> numbers) {
        return numbers.stream()
                .map(LottoUtils::validateAndConvertStringToInteger)
                .collect(Collectors.toList());
    }

    private int inputBonusWinningNumber(Lotto lottoWinningLottos) {

        while (true) {
            try {
                String number = inputBonusNumber();
                int bonusWinningNumber = convertAndValidateBonusNumber(lottoWinningLottos, number);
                return bonusWinningNumber;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }

    }

    private String inputBonusNumber() {
        output.printMessage(ASK_FOR_BONUS_NUMBER);
        return input.bonusWinningLottos();
    }

    private int convertAndValidateBonusNumber(Lotto lottoWinningLottos, String number) {
        int bonusWinningNumber = convertStringToInteger(number);
        validateBonusNumber(lottoWinningLottos, bonusWinningNumber);
        return bonusWinningNumber;
    }

    private void calculateLottoWinningRecord(List<Lotto> puchaseLottos, WinningLottos winningLottos) {
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
