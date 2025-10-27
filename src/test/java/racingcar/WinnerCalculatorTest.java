package racingcar;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinnerCalculatorTest {

    private Car createCarWithPosition(String name, int position) {
        Car car = new Car(name);
        // position만큼 이동시키기 위해 여러 번 move 호출
        for (int i = 0; i < position; i++) {
            car.move(new RandomMoveRule(), 5); // 5는 항상 이동
        }
        return car;
    }

    @Test
    @DisplayName("단일 우승자를 찾을 수 있다")
    void findSingleWinner() {
        // given
        List<Car> cars = List.of(
                createCarWithPosition("pobi", 3),
                createCarWithPosition("woni", 5),
                createCarWithPosition("jun", 2)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(cars);

        // then
        assertThat(winners).containsExactly("woni");
    }

    @Test
    @DisplayName("공동 우승자를 찾을 수 있다")
    void findMultipleWinners() {
        // given
        List<Car> cars = List.of(
                createCarWithPosition("pobi", 5),
                createCarWithPosition("woni", 5),
                createCarWithPosition("jun", 3)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(cars);

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치에 있으면 모두 우승자이다")
    void findAllWinnersWhenSamePosition() {
        // given
        List<Car> cars = List.of(
                createCarWithPosition("pobi", 3),
                createCarWithPosition("woni", 3),
                createCarWithPosition("jun", 3)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(cars);

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("모든 자동차가 위치 0에 있으면 모두 우승자이다")
    void findAllWinnersWhenAllAtZero() {
        // given
        List<Car> cars = List.of(
                createCarWithPosition("pobi", 0),
                createCarWithPosition("woni", 0),
                createCarWithPosition("jun", 0)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(cars);

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("빈 결과 리스트에 대해서는 빈 우승자 리스트를 반환한다")
    void findWinnersFromEmptyResults() {
        // given
        List<Car> cars = List.of();

        // when
        List<String> winners = WinnerCalculator.findWinners(cars);

        // then
        assertThat(winners).isEmpty();
    }

    @Test
    @DisplayName("단일 자동차 결과에 대해서는 해당 자동차가 우승자이다")
    void findWinnerFromSingleResult() {
        // given
        List<Car> cars = List.of(
                createCarWithPosition("pobi", 3)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(cars);

        // then
        assertThat(winners).containsExactly("pobi");
    }
}
