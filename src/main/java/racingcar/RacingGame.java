package racingcar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.inputview.AttemptCount;

public class RacingGame {
    
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
            currentAttempt++;
        }
    }
    
    public void startWithCallback(Runnable afterEachRound) {
        while (!isFinished()) {
            executeRound();
            afterEachRound.run();
            currentAttempt++;
        }
    }

    public void executeRound() {
        for (Car car : cars) {
            moveRule.moveCar(car);
        }
    }
    
    public void incrementAttempt() {
        currentAttempt++;
    }

    public List<Car> getResults() {
        return List.copyOf(cars);
    }

    public List<String> getWinners() {
        List<Car> results = getResults();
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
