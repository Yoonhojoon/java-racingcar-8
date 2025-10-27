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

    @Test
    @DisplayName("유효한 시도 횟수를 파싱한다")
    void parseAttemptCount_success() {
        // given
        String input = "5";

        // when
        AttemptCount result = inputParser.parseAttemptCount(input);

        // then
        assertThat(result.getCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("공백이 포함된 시도 횟수를 파싱한다")
    void parseAttemptCount_with_spaces() {
        // given
        String input = " 10 ";

        // when
        AttemptCount result = inputParser.parseAttemptCount(input);

        // then
        assertThat(result.getCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외가 발생한다")
    void parseAttemptCount_zero_throws_exception() {
        // given
        String input = "0";

        // when & then
        assertThatThrownBy(() -> inputParser.parseAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 음수면 예외가 발생한다")
    void parseAttemptCount_negative_throws_exception() {
        // given
        String input = "-1";

        // when & then
        assertThatThrownBy(() -> inputParser.parseAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 숫자가 아니면 예외가 발생한다")
    void parseAttemptCount_invalid_format_throws_exception() {
        // given
        String input = "abc";

        // when & then
        assertThatThrownBy(() -> inputParser.parseAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수 입력이 null이면 예외가 발생한다")
    void parseAttemptCount_null_throws_exception() {
        // when & then
        assertThatThrownBy(() -> inputParser.parseAttemptCount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수 입력값이 null이거나 비어있습니다.");
    }

    @Test
    @DisplayName("시도 횟수 입력이 빈 문자열이면 예외가 발생한다")
    void parseAttemptCount_empty_throws_exception() {
        // given
        String input = "";

        // when & then
        assertThatThrownBy(() -> inputParser.parseAttemptCount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수 입력값이 null이거나 비어있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100", "1000"})
    @DisplayName("1 이상의 유효한 시도 횟수를 파싱한다")
    void parseAttemptCount_valid_inputs(String input) {
        // when
        AttemptCount result = inputParser.parseAttemptCount(input);

        // then
        assertThat(result.getCount()).isEqualTo(Integer.parseInt(input));
    }
}
