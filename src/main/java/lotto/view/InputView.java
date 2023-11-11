package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.validator.GlobalValidator.validateBlank;
import static lotto.validator.GlobalValidator.validateNumber;
import static lotto.validator.WinningLottosValidator.validateCommaAtStartOrEnd;

public class InputView {

    public String purchaseAmount() {
        String input = Console.readLine();
        validateBlank(input);
        validateNumber(input);
        return input;
    }

    public String lottoWinningLottos() {
        String input = Console.readLine();
        validateBlank(input);
        validateCommaAtStartOrEnd(input);
        return input;
    }

    public String bonusWinningLottos() {
        String input = Console.readLine();
        validateBlank(input);
        validateNumber(input);
        return input;
    }
}
