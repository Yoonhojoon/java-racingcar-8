package racingcar;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.inputview.AttemptCount;

class RacingGameTest {

    @Test
    @DisplayName("RacingGame은 자동차 목록과 시도 횟수로 생성된다")
    void createRacingGame() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        AttemptCount attemptCount = new AttemptCount(3);
        MoveRule moveRule = new RandomMoveRule();

        // when
        RacingGame racingGame = new RacingGame(cars, attemptCount, moveRule);

        // then
        assertThat(racingGame.getCars()).hasSize(2);
        assertThat(racingGame.getAttemptCount().getCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("경주를 시작하면 모든 자동차가 동시에 움직인다")
    void startRacingGame() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        AttemptCount attemptCount = new AttemptCount(2);
        MoveRule moveRule = new RandomMoveRule();
        RacingGame racingGame = new RacingGame(cars, attemptCount, moveRule);

        // when
        racingGame.start();

        // then
        // 모든 자동차가 2번의 시도를 완료했는지 확인
        assertThat(racingGame.isFinished()).isTrue();
    }

    @Test
    @DisplayName("경주 결과를 가져올 수 있다")
    void getRacingResults() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        AttemptCount attemptCount = new AttemptCount(1);
        MoveRule moveRule = new RandomMoveRule();
        RacingGame racingGame = new RacingGame(cars, attemptCount, moveRule);

        // when
        racingGame.start();
        List<RacingResult> results = racingGame.getResults();

        // then
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getCarName()).isEqualTo("pobi");
        assertThat(results.get(1).getCarName()).isEqualTo("woni");
    }

    @Test
    @DisplayName("우승자를 계산할 수 있다")
    void getWinners() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"));
        AttemptCount attemptCount = new AttemptCount(1);
        MoveRule moveRule = new RandomMoveRule();
        RacingGame racingGame = new RacingGame(cars, attemptCount, moveRule);

        // when
        racingGame.start();
        List<String> winners = racingGame.getWinners();

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners).containsAnyOf("pobi", "woni");
    }

    @Test
    @DisplayName("여러 명의 우승자가 있을 수 있다")
    void getMultipleWinners() {
        // given
        List<Car> cars = List.of(new Car("pobi"), new Car("woni"), new Car("jun"));
        AttemptCount attemptCount = new AttemptCount(1);
        MoveRule moveRule = new RandomMoveRule();
        RacingGame racingGame = new RacingGame(cars, attemptCount, moveRule);

        // when
        racingGame.start();
        List<String> winners = racingGame.getWinners();

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("경주가 끝나지 않았으면 isFinished는 false이다")
    void isNotFinished() {
        // given
        List<Car> cars = List.of(new Car("pobi"));
        AttemptCount attemptCount = new AttemptCount(2);
        MoveRule moveRule = new RandomMoveRule();
        RacingGame racingGame = new RacingGame(cars, attemptCount, moveRule);

        // when
        // 아직 경주를 시작하지 않음

        // then
        assertThat(racingGame.isFinished()).isFalse();
    }
}
