package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingResultTest {

    @Test
    @DisplayName("RacingResult는 자동차 이름과 위치로 생성된다")
    void createRacingResult() {
        // given
        String carName = "pobi";
        int position = 3;

        // when
        RacingResult result = new RacingResult(carName, position);

        // then
        assertThat(result.getCarName()).isEqualTo("pobi");
        assertThat(result.getPosition()).isEqualTo(3);
    }

    @Test
    @DisplayName("RacingResult는 위치를 비교할 수 있다")
    void comparePosition() {
        // given
        RacingResult result1 = new RacingResult("pobi", 3);
        RacingResult result2 = new RacingResult("woni", 5);
        RacingResult result3 = new RacingResult("jun", 3);

        // when & then
        assertThat(result1.compareTo(result2)).isNegative(); // 3 < 5
        assertThat(result2.compareTo(result1)).isPositive(); // 5 > 3
        assertThat(result1.compareTo(result3)).isZero(); // 3 == 3
    }

    @Test
    @DisplayName("RacingResult는 위치가 같으면 같은 순위이다")
    void samePositionSameRank() {
        // given
        RacingResult result1 = new RacingResult("pobi", 3);
        RacingResult result2 = new RacingResult("woni", 3);

        // when & then
        assertThat(result1.compareTo(result2)).isZero();
    }
}
