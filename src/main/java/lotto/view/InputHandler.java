package lotto.view;

import java.util.function.Supplier;

public class InputHandler {
    private final OutputView output;

    public InputHandler(OutputView output) {
        this.output = output;
    }

    public <T> T repeatBeforeSuccess(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                output.printErrorMessage(e);
            }
        }
    }

}
