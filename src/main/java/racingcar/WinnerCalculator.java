package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerCalculator {

    public static List<String> findWinners(List<Car> cars) {
        if (cars.isEmpty()) {
            return List.of();
        }

        int maxPosition = cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
