package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Randoms;
import io.inputview.AttemptCount;
import io.outputview.OutputView;

public class RacingGame {
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;
    
    private final List<Car> cars;
    private final AttemptCount attemptCount;
    private final MoveRule moveRule;
    private int currentAttempt;

    public RacingGame(List<Car> cars, AttemptCount attemptCount, MoveRule moveRule) {
        this.cars = new ArrayList<>(cars);
        this.attemptCount = attemptCount;
        this.moveRule = moveRule;
        this.currentAttempt = 0;
    }

    public void start() {
        while (!isFinished()) {
            executeRound();
            printCurrentResults();
            currentAttempt++;
        }
    }

    private void printCurrentResults() {
        List<RacingResult> results = getResults();
        OutputView.printRacingResults(results);
    }

    private void executeRound() {
        for (Car car : cars) {
            int randomValue = Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
            car.move(moveRule, randomValue);
        }
    }

    public List<RacingResult> getResults() {
        return cars.stream()
                .map(car -> new RacingResult(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    public List<String> getWinners() {
        List<RacingResult> results = getResults();
        return WinnerCalculator.findWinners(results);
    }

    public boolean isFinished() {
        return currentAttempt >= attemptCount.getCount();
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }

    public AttemptCount getAttemptCount() {
        return attemptCount;
    }
}
