package io.outputview;

import java.util.List;
import java.util.StringJoiner;

import racingcar.RacingResult;

public class OutputView {

    public static void printCarPosition(String carName, int position) {
        String dashes = "-".repeat(position);
        System.out.print(carName + " : " + dashes + "\n");
    }

    public static void printRacingResults(List<RacingResult> results) {
        for (RacingResult result : results) {
            printCarPosition(result.getCarName(), result.getPosition());
        }
        System.out.print("\n");
    }

    public static void printWinners(List<String> winners) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String winner : winners) {
            joiner.add(winner);
        }
        System.out.print("최종 우승자 : " + joiner + "\n");
    }
}
