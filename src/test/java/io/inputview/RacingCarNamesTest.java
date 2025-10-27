package io.inputview;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RacingCarNamesTest {

    @Test
    @DisplayName("유효한 자동차 이름 목록으로 객체를 생성할 수 있다")
    void createValidRacingCarNames() {
        // given
        List<String> names = List.of("pobi", "woni", "jun");

        // when
        RacingCarNames racingCarNames = new RacingCarNames(names);

        // then
        assertThat(racingCarNames.getNames()).containsExactly("pobi", "woni", "jun");
        assertThat(racingCarNames.getCount()).isEqualTo(3);
        assertThat(racingCarNames.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("단일 자동차 이름으로 객체를 생성할 수 있다")
    void createSingleCarName() {
        // given
        List<String> names = List.of("pobi");

        // when
        RacingCarNames racingCarNames = new RacingCarNames(names);

        // then
        assertThat(racingCarNames.getNames()).containsExactly("pobi");
        assertThat(racingCarNames.getCount()).isEqualTo(1);
        assertThat(racingCarNames.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("5자 이하의 자동차 이름은 유효하다")
    void createValidLengthNames() {
        // given
        List<String> names = List.of("a", "ab", "abc", "abcd", "abcde");

        // when
        RacingCarNames racingCarNames = new RacingCarNames(names);

        // then
        assertThat(racingCarNames.getNames()).containsExactly("a", "ab", "abc", "abcd", "abcde");
        assertThat(racingCarNames.getCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("null 자동차 이름 목록이면 예외가 발생한다")
    void createWithNullNames() {
        // when & then
        assertThatThrownBy(() -> new RacingCarNames(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름 목록이 null이거나 비어있습니다.");
    }

    @Test
    @DisplayName("빈 자동차 이름 목록이면 예외가 발생한다")
    void createWithEmptyNames() {
        // given
        List<String> names = List.of();

        // when & then
        assertThatThrownBy(() -> new RacingCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름 목록이 null이거나 비어있습니다.");
    }

    @Test
    @DisplayName("자동차 이름이 null이면 예외가 발생한다")
    void createWithNullName() {
        // given
        List<String> names = Arrays.asList("pobi", null, "woni");

        // when & then
        assertThatThrownBy(() -> new RacingCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 null이거나 빈 값일 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름이 빈 문자열이면 예외가 발생한다")
    void createWithEmptyName() {
        // given
        List<String> names = List.of("pobi", "", "woni");

        // when & then
        assertThatThrownBy(() -> new RacingCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 null이거나 빈 값일 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름이 공백만 있으면 예외가 발생한다")
    void createWithBlankName() {
        // given
        List<String> names = List.of("pobi", "   ", "woni");

        // when & then
        assertThatThrownBy(() -> new RacingCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 null이거나 빈 값일 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "verylongname", "123456"})
    @DisplayName("6자 이상의 자동차 이름이면 예외가 발생한다")
    void createWithTooLongName(String longName) {
        // given
        List<String> names = List.of("pobi", longName, "woni");

        // when & then
        assertThatThrownBy(() -> new RacingCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 5자를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("반환된 이름 목록은 불변이다")
    void returnedNamesAreImmutable() {
        // given
        List<String> names = List.of("pobi", "woni");
        RacingCarNames racingCarNames = new RacingCarNames(names);

        // when
        List<String> returnedNames = racingCarNames.getNames();

        // then
        assertThatThrownBy(() -> returnedNames.add("jun"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("공백만 있는 자동차 이름은 예외가 발생한다")
    void createWithSpacedNames() {
        // given
        List<String> names = List.of("pobi", "   ", "woni");

        // when & then
        assertThatThrownBy(() -> new RacingCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 null이거나 빈 값일 수 없습니다.");
    }
}
