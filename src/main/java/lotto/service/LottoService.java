package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.configuration.ScoreBoard;
import lotto.configuration.WinningLevel;
import lotto.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LottoService {

    private static final Integer START_NUMBER = 1;
    private static final Integer LAST_NUMBER = 45;
    private static final Integer COUNT = 6;

    public List<List<Integer>> generateRandomLottoNumbers(Long length) {
        List<List<Integer>> lottoNumbers = new ArrayList<>();

        for (Integer count = 0; count < length; count++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(START_NUMBER, LAST_NUMBER, COUNT);
            lottoNumbers.add(Utils.sortAscendingRandomLottoList(numbers));
        }

        return lottoNumbers;
    }

    public Integer countMatchingWinningNumbers(List<Integer> winningLotto, List<Integer> myLotto) {
        Integer countValue = 0;

        for (int j = 0; j < COUNT; j++) {
            if (winningLotto.contains(myLotto.get(j))) {
                countValue++;
            }
        }

        return countValue;
    }

    public boolean countMatchingBonusNumbers(List<Integer> winningLotto, Integer bonusNumber) {
        if (winningLotto.contains(bonusNumber)) {
            return true;
        }

        return false;
    }

    public HashMap<String, Long> generateResultScoreBoard() {
        HashMap<String, Long> scoreBoard = new HashMap<>();

        for (ScoreBoard score : ScoreBoard.values()) {
            scoreBoard.put(score.getKey(), score.getValue());
        }

        return scoreBoard;
    }

    public HashMap<String, Long> getResultScoreBoard(List<List<Integer>> myLotto, List<Integer> winningLotto, Integer bonusNumber) {
        HashMap<String, Long> scoreBoard = generateResultScoreBoard();

        for (int i = 0; i < myLotto.size(); i++) {
            WinningLevel winningLevel = calculateLottoScore(countMatchingWinningNumbers(winningLotto, myLotto.get(i)), countMatchingBonusNumbers(winningLotto, bonusNumber));
            long value = scoreBoard.get(winningLevel.getRank()) + winningLevel.getReward();
            scoreBoard.put(winningLevel.getRank(), value);
        }

        return scoreBoard;

    }

    public WinningLevel calculateLottoScore(int matchingWinningNumber, boolean matchingBonusNumber) {
        int correctCount = matchingWinningNumber;
        boolean correctBonusNumber = matchingBonusNumber;

        WinningLevel winningLevel = WinningLevel.values()[correctCount];

        if (winningLevel == WinningLevel.THIRD_PLACE && correctBonusNumber) {
            return WinningLevel.SECOND_PLACE;
        }

        return winningLevel;
    }

}
