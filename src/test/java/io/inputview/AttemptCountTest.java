package io.inputview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AttemptCountTest {

    @Test
    @DisplayName("유효한 시도 횟수로 객체를 생성할 수 있다")
    void createValidAttemptCount() {
        // given
        int validCount = 5;

        // when
        AttemptCount attemptCount = new AttemptCount(validCount);

        // then
        assertThat(attemptCount.getCount()).isEqualTo(5);
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외가 발생한다")
    void createWithZeroCount() {
        // given
        int zeroCount = 0;

        // when & then
        assertThatThrownBy(() -> new AttemptCount(zeroCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    @DisplayName("시도 횟수가 음수면 예외가 발생한다")
    void createWithNegativeCount() {
        // given
        int negativeCount = -1;

        // when & then
        assertThatThrownBy(() -> new AttemptCount(negativeCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    @DisplayName("1 이상의 유효한 시도 횟수로 객체를 생성할 수 있다")
    void createValidAttemptCounts(int validCount) {
        // when
        AttemptCount attemptCount = new AttemptCount(validCount);

        // then
        assertThat(attemptCount.getCount()).isEqualTo(validCount);
    }

    @Test
    @DisplayName("시도 횟수가 1이면 유효하다")
    void createWithOneCount() {
        // given
        int oneCount = 1;

        // when
        AttemptCount attemptCount = new AttemptCount(oneCount);

        // then
        assertThat(attemptCount.getCount()).isEqualTo(1);
    }
}
