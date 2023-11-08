package lotto.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoMachine {
    private final Map<Rank, Integer> winningRecord;

    public LottoMachine() {
        winningRecord = new EnumMap<Rank, Integer>(Rank.class);
        for (Rank value : Rank.values()) {
            winningRecord.put(value, 0);
        }

    }

    public void winningResult(List<Lotto> lottos, WinningNumbers winningNumbers) {

        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.determineRank(lotto);
            updateWinningRecord(rank);
        }
    }

    public void updateWinningRecord(Rank rank) {
        if (rank == Rank.PASS)
            return;
        winningRecord.put(rank, getRankValue(rank) + 1);
    }

    public void print() {
        for (Map.Entry<Rank, Integer> entry : winningRecord.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            System.out.println(rank + ": " + count);
        }
    }

    public int getRankValue(Rank rank){
        return winningRecord.get(rank);
    }


}
