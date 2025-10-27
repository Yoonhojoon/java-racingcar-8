package io.outputview;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.Car;

class OutputViewTest {

    @Test
    @DisplayName("자동차 위치를 출력할 수 있다")
    void printCarPosition() {
        // given
        String carName = "pobi";
        int position = 3;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // when
            OutputView.printCarPosition(carName, position);

            // then
            assertThat(outputStream.toString()).isEqualTo("pobi : ---\n");
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @DisplayName("위치가 0인 자동차는 출력되지 않는다")
    void printCarPosition_zero() {
        // given
        String carName = "pobi";
        int position = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // when
            OutputView.printCarPosition(carName, position);

            // then
            assertThat(outputStream.toString()).isEqualTo("pobi : \n");
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @DisplayName("경주 결과를 출력할 수 있다")
    void printRacingResults() {
        // given
        List<Car> cars = List.of(
                createCarWithPosition("pobi", 3),
                createCarWithPosition("woni", 2)
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // when
            OutputView.printRacingResults(cars);

            // then
            String expected = "pobi : ---\nwoni : --\n\n";
            assertThat(outputStream.toString()).isEqualTo(expected);
        } finally {
            System.setOut(System.out);
        }
    }

    private Car createCarWithPosition(String name, int position) {
        Car car = new Car(name);
        // position만큼 이동시키기 위해 여러 번 move 호출
        for (int i = 0; i < position; i++) {
            car.move(new racingcar.RandomMoveRule(), 5); // 5는 항상 이동
        }
        return car;
    }

    @Test
    @DisplayName("우승자를 출력할 수 있다")
    void printWinners() {
        // given
        List<String> winners = List.of("pobi");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // when
            OutputView.printWinners(winners);

            // then
            assertThat(outputStream.toString()).isEqualTo("최종 우승자 : pobi\n");
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @DisplayName("공동 우승자를 출력할 수 있다")
    void printMultipleWinners() {
        // given
        List<String> winners = List.of("pobi", "woni");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // when
            OutputView.printWinners(winners);

            // then
            assertThat(outputStream.toString()).isEqualTo("최종 우승자 : pobi, woni\n");
        } finally {
            System.setOut(System.out);
        }
    }
}