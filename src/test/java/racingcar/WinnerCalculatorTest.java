package racingcar;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinnerCalculatorTest {

    @Test
    @DisplayName("단일 우승자를 찾을 수 있다")
    void findSingleWinner() {
        // given
        List<RacingResult> results = List.of(
                new RacingResult("pobi", 3),
                new RacingResult("woni", 5),
                new RacingResult("jun", 2)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(results);

        // then
        assertThat(winners).containsExactly("woni");
    }

    @Test
    @DisplayName("공동 우승자를 찾을 수 있다")
    void findMultipleWinners() {
        // given
        List<RacingResult> results = List.of(
                new RacingResult("pobi", 5),
                new RacingResult("woni", 5),
                new RacingResult("jun", 3)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(results);

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni");
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치에 있으면 모두 우승자이다")
    void findAllWinnersWhenSamePosition() {
        // given
        List<RacingResult> results = List.of(
                new RacingResult("pobi", 3),
                new RacingResult("woni", 3),
                new RacingResult("jun", 3)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(results);

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("모든 자동차가 위치 0에 있으면 모두 우승자이다")
    void findAllWinnersWhenAllAtZero() {
        // given
        List<RacingResult> results = List.of(
                new RacingResult("pobi", 0),
                new RacingResult("woni", 0),
                new RacingResult("jun", 0)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(results);

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("빈 결과 리스트에 대해서는 빈 우승자 리스트를 반환한다")
    void findWinnersFromEmptyResults() {
        // given
        List<RacingResult> results = List.of();

        // when
        List<String> winners = WinnerCalculator.findWinners(results);

        // then
        assertThat(winners).isEmpty();
    }

    @Test
    @DisplayName("단일 자동차 결과에 대해서는 해당 자동차가 우승자이다")
    void findWinnerFromSingleResult() {
        // given
        List<RacingResult> results = List.of(
                new RacingResult("pobi", 3)
        );

        // when
        List<String> winners = WinnerCalculator.findWinners(results);

        // then
        assertThat(winners).containsExactly("pobi");
    }
}
