package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.BonusNumber;
import lotto.Lotto;
import lotto.configuration.BonusCount;
import lotto.configuration.ScoreBoard;
import lotto.configuration.WinningLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {

    private static final String COMMAS = ",";
    private static final Integer START_NUMBER = 1;
    private static final Integer LAST_NUMBER = 45;
    private static final Integer COUNT = 6;

    public List<Integer> convertToLottoIntegerList(String userInput) {
        return Arrays.stream(userInput.split(COMMAS))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<List<Integer>> generateRandomLottoNumbers(Long length) {

        List<List<Integer>> lottoNumbers = new ArrayList<>();

        for (Integer count = 0; count < length; count++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(START_NUMBER, LAST_NUMBER, COUNT);
            lottoNumbers.add(numbers);
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

    public void repeatCompare() {

        Lotto lotto = new Lotto(convertToLottoIntegerList("1,2,3,4,5,6"));
        List<Integer> winningLotto = lotto.getLotto();
        List<List<Integer>> myLotto = generateRandomLottoNumbers(8L);

        for (int i = 0; i < 8; i++) {
            countMatchingWinningNumbers(winningLotto, myLotto.get(i));
        }
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
            WinningLevel winningLevel = calculateLottoScore(countMatchingWinningNumbers(winningLotto,myLotto.get(i)),countMatchingBonusNumbers(winningLotto,bonusNumber));
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
