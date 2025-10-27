package racingcar;

import java.util.List;
import java.util.stream.Collectors;

public class WinnerCalculator {

    public static List<String> findWinners(List<RacingResult> results) {
        if (results.isEmpty()) {
            return List.of();
        }

        int maxPosition = results.stream()
                .mapToInt(RacingResult::getPosition)
                .max()
                .orElse(0);

        return results.stream()
                .filter(result -> result.getPosition() == maxPosition)
                .map(RacingResult::getCarName)
                .collect(Collectors.toList());
    }
}
