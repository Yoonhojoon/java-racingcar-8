package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @Test
    @DisplayName("자동차는 이름과 초기 위치 0으로 생성된다")
    void createCar() {
        // given
        String name = "pobi";

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차는 MoveRule에 따라 전진할 수 있다")
    void move_withMoveRule() {
        // given
        Car car = new Car("pobi");
        MoveRule moveRule = new RandomMoveRule();

        // when
        car.move(moveRule, 4);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("자동차는 MoveRule에 따라 전진하지 않을 수 있다")
    void notMove_withMoveRule() {
        // given
        Car car = new Car("pobi");
        MoveRule moveRule = new RandomMoveRule();

        // when
        car.move(moveRule, 3);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차는 여러 번 전진할 수 있다")
    void moveMultipleTimes() {
        // given
        Car car = new Car("pobi");
        MoveRule moveRule = new RandomMoveRule();

        // when
        car.move(moveRule, 4);
        car.move(moveRule, 5);
        car.move(moveRule, 3); // 전진하지 않음
        car.move(moveRule, 6);

        // then
        assertThat(car.getPosition()).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    @DisplayName("4 이상의 값에서는 전진한다")
    void move_whenValueIs4OrMore(int value) {
        // given
        Car car = new Car("pobi");
        MoveRule moveRule = new RandomMoveRule();

        // when
        car.move(moveRule, value);

        // then
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    @DisplayName("3 이하의 값에서는 전진하지 않는다")
    void notMove_whenValueIs3OrLess(int value) {
        // given
        Car car = new Car("pobi");
        MoveRule moveRule = new RandomMoveRule();

        // when
        car.move(moveRule, value);

        // then
        assertThat(car.getPosition()).isEqualTo(0);
    }
}
