package io.outputview;

import java.util.List;
import java.util.StringJoiner;

import racingcar.RacingResult;

public class OutputView {

    public static void printCarNamePrompt() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    public static void printAttemptCountPrompt() {
        System.out.println("시도할 횟수는 몇 회인가요?");
    }

    public static void printRacingStartMessage() {
        System.out.println();
        System.out.println("실행 결과");
    }

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
