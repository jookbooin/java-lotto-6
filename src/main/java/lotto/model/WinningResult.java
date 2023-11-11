package lotto.model;

import static lotto.constant.NumberConstant.CHECK_BONUS_NUMBER_CONDITION;

public class WinningResult {

    private final Lotto winningLottos;
    private final int bonusNumber;

    public WinningResult(Lotto winningLottos, int bonusNumber) {
        this.winningLottos = winningLottos;
        this.bonusNumber = bonusNumber;
    }

    public static WinningResult createWinningResult(Lotto winningLotto, int bonusNumber) {
        return new WinningResult(winningLotto, bonusNumber);
    }

    public Rank determineRank(Lotto lotto) {

        int matchNumberCount = lotto.countCommonNumberFromAnotherLotto(this.winningLottos);
        boolean bonus = checkExistBonusNumber(lotto, matchNumberCount);

        return Rank.findRank(matchNumberCount, bonus);
    }

    public boolean checkExistBonusNumber(Lotto lotto, int matchNumberCount) {
        boolean bonus = false;
        if (isSameBonusNumberCondition(matchNumberCount)) {
            bonus = lotto.containsBonusNumber(this.bonusNumber);
        }
        return bonus;
    }

    public boolean isSameBonusNumberCondition(int matchNumberCount) {
        return matchNumberCount == CHECK_BONUS_NUMBER_CONDITION.value();
    }

}
