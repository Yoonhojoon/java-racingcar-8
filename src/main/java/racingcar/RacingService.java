package racingcar;

import java.util.List;
import java.util.stream.Collectors;

import io.inputview.AttemptCount;
import io.inputview.RacingCarNames;

public class RacingService {
    private final MoveRule moveRule;

    public RacingService(MoveRule moveRule) {
        this.moveRule = moveRule;
    }

    public RacingGame createRacingGame(RacingCarNames carNames, AttemptCount attemptCount) {
        List<Car> cars = createCars(carNames);
        return new RacingGame(cars, attemptCount, moveRule);
    }

    private List<Car> createCars(RacingCarNames carNames) {
        return carNames.getNames().stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }
}
