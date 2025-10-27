package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.inputview.InputParser;

class RacingControllerTest {

    @Test
    @DisplayName("RacingController는 올바른 의존성으로 생성된다")
    void createRacingController() {
        // given
        InputParser inputParser = new InputParser();
        MoveRule moveRule = new RandomMoveRule();
        RacingService racingService = new RacingService(moveRule);
        
        // when & then
        RacingController racingController = new RacingController(inputParser, racingService);
        assertThat(racingController).isNotNull();
    }
}
