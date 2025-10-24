package io.inputview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {

    private final InputParser inputParser = new InputParser();

    @Test
    @DisplayName("정상적인 자동차 이름들을 파싱한다")
    void parseCarNames_success() {
        // given
        String input = "pobi,woni";

        // when
        RacingCarNames result = inputParser.parseCarNames(input);

        // then
        assertThat(result.getNames()).containsExactly("pobi", "woni");
    }

    @Test
    @DisplayName("단일 자동차 이름을 파싱한다")
    void parseCarNames_single_car() {
        // given
        String input = "pobi";

        // when
        RacingCarNames result = inputParser.parseCarNames(input);

        // then
        assertThat(result.getNames()).containsExactly("pobi");
    }

    @Test
    @DisplayName("공백이 포함된 자동차 이름을 파싱한다")
    void parseCarNames_with_spaces() {
        // given
        String input = " pobi , woni ";

        // when
        RacingCarNames result = inputParser.parseCarNames(input);

        // then
        assertThat(result.getNames()).containsExactly("pobi", "woni");
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,javaji", "abcdef"})
    @DisplayName("5자를 초과하는 자동차 이름이 있으면 예외를 발생시킨다")
    void parseCarNames_invalid_length_throws_exception(String input) {
        // when & then
        assertThatThrownBy(() -> inputParser.parseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 문자열이 포함되면 예외를 발생시킨다")
    void parseCarNames_empty_name_throws_exception() {
        // given
        String input = "pobi, ,";

        // when & then
        assertThatThrownBy(() -> inputParser.parseCarNames(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null 입력시 예외를 발생시킨다")
    void parseCarNames_null_throws_exception() {
        // when & then
        assertThatThrownBy(() -> inputParser.parseCarNames(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
