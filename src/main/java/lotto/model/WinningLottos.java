package lotto.model;

import static lotto.constant.NumberConstant.CHECK_BONUS_NUMBER_CONDITION;

public class WinningLottos {

    private final Lotto winningLottos;
    private final int bonusNumber;

    public WinningLottos(Lotto winningLottos, int bonusNumber) {
        this.winningLottos = winningLottos;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLottos createWinningLottos(Lotto winningLottos, int bonusNumber) {
        return new WinningLottos(winningLottos, bonusNumber);
    }

    public Rank determineRank(Lotto lotto) {

        int matchNumberCount = lotto.countCommonNumberFromAnotherLotto(this.winningLottos);
        boolean bonus = checkExistBonusNumber(lotto, matchNumberCount);

        return Rank.judge(matchNumberCount, bonus);
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
