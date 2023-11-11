package lotto.model;

import java.text.NumberFormat;
import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000, false),
    SECOND(5, 30000000, true),
    THIRD(5, 1500000, false),
    FOURTH(4, 50000, false),
    FIFTH(3, 5000, false),
    PASS(0, 0, false);

    private final int matchCount;
    private final int reward;
    private final boolean isBonusMatched;

    Rank(int matchCount, int reward, boolean isBonusMatched) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.isBonusMatched = isBonusMatched;
    }

    // 1,2,3,4,5,6 bonus : 7
    // 1,2,3,7,44,45
    // match : 3 bonusMatch : true 라서  FIFTH가 안나올 수 도 있다..?
    public static Rank judge(int matchCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> isSameMatchCount(matchCount, rank) && isSameBonusStatus(isBonusMatched, rank))
                .findFirst()
                .orElse(PASS);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }

    public String rewardNumberFormat() {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        return formatter.format(reward);
    }

    public boolean isBonusMatched() {
        return isBonusMatched;
    }

    private static boolean isSameBonusStatus(boolean isBonusMatched, Rank rank) {
        return rank.isBonusMatched == isBonusMatched;
    }

    private static boolean isSameMatchCount(int matchCount, Rank rank) {
        return rank.matchCount == matchCount;
    }

}
