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
    private final boolean bonusMatch;

    Rank(int matchCount, int reward, boolean bonusMatch) {
        this.matchCount = matchCount;
        this.reward = reward;
        this.bonusMatch = bonusMatch;
    }

    // 1,2,3,4,5,6 bonus : 7
    // 1,2,3,7,44,45
    // match : 3 bonusMatch : true 라서  FIFTH가 안나올 수 도 있다..?
    public static Rank findRank(int matchCount, boolean bonusMatch) {

        /** 2등인 경우 */
        if (matchCount == Rank.SECOND.getMatchCount() && bonusMatch)
            return Rank.SECOND;

        /** 나머지 */
        return Arrays.stream(values())
                .filter(rank -> isSameMatchCount(matchCount, rank))
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

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    private static boolean isBonusMatch(boolean bonusMatch, Rank rank) {
        return rank.bonusMatch == bonusMatch;
    }

    private static boolean isSameMatchCount(int matchCount, Rank rank) {
        return rank.matchCount == matchCount;
    }

}
