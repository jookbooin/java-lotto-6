package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.validator.GlobalValidator.validateBlank;
import static lotto.validator.WinningLottoValidator.validateCommaAtStartOrEnd;

public class InputView {

    public String purchaseAmount() {
        String input = Console.readLine();
        validateBlank(input);
        return input;
    }

    public String winningLotto() {
        String input = Console.readLine();
        validateBlank(input);
        validateCommaAtStartOrEnd(input);
        return input;
    }

    public String bonusWinningLottos() {
        String input = Console.readLine();
        validateBlank(input);
        return input;
    }
}
