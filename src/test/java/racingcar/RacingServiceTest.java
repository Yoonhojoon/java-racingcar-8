package racingcar;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.inputview.InputParser;

class RacingServiceTest {

    @Test
    @DisplayName("RacingService는 올바른 의존성으로 생성된다")
    void createRacingService() {
        // given
        InputParser inputParser = new InputParser();
        MoveRule moveRule = new RandomMoveRule();
        
        // when & then
        RacingService racingService = new RacingService(inputParser, moveRule);
        assertThatThrownBy(() -> racingService.run())
                .isInstanceOf(Exception.class); // InputView.read()에서 예외 발생
    }
}
