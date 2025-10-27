package io.inputview;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public RacingCarNames parseCarNames(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("입력값이 null이거나 비어있습니다.");
        }

        String[] nameArray = input.split(",");
        List<String> trimmedNames = Arrays.stream(nameArray)
                .map(String::trim)
                .toList();

        return new RacingCarNames(trimmedNames);
    }

    public AttemptCount parseAttemptCount(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("시도 횟수 입력값이 null이거나 비어있습니다.");
        }

        try {
            int count = Integer.parseInt(input.trim());
            return new AttemptCount(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
    }
}
