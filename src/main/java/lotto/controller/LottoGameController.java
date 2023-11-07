package lotto.controller;

import lotto.generator.LottoGenerator;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.PurchaseCost;
import lotto.utils.LottoUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.constant.StringConstant.COMMA;
import static lotto.message.GameMessage.*;
import static lotto.message.LottoMessage.LOTTO_COUNT;
import static lotto.utils.LottoUtils.convertStringToInteger;
import static lotto.utils.LottoUtils.splitStringToList;
import static lotto.validator.WinningNumbersValidator.validateBonusNumber;

public class LottoGameController {

    private final OutputView output;
    private final InputView input;
    private final LottoGenerator lottoGenerator;


    public LottoGameController(OutputView output, InputView input, LottoGenerator lottoGenerator) {
        this.output = output;
        this.input = input;
        this.lottoGenerator = lottoGenerator;
    }

    public void start() {
        PurchaseCost purchaseCost = inputLottoPurchaseCost();
        int lottoCount = purchaseCost.calculateLottoCount();
        Lottos puchaseLottos = lottoGenerator.generatePurchaseLottos(lottoCount);

        printPurchaseLottos(lottoCount, puchaseLottos);

        Lotto lottoWinningNumbers = inputLottoWinningNumbers();
        int bonusWinningNumber = inputBonusWinningNumber(lottoWinningNumbers);

        printWinningStatistics();
    }

    private PurchaseCost inputLottoPurchaseCost() {

        while (true) {
            try {
                String money = inputPurchaseCost();
                return new PurchaseCost(money);
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }
    }

    private String inputPurchaseCost() {
        output.printMessage(ASK_FOR_PURCHASE_COST);
        return input.purchaseCost();
    }

    private void printPurchaseLottos(int lottoCount, Lottos lottos) {
        printPurchaseLottoCount(lottoCount);
        printSortedPurchaseLottos(lottos);
    }

    private void printSortedPurchaseLottos(Lottos lottos) {
        output.printPurchaseLottos(lottos);
    }

    private void printPurchaseLottoCount(int lottoCount) {
        output.printLottoCount(LOTTO_COUNT, lottoCount);
    }

    private Lotto inputLottoWinningNumbers() {

        while (true) {
            try {
                String numbers = inputWinningNumbers();
                return createLottoWinningNumbers(numbers);
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }

    }

    private String inputWinningNumbers() {
        output.printMessage(ASK_FOR_WINNING_NUMBER);
        return input.lottoWinningNumbers();
    }

    private Lotto createLottoWinningNumbers(String numbers) {
        List<Integer> winningNumbers = convertToWinningNumbers(numbers);
        return new Lotto(winningNumbers);
    }

    private List<Integer> convertToWinningNumbers(String input) {
        List<String> numbers = splitStringToList(COMMA, input);
        return convertToNumbers(numbers);
    }

    private List<Integer> convertToNumbers(List<String> numbers) {
        return numbers.stream()
                .map(LottoUtils::validateAndConvertStringToInteger)
                .collect(Collectors.toList());
    }

    private int inputBonusWinningNumber(Lotto lottoWinningNumbers) {

        while (true) {
            try {
                String number = inputBonusNumber();
                int bonusWinningNumber = convertAndValidateBonusNumber(lottoWinningNumbers, number);
                return bonusWinningNumber;
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }

    }

    private String inputBonusNumber() {
        output.printMessage(ASK_FOR_BONUS_NUMBER);
        return input.bonusWinningNumbers();
    }

    private int convertAndValidateBonusNumber(Lotto lottoWinningNumbers, String number) {
        int bonusWinningNumber = convertStringToInteger(number);
        validateBonusNumber(lottoWinningNumbers, bonusWinningNumber);
        return bonusWinningNumber;
    }

    private void printWinningStatistics() {
        output.printMessage(WINNING_STATISTICS);
    }

}
