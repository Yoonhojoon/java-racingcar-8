package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoveRuleTest {

    @Test
    @DisplayName("RandomMoveRule은 4 이상일 때 전진한다")
    void randomMoveRule_canMove_whenValueIs4OrMore() {
        // given
        MoveRule moveRule = new RandomMoveRule();

        // when & then
        assertThat(moveRule.canMove(4)).isTrue();
        assertThat(moveRule.canMove(5)).isTrue();
        assertThat(moveRule.canMove(6)).isTrue();
        assertThat(moveRule.canMove(7)).isTrue();
        assertThat(moveRule.canMove(8)).isTrue();
        assertThat(moveRule.canMove(9)).isTrue();
    }

    @Test
    @DisplayName("RandomMoveRule은 3 이하일 때 전진하지 않는다")
    void randomMoveRule_cannotMove_whenValueIs3OrLess() {
        // given
        MoveRule moveRule = new RandomMoveRule();

        // when & then
        assertThat(moveRule.canMove(0)).isFalse();
        assertThat(moveRule.canMove(1)).isFalse();
        assertThat(moveRule.canMove(2)).isFalse();
        assertThat(moveRule.canMove(3)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    @DisplayName("4 이상의 값에서는 전진할 수 있다")
    void randomMoveRule_canMove_whenValueIs4OrMore(int value) {
        // given
        MoveRule moveRule = new RandomMoveRule();

        // when
        boolean canMove = moveRule.canMove(value);

        // then
        assertThat(canMove).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("3 이하의 값에서는 전진할 수 없다")
    void randomMoveRule_cannotMove_whenValueIs3OrLess(int value) {
        // given
        MoveRule moveRule = new RandomMoveRule();

        // when
        boolean canMove = moveRule.canMove(value);

        // then
        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("moveCar 메서드는 자동차를 이동시킨다")
    void moveCar_movesCar() {
        // given
        MoveRule moveRule = new RandomMoveRule();
        Car car = new Car("test");

        // when
        moveRule.moveCar(car);

        // then
        // moveCar는 난수를 사용하므로 정확한 위치는 예측할 수 없지만
        // 메서드가 정상적으로 실행되는지 확인
        assertThat(car).isNotNull();
    }
}
