package racingcar;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.inputview.AttemptCount;
import io.inputview.RacingCarNames;

class RacingServiceTest {

    @Test
    @DisplayName("RacingService는 올바른 의존성으로 생성된다")
    void createRacingService() {
        // given
        MoveRule moveRule = new RandomMoveRule();
        
        // when & then
        RacingService racingService = new RacingService(moveRule);
        assertThat(racingService).isNotNull();
    }

    @Test
    @DisplayName("자동차 이름과 시도 횟수로 RacingGame을 생성한다")
    void createRacingGame() {
        // given
        MoveRule moveRule = new RandomMoveRule();
        RacingService racingService = new RacingService(moveRule);
        RacingCarNames carNames = new RacingCarNames(Arrays.asList("pobi", "woni", "jun"));
        AttemptCount attemptCount = new AttemptCount(5);
        
        // when
        RacingGame racingGame = racingService.createRacingGame(carNames, attemptCount);
        
        // then
        assertThat(racingGame).isNotNull();
    }
}
